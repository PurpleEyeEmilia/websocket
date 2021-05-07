package com.demo.websocket.test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pengnian
 * @version V1.0
 * @date 2020/9/24 15:26
 * @Desc
 */
public class MapTest {


    private static volatile Map<String, String> testMap = new HashMap<>();

    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    MapTest mapTest = new MapTest();
                    System.out.println(Thread.currentThread().getName() + "执行----------");
                    mapTest.look("1");
                }
            });
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (String s : testMap.keySet()) {
            System.out.println(s + "=========>" + testMap.get(s));
        }
    }

    private String look(String key) {
        String result = testMap.get(key);
        if (result == null) {
            synchronized (LOCK) {
                result = testMap.get(key);
                Random random = new Random();
                int i = random.nextInt();
                if (result == null) {
                    testMap.put(key, String.valueOf(i));
                }
            }
        }
        return result;
    }

    private String look1(String key) {
        String result = testMap.get(key);
        if (result == null) {
            synchronized (LOCK) {
                result = testMap.get(key);
                Random random = new Random();
                int i = random.nextInt();
                if (result == null) {
                    Map<String, String> testMap1 = new HashMap<>(testMap.size() + 1);
                    testMap1.putAll(testMap);
                    testMap1.put(key, String.valueOf(i));
                    testMap = testMap1;
                }
            }
        }
        return result;
    }

    private static void concurrentHashMapTest() {
        Map<String, String> map = new ConcurrentHashMap<>();


    }


}
