package com.demo.websocket.nio.client;

import com.demo.websocket.nio.server.NioServer;

import java.io.IOException;
import java.util.Scanner;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/12/21 10:24:10:24
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, IOException {
        NioServer.start();

        Thread.sleep(100);

        NioClient.start();

        while (true) {
            if (!NioClient.sendMsg(new Scanner(System.in).nextLine())) {
                NioClient.stop();
                NioServer.stop();
            }
        }


    }
}
