package com.demo.websocket.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware, DisposableBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextUtils.class);

    private static ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        Assert.notNull(applicationContext, "applicationContext未注入，请检查后再试！");
        return applicationContext;
    }

    public static <T> T getBean(Class<T> tClass){
        Assert.notNull(applicationContext, "applicationContext未注入，请检查后再试！");
        return applicationContext.getBean(tClass);
    }

    public static <T> T getBean(String name){
        Assert.notNull(applicationContext, "applicationContext未注入，请检查后再试！");
        return (T) applicationContext.getBean(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.info("清除SpringContextHolder中的ApplicationContext：{}", applicationContext);
        ApplicationContextUtils.applicationContext = null;
    }
}
