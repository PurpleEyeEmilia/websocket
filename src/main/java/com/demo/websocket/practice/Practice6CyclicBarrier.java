package com.demo.websocket.practice;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/24 14:03
 * @Desc
 */
public class Practice6CyclicBarrier {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println(Thread.currentThread().getName() + " 任务执行完成回调");
        });

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 到达屏障点");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " 步骤一开始");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " 步骤二开始");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " 到达屏障点");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " 步骤一开始");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " 步骤二开始");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }

}
