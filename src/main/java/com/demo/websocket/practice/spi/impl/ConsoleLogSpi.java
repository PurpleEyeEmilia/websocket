package com.demo.websocket.practice.spi.impl;

import com.demo.websocket.practice.spi.MyLog;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/5 15:26
 * @Desc
 */
public class ConsoleLogSpi implements MyLog {

    @Override
    public void printLog(String msg) {
        System.out.println("ConsoleLogSpi：" + msg);
    }
}
