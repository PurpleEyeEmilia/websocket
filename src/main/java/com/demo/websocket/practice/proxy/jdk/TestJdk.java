package com.demo.websocket.practice.proxy.jdk;

import com.demo.websocket.practice.proxy.jdk.api.SmsService;

import java.lang.reflect.Proxy;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/20 10:46
 * @Desc
 */
public class TestJdk {

    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.sendMsg("宝贝，我爱你!");
    }

}
