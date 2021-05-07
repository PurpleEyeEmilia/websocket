package com.demo.websocket.practice;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pengnian
 * @version V1.0
 * @date 2020/10/13 10:34
 * @Desc
 */
public class ConcurrentHashMapTest {

    private static final ConcurrentHashMap<String, String> TEST_MAP = new ConcurrentHashMap<>(55);

    public static void main(String[] args) {
        TEST_MAP.put("1", "1");
        System.out.println("xxx");
    }
}
