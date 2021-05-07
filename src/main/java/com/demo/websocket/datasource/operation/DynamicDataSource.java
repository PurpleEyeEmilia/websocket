package com.demo.websocket.datasource.operation;

import com.alibaba.druid.pool.DruidDataSource;
import com.demo.websocket.datasource.bean.DataSourceBean;
import com.demo.websocket.datasource.holder.DataSourceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public final class DynamicDataSource extends AbstractRoutingDataSource implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    private static final String TARGET_DATA_SOURCES = "targetDataSources";

    private ApplicationContext applicationContext;

    @Override
    protected Object determineCurrentLookupKey() {
        //1.从当前线程上下文中获取数据源
        DataSourceBean dataSource = DataSourceHolder.getDataSource();
        if (dataSource == null) {
            LOGGER.info("获取动态数据源为空，使用默认数据源！");
            return null;
        } else {
            LOGGER.info("获取动态数据源为：{}", dataSource);
        }

        try {
            //2.获取AbstractRoutingDataSource中的私有成员变量targetDataSources的值
            Map<Object, Object> map = getTargetDataSources();

            //3.先判断后创建数据源的过程，多线程会有线程安全问题，须加锁。
            synchronized (this) {
                if (!map.containsKey(dataSource.getBeanName())) {
                    //4.创建创建目标数据源
                    map.put(dataSource.getBeanName(), createDataSource(dataSource));

                    //5.通知spring有bean更新
                    super.afterPropertiesSet();
                }
            }

            //返回bean的名称
            return dataSource.getBeanName();
        } catch (NoSuchFieldException e) {
            LOGGER.error("获取目标数据源异常！", e);
            throw new RuntimeException("获取目标数据源异常");
        } catch (IllegalAccessException e) {
            LOGGER.error("访问未知字段异常！", e);
            throw new RuntimeException("访问未知字段异常");
        }
    }

    private Object createDataSource(DataSourceBean dataSource) throws IllegalAccessException {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) this.applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DruidDataSource.class);
        Map<String, Object> map = getKeyValueProperties(DataSourceBean.class, dataSource);
        for (String key : map.keySet()) {
            beanDefinitionBuilder.addPropertyValue(key, map.get(key));
        }

        beanFactory.registerBeanDefinition(dataSource.getBeanName(), beanDefinitionBuilder.getBeanDefinition());
        return applicationContext.getBean(dataSource.getBeanName());
    }

    private <T> Map<String, Object> getKeyValueProperties(Class<T> clazz, Object object) throws IllegalAccessException {
        Field[] declaredFields = clazz.getDeclaredFields();
        Map<String, Object> reslut = new HashMap<>(16);
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            reslut.put(declaredField.getName(), declaredField.get(object));
        }
        reslut.remove("beanName");
        return reslut;
    }

    private Map<Object, Object> getTargetDataSources() throws NoSuchFieldException, IllegalAccessException {
        Field field = AbstractRoutingDataSource.class.getDeclaredField(TARGET_DATA_SOURCES);
        field.setAccessible(true);
        return (Map<Object, Object>) field.get(this);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
