package com.demo.websocket.test;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/15 10:40:10:40
 * @Description: 单例模式
 */
public class SingleInstance {

    private SingleInstance() {
    }

    /**
     * 饿汉式
     */
//    private static SingleInstance singleInstance = new SingleInstance();
//
//    public SingleInstance getSingleInstance(){
//        return singleInstance;
//    }

    /**
     * 懒汉式,双重检查，推荐
     * 一定要添加 volatile 关键字是为防止指令重排序，因为 new Instance() 是一个非原子操作，可能创建一个不完整的实例
     *
     * new Instance()  的执行过程可以形象地用如下3行伪代码来表示：
     * memory = allocate();        //1:分配对象的内存空间
     * ctorInstance(memory);       //2:初始化对象
     * singleton3 = memory;        //3:使singleton3指向刚分配的内存地址
     *
     * 但实际上，这个过程可能发生无序写入(指令重排序)，也就是说上面的3行指令可能会被重排序导致先执行第3行后执行第2行，也就是说其真实执行顺序可能是下面这种：
     * memory = allocate();        //1:分配对象的内存空间
     * singleton3 = memory;        //3:使singleton3指向刚分配的内存地址
     * ctorInstance(memory);       //2:初始化对象
     *
     */
//    private static volatile SingleInstance singleInstance = null;
//
//    public SingleInstance getSingleInstance(){
//        if (singleInstance == null) {
//            synchronized (SingleInstance.class){
//                if (singleInstance == null) {
//                    singleInstance = new SingleInstance();
//                }
//            }
//        }
//        return singleInstance;
//    }

    /**
     * 内部类来实现单例，推荐
     */
    private static class SingleInstanceHolder {
        private static SingleInstance singleInstance = new SingleInstance();
    }

    public SingleInstance getSingleInstance() {
        return SingleInstanceHolder.singleInstance;
    }
}
