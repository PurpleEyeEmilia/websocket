package com.demo.websocket.practice;

import java.util.Stack;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/12/25 15:41:15:41
 * @Description: 计算下列表达式的结果
 */
public class Test2 {

    public static void main(String[] args) {
        String equation = "9+(3-1)*3+10/2";

        getResult(equation);
    }

    private static void getResult(String equation) {
        Stack<String> number = new Stack<>();
        Stack<String> symbol = new Stack<>();

        for (int i = 0; i < equation.length(); i++) {
            if (Character.isDigit(equation.charAt(i))) {
                i = judge(equation, number, symbol, i);
            }
        }
    }

    private static int judge(String equation, Stack<String> number, Stack<String> symbol, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(equation.charAt(i));
        i++;
        if (i < equation.length()) {
            if(Character.isDigit(equation.charAt(i))) {
                return judge(equation, number, symbol, i);
            } else {
                number.push(stringBuilder.toString());
                symbol.push(String.valueOf(equation.charAt(i)));
            }
        }
        return i;
    }


}
