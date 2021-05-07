package com.demo.websocket.practice.spring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Resource;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/5/6 14:30
 * @Desc
 */
@Setter
@Getter
public class UserServiceImpl implements UserService {

    @Resource
    private MessageService messageService;

}
