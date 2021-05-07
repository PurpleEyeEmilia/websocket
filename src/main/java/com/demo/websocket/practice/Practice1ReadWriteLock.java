package com.demo.websocket.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: pn
 * @Date: 2018/8/30 20:38
 * @Description: 读写锁与重入锁的性能比较
 */
public class Practice1ReadWriteLock {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();

    private static Lock writeLock = readWriteLock.writeLock();

    private static int index = 1;

    //重入锁模拟读写
    public static void main(String[] args) {
        final List<Thread> threads = new ArrayList<>(500);
        for (int i = 0; i < 500; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    handleWrite2(handleRead2());
                }
            });
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static void handleWrite(Integer value) {
        try {
            reentrantLock.lock();
            System.out.println("开始写入数据！" + value);
            Thread.sleep(1);
            index = ++value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

    }

    private static Integer handleRead() {
        try {
            reentrantLock.lock();
            System.out.println("开始读取数据！");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return index;
    }

    private static void handleWrite2(Integer value) {
        try {
            writeLock.lock();
            System.out.println("开始写入数据！" + value);
            Thread.sleep(1);
            index = ++value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }

    }

    private static Integer handleRead2() {
        try {
            readLock.lock();
            System.out.println("开始读取数据！");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return index;
    }

}
