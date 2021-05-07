package com.demo.websocket.practice.proxy.cglib;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/20 10:37
 * @Desc
 */
public class TestCglib {

    public static void main(String[] args) {
        SmsService smsService = CglibProxyFactory.getProxyObject(SmsService.class);
        smsService.sendMsg("我爱你，宝贝");
    }

}
