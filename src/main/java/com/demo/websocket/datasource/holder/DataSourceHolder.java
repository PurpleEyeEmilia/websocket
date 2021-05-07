package com.demo.websocket.datasource.holder;

import com.demo.websocket.datasource.bean.DataSourceBean;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/7/23 15:04:15:04
 * @Description:
 */
public class DataSourceHolder {

    private static ThreadLocal<DataSourceBean> threadLocal = ThreadLocal.withInitial(() -> null);

    public static DataSourceBean getDataSource() {
        return threadLocal.get();
    }

    public static void setDataSource(DataSourceBean dataSource) {
        threadLocal.set(dataSource);
    }

    public static void removeDataSource() {
        threadLocal.remove();
    }
}
