package com.demo.websocket.practice;

public class Kmp {

    public static void main(String[] args) {
        String src = "BBC ABCDABCABCDABCDABDAE";
        String pattern = "ABCDABDA";
        int[] next = getKmpNext(pattern);
        int kmp = kmp(src, pattern, next);
        System.out.println(kmp);
    }

    private static int kmp(String src, String pattern, int[] next) {
        for (int i = 0, j = 0; i < src.length(); i++) {
            while (j > 0 && src.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (src.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    private static int[] getKmpNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        
        return next;
    }

}
