package com.demo.websocket.practice.proxy.jdk;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/20 10:42
 * @Desc
 */
@AllArgsConstructor
public class SmsJdkProxy implements InvocationHandler {
    /**
     *
     */
    private final Object target;

    /**
     * 调用代理对象方法真正的逻辑实现
     *
     * @param proxy 代理的对象（需要增强的对象）
     * @param method 被代理对象的方法 （需要增强对象的方法）
     * @param args 被代理对象方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("发送前，方法名：" + method.getName());

        //注意这里的传入的是 target 对象，而不是proxy 对象
        Object obj = method.invoke(target, args);

        System.out.println("发送后，方法名：" + method.getName());

        return obj;
    }
}
