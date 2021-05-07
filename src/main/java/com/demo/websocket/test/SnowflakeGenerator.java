package com.demo.websocket.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/15 11:20:11:20
 * @Description: 雪花id生成器
 */
public class SnowflakeGenerator {

    /**
     * 0 00000000000000000000000000000000000000000 00000 00000 000000000000
     */

    public SnowflakeGenerator() {
    }

    public SnowflakeGenerator(long dataCenterId, long machineId) {
        if (dataCenterId > DATA_CENTER_ID_MAX_VALUE) {
            throw new IllegalArgumentException("IllegalArgument:" + dataCenterId);
        }

        if (machineId > MACHINE_ID_MAX_VALUE) {
            throw new IllegalArgumentException("IllegalArgument:" + dataCenterId);
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 起始的时间戳 2018-05-09
     */
    private static final long START_TIMESTAMP = 1525795200000L;

    /**
     * 最高位：符号位：0，表示正数
     */
    private static final long FIRST_BIT = 0L;

    /**
     * 数据中心：5位
     */
    private static final long DATA_CENTER_ID_BIT = 5L;

    /**
     * 机器标识位：5位
     */
    private static final long MACHINE_ID_BIT = 5L;

    /**
     * 自增序列位：12位
     */
    private static final long SEQUENCE_BIT = 12L;


    /**
     * 时间戳左移位
     */
    private static final long TIMESTAMP_LEFT_BIT = DATA_CENTER_ID_BIT + MACHINE_ID_BIT + SEQUENCE_BIT;

    /**
     * 数据中心左移位
     */
    private static final long DATA_CENTER_LEFT_BIT = MACHINE_ID_BIT + SEQUENCE_BIT;

    /**
     *
     */
    private static final long MACHINE_LEFT_BIT = SEQUENCE_BIT;

    /**
     * 数据中心最大值
     */
    private static final long DATA_CENTER_ID_MAX_VALUE = ~(-1L << DATA_CENTER_ID_BIT);

    /**
     * 机器标识位最大值
     */
    private static final long MACHINE_ID_MAX_VALUE = ~(-1L << MACHINE_ID_BIT);

    /**
     * 自增序列位最大值
     */
    private static final long SEQUENCE_MAX_VALUE = ~(-1L << SEQUENCE_BIT);

    /**
     * 数据中心id 0~31
     */
    private long dataCenterId;

    /**
     * 机器id 0~31
     */
    private long machineId;

    /**
     * 自增序列
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastTimestamp = -1L;

    public synchronized Long nextId() {
        long timestamp = getSysCurMillis();

        if (timestamp - START_TIMESTAMP < 0L) {
            throw new IllegalArgumentException("IllegalArgument:" + START_TIMESTAMP);
        }

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        if (timestamp == lastTimestamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & SEQUENCE_MAX_VALUE;
            if (sequence == 0L) {
                timestamp = getNextMillisecond();
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return (timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_BIT | dataCenterId << DATA_CENTER_LEFT_BIT | machineId << MACHINE_LEFT_BIT | sequence;
    }

    private long getSysCurMillis() {
        return System.currentTimeMillis();
    }

    private long getNextMillisecond() {
        long sysCurMillis = getSysCurMillis();
        while (sysCurMillis <= lastTimestamp) {
            sysCurMillis = getSysCurMillis();
        }
        return sysCurMillis;
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                generateData(0L, 0L);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                generateData(0L, 1L);
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                generateData(1L, 1L);
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                generateData(0L, 2L);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    private static void generateData(long dataCenterId, long machineId) {
        SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator(dataCenterId, machineId);
        List<Long> kids = new Vector<>();
        for (int i = 0; i < 100000; i++) {
            kids.add(snowflakeGenerator.nextId());
        }
        Integer integer = insertData(kids);
        System.out.println(integer);
    }

    private static Integer insertData(List<Long> kids) {
        Connection connection = null;
        Statement statement = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.获取jdbc连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iflytek?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false&amp;allowMultiQueries=true&amp;serverTimezone=GMT%2b8",
                    "root", "123456");
            //开启事务
            connection.setAutoCommit(false);

            //3.创建statement对象
            statement = connection.createStatement();

            StringBuilder sql = new StringBuilder("insert user (kid, name, phone) values ");
            for (int i = 0; i < kids.size(); i++) {
                if (i == kids.size() - 1) {
                    sql.append("(")
                            .append(kids.get(i)).append(", ")
                            .append("\'").append("name").append(i).append("\'").append(", ")
                            .append("\'").append("phone").append(i).append("\'")
                            .append(")");
                } else {
                    sql.append("(")
                            .append(kids.get(i)).append(", ")
                            .append("\'").append("name").append(i).append("\'").append(", ")
                            .append("\'").append("phone").append(i).append("\'")
                            .append("), ");
                }
            }
            int i = statement.executeUpdate(sql.toString());
            connection.commit();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
