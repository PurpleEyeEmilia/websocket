package com.demo.websocket.practice.proxy.jdk;

import com.demo.websocket.practice.proxy.jdk.api.SmsService;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/20 10:24
 * @Desc
 */
public class SmsServiceImpl implements SmsService {

    @Override
    public String sendMsg(String msg){
        System.out.println("发送消息: " + msg);
        return msg;
    }

}
