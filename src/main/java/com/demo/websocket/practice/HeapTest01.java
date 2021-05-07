package com.demo.websocket.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/11/23 10:26:10:26
 * @Description:
 */
public class HeapTest01 {

    public static void main(String[] args) {
        //jvm参数：-XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -Xms40M -Xmx40M -Xmn20M
        /*byte[] test1 = new byte[1024 * 1024 / 2];
        byte[] test2 = new byte[1024 * 1024 * 8];

        test2 = null;
        test2 = new byte[1024 * 1024 * 8];*/

        //jvm参数：-XX:PermSize=2M -XX:MaxPermSize=4M -XX:+PrintGCDetails
        /*for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String intern = String.valueOf(i).intern();
        }*/

        //jvm参数：-Xmx5M javatuning.ch5.memory.TestXmx
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            byte[] bytes = new byte[1024 * 1024];
            list.add(bytes);
            System.out.println(i + 1 + "M is allocated!");
        }

        System.out.println("Max memory:" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
    }

}
