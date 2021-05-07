package com.demo.websocket.aspectj;

import com.demo.websocket.annotation.DistributedLock;
import com.demo.websocket.utils.DistributedLockUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description: 设置order为1是为了再事务aop之前，自定义的aop先生效。
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Aspect
@Component
@Order(1)
public class DistributedLockAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLockAspect.class);

    private static final String GET = "get";

    @Before(value = "execution(* com.iflytek.websocket..*.*(..)) && @annotation(distributedLock)")
    public void lock(JoinPoint joinPoint, DistributedLock distributedLock) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GetLockKeyt getLockKeyt = new GetLockKeyt(joinPoint, distributedLock).invoke();
        int expiredTime = getLockKeyt.getExpiredTime();
        long timeout = getLockKeyt.getTimeout();
        String lockTrueKey = getLockKeyt.getLockTrueKey();
        DistributedLockUtils.lockByKey(lockTrueKey, expiredTime, timeout);
    }

    @After(value = "execution(* com.iflytek.websocket..*.*(..)) && @annotation(distributedLock)")
    public void unlock(JoinPoint joinPoint, DistributedLock distributedLock) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException  {
        GetLockKeyt getLockKeyt = new GetLockKeyt(joinPoint, distributedLock).invoke();
        DistributedLockUtils.unlockByKey(getLockKeyt.getLockTrueKey());
    }

    @AfterThrowing(value = "execution(* com.iflytek.websocket..*.*(..)) && @annotation(distributedLock)")
    public void throwUnlock(JoinPoint joinPoint, DistributedLock distributedLock) {
        System.out.println("5555555555555555555555555555555555");
    }

    private class GetLockKeyt {
        private JoinPoint joinPoint;
        private DistributedLock distributedLock;
        private int expiredTime;
        private long timeout;
        private String lockTrueKey;

        public GetLockKeyt(JoinPoint joinPoint, DistributedLock distributedLock) {
            this.joinPoint = joinPoint;
            this.distributedLock = distributedLock;
        }

        public int getExpiredTime() {
            return expiredTime;
        }

        public long getTimeout() {
            return timeout;
        }

        public String getLockTrueKey() {
            return lockTrueKey;
        }

        public GetLockKeyt invoke() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
            String lockKey = distributedLock.lockKey();
            expiredTime = distributedLock.expiredTime();
            timeout = distributedLock.timeout();
            String prefix = distributedLock.lockKeyPrefix();
            String suffix = distributedLock.lockKeySuffix();
            String[] beanNames = distributedLock.lockKeyByBeanNames();
            String interval = distributedLock.interval();

            Assert.state(expiredTime > 0, "expiredTime 不能小于等于0");
            Assert.state(timeout > 0, "timeout 不能小于等于0");

            if (StringUtils.isBlank(lockKey)) {
                Assert.notEmpty(beanNames, "lockKey 和 lockKeyByBeanNames 都为空！");
                Object[] args = joinPoint.getArgs();
                Assert.notEmpty(args, "目标方法无参数！");
                for (Object object : args) {
                    for (String beanName : beanNames) {
                        Assert.hasText(beanName, "beanName 为空！");
                        String upperCase = beanName.substring(0, 1).toUpperCase();
                        String substring = beanName.substring(1);
                        Method method = object.getClass().getMethod(GET + upperCase + substring);
                        Object invoke = method.invoke(object);
                        lockKey += invoke.toString() + interval;
                    }
                }
            }

            lockTrueKey = prefix + lockKey + suffix;
            if (StringUtils.isBlank(suffix) && lockTrueKey.length() > 0) {
                lockTrueKey = lockTrueKey.substring(0, lockTrueKey.length() - 1);
            }
            return this;
        }
    }
}
