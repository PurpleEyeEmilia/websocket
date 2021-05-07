package com.demo.websocket.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/23 18:06
 * @Desc 判断是一个数是不是对称数
 */
public class Day6 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(100));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x == 0) {
            return true;
        }

        List<Integer> resList = new ArrayList<>();
        while (x != 0) {
            int y = x % 10;
            x /= 10;
            resList.add(y);
        }

        if (resList.size() == 1) {
            return true;
        }

        if (resList.size() == 2) {
            return resList.get(0).equals(resList.get(1));
        }

        int a = resList.size() % 2;
        int j = a == 0 ? resList.size() / 2 : resList.size() / 2 + 1;
        for (int i = resList.size() / 2 - 1; i >= 0; i--) {
            if (!resList.get(i).equals(resList.get(j))) {
                return false;
            }
            j++;
        }
        return true;
    }
}
