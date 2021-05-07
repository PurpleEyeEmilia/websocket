package com.demo.websocket.practice.proxy.cglib;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/20 10:24
 * @Desc
 */
public class SmsService {

    public String sendMsg(String msg){
        System.out.println("发送消息: " + msg);
        return msg;
    }

}
