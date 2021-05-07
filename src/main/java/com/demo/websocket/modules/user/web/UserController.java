package com.demo.websocket.modules.user.web;

import com.demo.websocket.modules.user.entity.User;
import com.demo.websocket.modules.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Description:
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(Long kid, Model model) {
        User user1 = userService.getById(kid);
        model.addAttribute("user", user1);
        model.addAttribute("kid", kid);
        return "user_message";
    }

    @RequestMapping("/message")
    @ResponseBody
    public String sendMessage(User user) {
        userService.sendMessage(user);
        return "1";
    }

    @RequestMapping("user/list")
    public String userList(Model model){
        List<User> list = userService.userList();
        model.addAttribute("userList", list);
        return "";
    }

    @RequestMapping("/send/attachment")
    @ResponseBody
    public String attachment(User user){
        userService.sendAttachment(user);
        return "success";
    }

    @RequestMapping("lock")
    @ResponseBody
    public Integer lock(User user) {
        return userService.insert(user);
    }

    @RequestMapping("dataSource")
    @ResponseBody
    public Integer dataSource(User user) {
        return userService.switchDataSource(user);
    }

}
