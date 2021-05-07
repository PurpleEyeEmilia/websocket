package com.demo.websocket.practice;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/11/27 09:43:9:43
 * @Description:
 */
public class GcTest01 implements Runnable {

    private static ScheduledExecutorService executorService
            = Executors.newScheduledThreadPool(2);

    private Deque<byte[]> deque;
    private int objectSize;
    private int queueSize;

    private static String[] a;

    public GcTest01(int objectSize, int ttl) {
        this.deque = new ArrayDeque<>();
        this.objectSize = objectSize;
        this.queueSize = ttl * 1000;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            deque.add(new byte[objectSize]);
            if (deque.size() > queueSize) {
                deque.poll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        executorService.scheduleAtFixedRate(
                new GcTest01(200 * 1024 * 1024 / 1000, 5),
                0, 100, TimeUnit.MILLISECONDS);

        executorService.scheduleAtFixedRate(
                new GcTest01(50 * 1024 * 1024 / 1000, 120),
                0, 100, TimeUnit.MILLISECONDS);

        TimeUnit.MINUTES.sleep(10);
        executorService.shutdownNow();
    }
}
