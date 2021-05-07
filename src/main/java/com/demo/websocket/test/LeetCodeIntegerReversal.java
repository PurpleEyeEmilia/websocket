package com.demo.websocket.test;

/**
 * @author pengnian
 * @version V1.0
 * @date 2020/1/9 11:30
 * @Desc
 */
public class LeetCodeIntegerReversal {

    public static void main(String[] args) {
        //给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
        //示例 1:
        //
        //输入: 123
        //输出: 321
        // 示例 2:
        //
        //输入: -123
        //输出: -321
        //示例 3:
        //
        //输入: 120
        //输出: 21
        //注意: 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
        int i = 123;
        int result = integerReversal(i);
        System.out.println(result);
    }

    private static int integerReversal(int i) {
        String s = String.valueOf(i);
        if (s.charAt(0) == '-') {
            s = s.substring(1);
        }

        StringBuilder stringBuilder = new StringBuilder(s);
        StringBuilder reverse = stringBuilder.reverse();


        return 0;
    }
}
