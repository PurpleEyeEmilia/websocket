package com.demo.websocket.practice.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/5 15:34
 * @Desc
 */
public class Main {

    private static ServiceLoader<MyLog> serviceLoader = ServiceLoader.load(MyLog.class);

    public static void main(String[] args) {
        for (MyLog myLog : serviceLoader) {
            myLog.printLog("hello spi");
        }
    }
}
