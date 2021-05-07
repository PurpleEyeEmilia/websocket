package com.demo.websocket.nio.server;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/12/18 16:49:16:49
 * @Description:
 */
public class NioServer {

    private static int DEFAULT_PORT = 8888;

    private static NioServerHandle serverHandle;

    public static void main(String[] args){
        start();
    }

    public static void start() {
        start(DEFAULT_PORT);

    }

    public static synchronized void start(int port) {
        if (serverHandle != null) {
            serverHandle.stop();
        }

        serverHandle = new NioServerHandle(port);
        new Thread(serverHandle, "server").start();
    }

    public static void stop() {
        serverHandle.stop();
    }
}
