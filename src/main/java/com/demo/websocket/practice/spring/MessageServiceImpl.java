package com.demo.websocket.practice.spring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Resource;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/26 10:08
 * @Desc
 */
@Setter
@Getter
public class MessageServiceImpl implements MessageService {

    private UserService userService;

    @Override
    public String getMessage() {
        return "hello World!";
    }


}
