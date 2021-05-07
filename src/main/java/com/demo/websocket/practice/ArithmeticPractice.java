package com.demo.websocket.practice;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/28 15:29:15:29
 * @Description: 算法题目
 */
public class ArithmeticPractice {

    public static void main(String[] args) {
//        System.out.println(question1());
//        question2();
//        System.out.println(question3());
//        System.out.println(question4());
//        System.out.println(question5());
        System.out.println(question6());
    }

    private static String question6() {
        String patten = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s1 = "zzzzzzzzzzzzz";
        String s2 = "1";

        return getResult6(patten, s1, s2);
    }

    private static String getResult6(String patten, String s1, String s2) {
        BigInteger s1To10 = new BigInteger("0");
        s1To10 = to10(patten, s1.toUpperCase(), s1To10);

        BigInteger s2To10 = new BigInteger("0");
        s2To10 = to10(patten, s2.toUpperCase(), s2To10);

        BigInteger sumTo10 = s1To10.add(s2To10);

        StringBuilder sb = new StringBuilder();
        while (sumTo10.compareTo(new BigInteger("0")) > 0) {
            BigInteger m = new BigInteger("36");
            BigInteger[] bigIntegers = sumTo10.divideAndRemainder(m);
            sb.append(patten.charAt(bigIntegers[1].intValue()));
            sumTo10 = bigIntegers[0];
        }

        return sb.reverse().toString();
    }

    private static BigInteger to10(String patten, String s1, BigInteger s1To10) {
        for (int i = s1.length() - 1, j = 0; i >= 0; i--, j++) {
            int index = patten.indexOf(s1.charAt(i));
            BigInteger indexBbigInteger = new BigInteger(String.valueOf(index));
            BigInteger powBigInteger = new BigInteger(String.valueOf(new Double(Math.pow(36, j)).longValue()));
            s1To10 = s1To10.add(indexBbigInteger.multiply(powBigInteger));
        }
        return s1To10;
    }

    private static BigInteger question5() {
        //迭代法求斐波那契数列
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入斐波那契数：");
        int n = scanner.nextInt();
        if (n <= 0) {
            return new BigInteger("0");
        }

        if (n == 1) {
            return new BigInteger("1");
        }

        BigInteger x = new BigInteger("0");
        BigInteger y = new BigInteger("1");
        BigInteger sn = new BigInteger("0");

        for (int i = 0; i < n - 1; i++) {
            sn = x.add(y);
            x = y;
            y = sn;
        }

        return sn;
    }

    private static int question4() {
        //递归法求斐波那契数列
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入斐波那契数：");
        int n = scanner.nextInt();
        return add(n);
    }

    private static int add(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return add(n - 1) + add(n - 2);
        }
    }

    private static int question3() {
        //求n层汉诺塔的最小移动次数
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入汉诺塔数：");
        int n = scanner.nextInt();
        if (n <= 0) {
            return 0;
        }
        return move(n) - 1;
    }

    private static int move(int n) {
        if (n <= 0) {
            return 1;
        } else if (n == 1) {
            return 2;
        } else {
            return 2 * move(--n);
        }
    }

    private static void question2() {
        //编写一个程序，有1, 2, 3, 4个数字，能组成多少个互不相同且无重复数字的四位数？都是多少？
        int num = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int m = 1; m <= 4; m++) {
                    for (int n = 1; n <= 4; n++) {
                        if (i != j && i != m && i != n && j != m && j != n && m != n) {
                            num++;
                            System.out.println("" + i + j + m + n);
                        }
                    }
                }
            }
        }
        System.out.println("共有：" + num + "个");
    }

    private static long question1() {
        //编写一个程序，输入n,求n！（用递归的方式实现）。
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入阶乘数：");
        int n = scanner.nextInt();
        if (n <= 0) {
            return 0;
        }
        return compute(n);
    }

    private static long compute(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return n * compute(--n);
        }
    }
}
