package com.demo.websocket.annotation;

import java.lang.annotation.*;

/**
 * Copyright (c) 2017-2018 Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DistributedLock {

    /**
     * 默认锁超时时间，ms
     *
     * @return
     */
    long timeout() default 500;

    /**
     * 默认锁失效时间，s
     *
     * @return
     */
    int expiredTime() default 2;

    /**
     * 需要加锁的key（动态key则此项可不设值）
     *
     * @return
     */
    String lockKey() default "";

    /**
     * 锁前缀
     *
     * @return
     */
    String lockKeyPrefix() default "";

    /**
     * 锁后缀
     *
     * @return
     */
    String lockKeySuffix() default "";

    /**
     * 指定目标方法形参上某个参数名或者javabean对象中的某个字段的值作为动态key
     *
     * @return
     */
    String[] lockKeyByBeanNames();

    /**
     * 间隔符
     *
     * @return
     */
    String interval() default ":";
}
