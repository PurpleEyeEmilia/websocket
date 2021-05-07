package com.demo.websocket.practice.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/20 10:32
 * @Desc 获取代理类
 */
public class CglibProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getProxyObject(Class<T> tClass){
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(tClass.getClassLoader());
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(new SmsCglibInterceptor());
        return (T) enhancer.create();
    }

}
