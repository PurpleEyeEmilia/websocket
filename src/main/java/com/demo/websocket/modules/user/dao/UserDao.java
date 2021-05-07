package com.demo.websocket.modules.user.dao;

import com.demo.websocket.modules.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Repository
public interface UserDao {

    User getById(@Param("id") Long id);

    List<User> userList();

    Integer insertUser(User user);

}
