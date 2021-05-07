package com.demo.websocket.practice;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/11/5 15:19:15:19
 * @Description:  递归法和迭代法求斐波那契数列
 */
public class Test1 {

    public static void main(String[] args) {
        int time = 6;
        System.out.println(getResult(time));
    }

    private static long getResult(int time) {
        /*if (time <= 0) {
            return 0;
        } else if (time == 1) {
            return 1;
        } else {
            return getResult(time - 1) + getResult(time - 2);
        }*/

        long[] arr = new long[time + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= time; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
            System.out.println(arr[i]);
        }

        return arr[time];
    }
}
