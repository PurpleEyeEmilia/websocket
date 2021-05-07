package com.demo.websocket.test;

import org.apache.commons.lang3.StringUtils;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/20 16:45:16:45
 * @Description: KMP算法
 */
public class Kmp {

    public static int kmp(String src, String patten) {
        if (StringUtils.isBlank(src) || StringUtils.isBlank(patten)) {
            return -1;
        }

        int[] next = kmpNext(patten);
        return kmpMatch(src, patten, next);
    }

    private static int[] kmpNext(String patten) {
        int[] next = new int[patten.length()];
        next[0] = 0;

        for (int i = 1, j = 0; i < patten.length(); i++) {
            while (j > 0 && patten.charAt(i) != patten.charAt(j)) {
                j = next[--j];
            }

            if (patten.charAt(i) == patten.charAt(j)) {
                ++j;
            }

            next[i] = j;
        }

        return next;
    }

    private static int kmpMatch(String src, String patten, int[] next) {
        for (int i = 0, j = 0; i < src.length(); i++) {
            while (j > 0 && src.charAt(i) != patten.charAt(j)) {
                j = next[--j];
            }

            if (src.charAt(i) == patten.charAt(j)) {
                ++j;
            }

            if (j == patten.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String src = "BBC ABCDABCABCDABCDABDAE";
        String pattern = "ABCDABDA";
        System.out.println(kmp(src, pattern));
    }
}
