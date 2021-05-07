package com.demo.websocket.datasource.bean;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public final class DataSourceBean {

    /**
     *
     */
    private String beanName;

    /**
     *
     */
    private String driverClassName;

    /**
     *
     */
    private String url;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private Long initialSize;

    /**
     *
     */
    private Long minIdle;

    /**
     *
     */
    private Long maxActive;

    /**
     *
     */
    private Long maxWait;

    /**
     *
     */
    private Long timeBetweenEvictionRunsMillis;

    /**
     *
     */
    private Long minEvictableIdleTimeMillis;

    /**
     *
     */
    private String validationQuery;

    /**
     *
     */
    private Boolean testWhileIdle;

    /**
     *
     */
    private Boolean testOnBorrow;

    /**
     *
     */
    private Boolean testOnReturn;

    public DataSourceBean() {
    }

    public DataSourceBean(String beanName, String driverClassName, String url, String username, String password, Long initialSize, Long minIdle, Long maxActive, Long maxWait, Long timeBetweenEvictionRunsMillis, Long minEvictableIdleTimeMillis, String validationQuery, Boolean testWhileIdle, Boolean testOnBorrow, Boolean testOnReturn) {
        this.beanName = beanName;
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
        this.initialSize = initialSize;
        this.minIdle = minIdle;
        this.maxActive = maxActive;
        this.maxWait = maxWait;
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        this.validationQuery = validationQuery;
        this.testWhileIdle = testWhileIdle;
        this.testOnBorrow = testOnBorrow;
        this.testOnReturn = testOnReturn;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInitialSize(Long initialSize) {
        this.initialSize = initialSize;
    }

    public void setMinIdle(Long minIdle) {
        this.minIdle = minIdle;
    }

    public void setMaxActive(Long maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxWait(Long maxWait) {
        this.maxWait = maxWait;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getInitialSize() {
        return initialSize;
    }

    public Long getMinIdle() {
        return minIdle;
    }

    public Long getMaxActive() {
        return maxActive;
    }

    public Long getMaxWait() {
        return maxWait;
    }

    public Long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public Long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    @Override
    public String toString() {
        return "DataSourceBean{" +
                "beanName='" + beanName + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", initialSize=" + initialSize +
                ", minIdle=" + minIdle +
                ", maxActive=" + maxActive +
                ", maxWait=" + maxWait +
                ", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis +
                ", minEvictableIdleTimeMillis=" + minEvictableIdleTimeMillis +
                ", validationQuery='" + validationQuery + '\'' +
                ", testWhileIdle=" + testWhileIdle +
                ", testOnBorrow=" + testOnBorrow +
                ", testOnReturn=" + testOnReturn +
                '}';
    }
}
