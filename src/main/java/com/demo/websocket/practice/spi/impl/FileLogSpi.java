package com.demo.websocket.practice.spi.impl;

import com.demo.websocket.practice.spi.MyLog;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/5 15:27
 * @Desc
 */
public class FileLogSpi implements MyLog {

    @Override
    public void printLog(String msg) {
        System.out.println("FileLogSpi" + msg);
    }
}
