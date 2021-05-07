package com.demo.websocket.practice.proxy.jdk.api;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/20 10:48
 * @Desc
 */
public interface SmsService {
    /**
     * 发送消息
     *
     * @param msg
     * @return
     */
    String sendMsg(String msg);

}
