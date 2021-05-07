package com.demo.websocket.practice.leetcode;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/17 13:54
 * @Desc
 */
public class Day5 {

    public static void main(String[] args) {
        System.out.println(convert("abcdefgh", 4));
    }

    public static String convert(String s, int numRows) {
        int length = s.length();
        int i = (length + 1) / 2;
        int j = (length + 1) % 2;
        String[][] result;
        if (j == 0) {
            //能整除
            result = new String[i][numRows];
        } else {
            //有问题
            result = new String[i + j][numRows];
        }

        //右指针
        int x = 0;

        //下指针
        int y = 0;

        //true时y递增，false，y递减，x递增
        boolean f = true;
        for (int a = 0; a < s.length(); a++) {
            String val = String.valueOf(s.charAt(a));
            if (y < numRows && f) {
                result[x][y++] = val;
            } else {
                if (y == numRows) {
                    y--;
                }
                x++;
                y--;
                result[x][y] = val;
                if (y == 0) {
                    f = true;
                    y++;
                } else {
                    f = false;
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int c = 0, d = 0; c < result.length; c++) {
            if(d >= result[c].length) {
                continue;
            }
            if (result[c][d] != null) {
                sb.append(result[c][d]);
            }
            if (c == result.length - 1) {
                c = -1;
                d++;
            }
        }
        return sb.toString();
    }

}
