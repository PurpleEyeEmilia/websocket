package com.demo.websocket.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/12/19 14:40:14:40
 * @Description:
 */
public class NioClientHandle implements Runnable {

    private String ip;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile boolean started;


    public NioClientHandle(String ip, int port) {
        this.ip = ip;
        this.port = port;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            started = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
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

    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }


        while (started) {
            try {
                if (!selector.isOpen()) {
                    System.out.println("selector is close !");
                    break;
                }

                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                SelectionKey selectionKey;
                while (iterator.hasNext()) {
                    selectionKey = iterator.next();
                    iterator.remove();

                    try {
                        handleInput(selectionKey);
                    } catch (Exception e) {
                        if (selectionKey != null) {
                            selectionKey.cancel();
                        }

                        if (selectionKey.channel() != null) {
                            selectionKey.channel().close();
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

    }

    private void handleInput(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            if (selectionKey.isConnectable()) {
                if (socketChannel.finishConnect()) {
                } else {
                    System.exit(1);
                }
            }

            if (selectionKey.isReadable()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                int read = socketChannel.read(byteBuffer);

                if (read > 0) {
                    byteBuffer.flip();

                    byte[] bytes = new byte[byteBuffer.remaining()];

                    byteBuffer.get(bytes);

                    String msg = new String(bytes, "UTF-8");
                    System.out.println("客户端收到消息：" + msg);
                } else {
                    selectionKey.cancel();
                    socketChannel.close();
                }
            }
        }

    }

    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress(ip, port))) {

        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    public void sendMsg(String msg) throws IOException {
        socketChannel.register(selector, SelectionKey.OP_READ);
        doWrite(socketChannel, msg);
    }

    private void doWrite(SocketChannel socketChannel, String msg) throws IOException {
        byte[] bytes = msg.getBytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);

        byteBuffer.put(bytes);

        byteBuffer.flip();

        socketChannel.write(byteBuffer);

    }
}
