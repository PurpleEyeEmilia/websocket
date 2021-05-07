package com.demo.websocket.modules.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.websocket.annotation.DistributedLock;
import com.demo.websocket.modules.user.entity.User;
import com.demo.websocket.modules.user.dao.UserDao;
import com.demo.websocket.modules.user.service.UserService;
import com.demo.websocket.socket.MessageWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.*;
import java.util.List;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageWebSocketHandler messageWebSocketHandler;

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public User getById(Long id) {
        User user = userDao.getById(id);
        return user;
    }

    @Override
    public void sendMessage(User user) {
        messageWebSocketHandler.sendMessage(new TextMessage(JSON.toJSONString(user)), String.valueOf(user.getKid()));
    }

    @Override
    public List<User> userList() {
        return userDao.userList();
    }

    @Override
    public void sendAttachment(User user) {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            File file = new File("D:\\xixi.txt");
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);

            byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] bytes = new byte[1024];
            int b;
            while ((b = bufferedInputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, b);
            }
            byteArrayOutputStream.flush();

            messageWebSocketHandler.sendAttachment(new BinaryMessage(byteArrayOutputStream.toByteArray()), String.valueOf(user.getKid()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    @DistributedLock(lockKeyByBeanNames = {"name", "phone"}, expiredTime = 500)
    @Transactional(rollbackFor = Exception.class)
    public Integer insert(User user) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        jedis.set("xxx", "我去");
        Integer integer = userDao.insertUser(user);
        jedis.close();
        return integer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer switchDataSource(User user) {
        Integer integer = userDao.insertUser(user);
        return integer;
    }
}
