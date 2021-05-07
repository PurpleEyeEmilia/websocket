package com.demo.websocket.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description: 基于redis分布式锁
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class DistributedLockUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLockUtils.class);

    private static final String LOCK_VALUE = "lock";

    private static ShardedJedisPool shardedJedisPool = ApplicationContextUtils.getBean(ShardedJedisPool.class);

    //锁超时时间，默认500ms
    private static final long DEFAULT_TIMEOUT = 500;

    //锁失效时间，默认2s
    private static final int DEFAULT_EXPIRED_TIME = 2;

    //纳秒和毫秒之间的转换率
    private static final long MILLI_NANO_TIME = 1000 * 1000L;

    private static final Random RANDOM = new Random();

    private static Boolean lock(String lockKey, int expire) {
        ShardedJedis shardedJedis = getResource();
        Assert.notNull(shardedJedis, "Jedis is null");
        try {
            Long setnx = shardedJedis.setnx(lockKey, LOCK_VALUE);
            //设置成功，返回 1 。
            //设置失败，返回 0 。
            if (setnx != null && setnx == 1) {
                LOGGER.info("redis lock success, lockKey: {}", lockKey);
                shardedJedis.expire(lockKey, expire);
                LOGGER.info("set redis lock expire success, expire: {}s", expire);
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.error("set redis lock exception", e);
            unLock(shardedJedis, lockKey);
            return false;
        } finally {
            returnResource(shardedJedis);
        }
    }

    private static void unLock(ShardedJedis shardedJedis, String lockKey) {
        try {
            if (shardedJedis != null) {
                shardedJedis.del(lockKey);
                LOGGER.info("释放分布式锁成功，KEY={}", lockKey);
            }
        } catch (Exception e) {
            LOGGER.error("redis unlock exception, unlockKey={}", lockKey, e);
        } finally {
            returnResource(shardedJedis);
        }
    }

    private static ShardedJedis getResource() {
        ShardedJedis shardedJedis = null;
        try {
            Assert.notNull(shardedJedisPool, "shardedJedisPool must not null");
            shardedJedis = shardedJedisPool.getResource();
            return shardedJedis;
        } catch (Exception e) {
            LOGGER.error("getResource exception:", e);
            returnResource(shardedJedis);
            throw e;
        }
    }

    private static void returnResource(ShardedJedis shardedJedis) {
        if (shardedJedis != null) {
            shardedJedis.close();
        }
    }


    public static void lockByKey(String lockKey) {
        lockByKey(lockKey, DEFAULT_EXPIRED_TIME);
    }

    public static void lockByKey(String lockKey, int expire) {
        lockByKey(lockKey, expire, DEFAULT_TIMEOUT);
    }

    public static void lockByKey(String lockKey, long timeout) {
        lockByKey(lockKey, DEFAULT_EXPIRED_TIME, timeout);
    }

    /**
     * 加锁方法
     *
     * @param lockKey 需要加锁的key
     * @param expire  加锁的key的失效时间，单位：s
     * @param timeout 轮询超时时间,单位：ms
     * @return
     */
    public static void lockByKey(String lockKey, int expire, long timeout) {
        try {
            long nanoTime = System.nanoTime();
            long nanoTimeOut = timeout * MILLI_NANO_TIME;

            while (System.nanoTime() - nanoTime < nanoTimeOut) {
                if (lock(lockKey, expire)) {
                    LOGGER.info("获取分布式锁成功！lockKey：{}", lockKey);
                    return;
                }
                LOGGER.info("分布式锁获取等待中······");
                Thread.sleep(10, RANDOM.nextInt(100));
            }
        } catch (Exception e) {
            LOGGER.error("获取分布式锁异常！", e);
        }
        LOGGER.error("获取分布式锁超时异常！lockKey：{}，expire：{}，timeout：{}", lockKey, expire, timeout);
        throw new RuntimeException("获取分布式锁超时异常！");
    }

    /**
     * 解锁方法
     *
     * @param lockKey 加锁的key
     */
    public static void unlockByKey(String lockKey) {
        Assert.notNull(shardedJedisPool, "shardedJedisPool must not null");
        unLock(shardedJedisPool.getResource(), lockKey);
    }

    public static void main(String[] args) {
        final String lockKey = "pn";

        List<Thread> list = new ArrayList<>(1000);

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    lockByKey(lockKey, 5000, 5000 * 1000);
                    System.out.println("thread" + j + "抢到锁了，开始执行业务·····");
                    unlockByKey(lockKey);
                }
            });
            list.add(thread);
        }

        for (Thread thread : list) {
            thread.start();
        }
    }

    public static int getPoolCount(){
        return shardedJedisPool.getNumActive();
    }

}
