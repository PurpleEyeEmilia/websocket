package com.demo.websocket.nio.client;

import java.io.IOException;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/12/19 14:40:14:40
 * @Description:
 */
public class NioClient {

    private static String DEFAULT_HOST = "127.0.0.1";

    private static int DEFAULT_PORT = 8888;

    private static NioClientHandle nioClientHandle;

    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port) {
        if (nioClientHandle != null) {
            nioClientHandle.stop();
        }

        nioClientHandle = new NioClientHandle(ip, port);
        new Thread(nioClientHandle, "client").start();
    }

    public static boolean sendMsg(String msg) throws IOException {
        if (msg.equals("q")) {
            return false;
        }
        nioClientHandle.sendMsg(msg);
        return true;
    }

    public static void stop() {
        nioClientHandle.stop();
    }
}
