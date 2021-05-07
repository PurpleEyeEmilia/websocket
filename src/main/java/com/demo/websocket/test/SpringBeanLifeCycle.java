package com.demo.websocket.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/14 13:57:13:57
 * @Description:
 */
public class SpringBeanLifeCycle implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {

    private String beanName = "xxx";

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    private String properties;

    private String initName;

    public SpringBeanLifeCycle() {
        System.out.println(beanName);
        System.out.println("空参构造初始化！");
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("setBeanName");
        this.beanName = beanName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("注入beanFactory！");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        System.out.println(beanName);
        if (bean instanceof SpringBeanLifeCycle) {
            System.out.println(((SpringBeanLifeCycle) bean).properties);
        }
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.properties = "haha";
        System.out.println("afterPropertiesSet !!!");
    }

    public void initMethod() {
        System.out.println("init Method !");
        this.initName = "init";
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        System.out.println(beanName);
        if (bean instanceof SpringBeanLifeCycle) {
            System.out.println(((SpringBeanLifeCycle) bean).properties);
        }
        return bean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("bean destroy !");
    }

    private void destroyMethod() {
        System.out.println("destroy Method!");
    }
}
