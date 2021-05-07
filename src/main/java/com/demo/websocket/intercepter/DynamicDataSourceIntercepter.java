package com.demo.websocket.intercepter;

import com.demo.websocket.datasource.bean.DataSourceBean;
import com.demo.websocket.datasource.holder.DataSourceHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/1 16:23:16:23
 * @Description:
 */
public class DynamicDataSourceIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String switchDataSource = request.getParameter("switchDataSource");
        if (StringUtils.isBlank(switchDataSource)) {
            return true;
        }
        //这里可以扩展自己想要切换的数据源，还可以将要切换的数据源放在数据库中，或者查询更底层的平台级接口返回数据源信息
        DataSourceBean dataSourceBean = new DataSourceBean();
        dataSourceBean.setBeanName("dynamicDataSource");
        dataSourceBean.setDriverClassName("com.mysql.jdbc.Driver");
        dataSourceBean.setUrl("jdbc:mysql://localhost:3306/websocket?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false&amp;allowMultiQueries=true&amp;serverTimezone=GMT%2b8");
        dataSourceBean.setUsername("root");
        dataSourceBean.setPassword("123456");
        dataSourceBean.setInitialSize(1L);
        dataSourceBean.setMinIdle(3L);
        dataSourceBean.setMaxActive(20L);
        dataSourceBean.setMaxWait(60000L);
        dataSourceBean.setTimeBetweenEvictionRunsMillis(60000L);
        dataSourceBean.setMinEvictableIdleTimeMillis(30000L);
        dataSourceBean.setValidationQuery("SELECT 'x' FROM DUAL");
        dataSourceBean.setTestWhileIdle(true);
        dataSourceBean.setTestOnBorrow(false);
        dataSourceBean.setTestOnReturn(false);
        DataSourceHolder.setDataSource(dataSourceBean);
        return true;
    }

}
