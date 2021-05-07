package com.demo.websocket.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/31 15:48:15:48
 * @Description:
 */
public class TestMap {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        System.out.println(map.get("1"));
    }


}
