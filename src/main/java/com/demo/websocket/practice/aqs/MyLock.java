package com.demo.websocket.practice.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/23 10:32
 * @Desc
 */
public class MyLock extends AbstractQueuedSynchronizer {

    private ReentrantLock lock = new ReentrantLock();

    public void lock() {
        lock.lock();
    }

}
