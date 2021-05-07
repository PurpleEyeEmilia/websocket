package com.demo.websocket.practice.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/26 10:10
 * @Desc
 */
public class TestIoc {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-test.xml");

        MessageService messageService = applicationContext.getBean(MessageService.class);

        System.out.println(messageService.getMessage());

    }

}
