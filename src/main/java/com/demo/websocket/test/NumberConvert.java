package com.demo.websocket.test;

import java.math.BigDecimal;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/31 10:56:10:56
 * @Description:
 */
public class NumberConvert {

    private static String str = "0123456789abcdefghijklmnopqrstuvwxyz";

    //将两个36进制的数相加得到新的36进制的数
    public static void main(String[] args) {
        String a = "zzy";
        String b = "1";

        String c = add(a, b);
        System.out.println(c);
    }

    private static String add(String a, String b) {
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        BigDecimal avalue = new BigDecimal("0");
        BigDecimal bvalue = new BigDecimal("0");

        BigDecimal value = getValue(aArray, avalue);

        BigDecimal value1 = getValue(bArray, bvalue);

        BigDecimal cValue = value.add(value1);
        BigDecimal bigDecimal = cValue.setScale(0);
        StringBuilder sb = new StringBuilder();
        while (bigDecimal.compareTo(new BigDecimal("0")) != 0) {
            BigDecimal divisor = new BigDecimal("36");
            BigDecimal[] bigDecimals = bigDecimal.divideAndRemainder(divisor);
            String s = bigDecimals[1].toString();
            if(s.indexOf(".") >= 0) {
               s = s.substring(0, s.indexOf("."));
            }
            char c = str.charAt(Integer.valueOf(s));
            sb.append(c);
            bigDecimal = bigDecimal.divide(divisor, 0, BigDecimal.ROUND_DOWN);
        }
        return sb.reverse().toString();
    }

    private static BigDecimal getValue(char[] bArray, BigDecimal bvalue) {
        for (int i = bArray.length - 1, j = 0; i >= 0; i--, j++) {
            int indexOf = str.indexOf(bArray[i]);
            BigDecimal index = new BigDecimal(indexOf);
            BigDecimal pow = new BigDecimal(String.valueOf(Math.pow(36D, j)));
            bvalue = bvalue.add(index.multiply(pow));
        }
        return bvalue;
    }
}
