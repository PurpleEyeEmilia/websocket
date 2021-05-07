package com.demo.websocket.modules.user.service;

import com.demo.websocket.modules.user.entity.User;

import java.util.List;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public interface UserService {

    User getById(Long id);

    void sendMessage(User user);

    List<User> userList();

    void sendAttachment(User user);

    Integer insert(User user);

    Integer switchDataSource(User user);
}
