package com.demo.websocket.practice;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/24 10:59
 * @Desc
 */
public class Practice5CountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getThreadCountTime());
    }

    /**
     * 模拟十位选手赛跑，得到所有选手到达终点的时间
     *
     * @return
     * @throws InterruptedException
     */
    private static long getThreadCountTime() throws InterruptedException {
        CountDownLatch c1 = new CountDownLatch(1);
        CountDownLatch c2 = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long a = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    c1.await();
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "选手到达终点， 成绩是：" + System.currentTimeMillis() + "ms");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    c2.countDown();
                }
            });
            executorService.submit(thread);
        }
        System.out.println("砰，游戏开始");
        c1.countDown();
        c2.await();
        System.out.println("游戏结束");
        long b = System.currentTimeMillis();
        executorService.shutdown();
        return b - a;
    }


}
