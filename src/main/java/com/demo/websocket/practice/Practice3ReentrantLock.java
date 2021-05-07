package com.demo.websocket.practice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/10/22 10:10:10:10
 * @Description:
 */
public class Practice3ReentrantLock {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Practice3ReentrantLock practice3ReentrantLock = new Practice3ReentrantLock();
        Thread thread1 = new Thread(practice3ReentrantLock.createTask(), "FirstThread");
        Thread thread2 = new Thread(practice3ReentrantLock.createTask(), "secondThread");
        thread1.start();
        thread2.start();
        Thread.sleep(600);
        thread2.interrupt();
    }

    private Runnable createTask() {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
//                        if (lock.tryLock()) {
                            try {
                                System.out.println("locked " + Thread.currentThread().getName());
                                Thread.sleep(1000);
                            } finally {
                                lock.unlock();
                                System.out.println("unlocked " + Thread.currentThread().getName());
                            }
                            break;
                        } else {
                            System.out.println("unable lock " + Thread.currentThread().getName());
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " is interrupted");
                }
            }
        };
    }


}
