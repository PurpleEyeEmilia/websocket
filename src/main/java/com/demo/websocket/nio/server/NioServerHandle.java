package com.demo.websocket.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Copyright (c) 2017-2018 Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/12/18 16:50:16:50
 * @Description:
 */
public class NioServerHandle implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean started;

    public NioServerHandle(int port) {
        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            started = true;

            System.out.println("服务已启动，端口：" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        while (started) {
            try {
                if (!selector.isOpen()) {
                    System.out.println("selector is close !");
                    break;
                }

                selector.select(1000);

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                SelectionKey key;
                while (iterator.hasNext()) {
                    key = iterator.next();

                    iterator.remove();

                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

                SocketChannel socketChannel = serverSocketChannel.accept();

                socketChannel.configureBlocking(false);

                socketChannel.register(selector, SelectionKey.OP_READ);

            }

            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                int read = socketChannel.read(byteBuffer);

                if (read > 0) {
                    byteBuffer.flip();

                    byte[] bytes = new byte[byteBuffer.remaining()];

                    byteBuffer.get(bytes);

                    String msg = new String(bytes, "UTF-8");

                    System.out.println("服务端接受到消息：" + msg);

                    doWrite(socketChannel, msg);

                } else {
                    key.cancel();
                    socketChannel.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel socketChannel, String msg) throws IOException {

        byte[] bytes = msg.getBytes();

        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);

        writeBuffer.put(bytes);

        writeBuffer.flip();

        socketChannel.write(writeBuffer);
    }

    public void stop() {
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        started = false;
    }


}
