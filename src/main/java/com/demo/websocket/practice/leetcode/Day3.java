package com.demo.websocket.practice.leetcode;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/1 16:49
 * @Desc
 */
public class Day3 {

    /**
     * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入："ab-cd"
     * 输出："dc-ba"
     * 示例 2：
     * <p>
     * 输入："a-bC-dEf-ghIj"
     * 输出："j-Ih-gfE-dCba"
     * 示例 3：
     * <p>
     * 输入："Test1ng-Leet=code-Q!"
     * 输出："Qedo1ct-eeLg=ntse-T!"
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "Test1ng-Leet=code-Q!";
        System.out.println(reversal(s));
    }

    private static String reversal(String s) {
        char[] temp = new char[s.length()];
        char[] temp1 = new char[1];
        char[] result = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i))) {
                temp[i] = s.charAt(i);
            }
        }


        final String s1 = String.valueOf(temp1[0]);
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            final char c = s.charAt(i);
            String s3 = String.valueOf(temp[i]);
            if (!s1.equals(s3)) {
                result[i] = temp[i];
            }

            String s2 = String.valueOf(result[i]);

            while (!s1.equals(s2)) {
                j++;
                s2 = String.valueOf(result[j]);
            }

            result[j++] = c;
        }

        return String.valueOf(result);
    }

}
