package com.demo.websocket.practice.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/20 10:26
 * @Desc
 */
public class SmsCglibInterceptor implements MethodInterceptor {

    /**
     *
     * @param o      被代理的对象（需要增强的对象）
     * @param method 被代理对象的方法（需要增强的方法）
     * @param args 被代理对象方法的参数
     * @param methodProxy 用于调用原始方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("发送前，方法名：" + method.getName());

        //注意这里是 methodProxy 调用父类的原始方法，而不是 method 对象调用动态生成的代理类的方法。因为cglib代理类继承了目标类，所以要调用目标类的原始方法，必须是invokeSuper
        Object obj = methodProxy.invokeSuper(o, args);

        System.out.println("发送后，方法名：" + method.getName());

        return obj;
    }
}
