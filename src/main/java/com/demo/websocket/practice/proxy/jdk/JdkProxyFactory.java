package com.demo.websocket.practice.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/20 10:55
 * @Desc
 */
public class JdkProxyFactory {

    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new SmsJdkProxy(target));
    }

}
