package com.demo.websocket.practice;

public class Test {

    public static void main(String[] args) {
        String src = "BBC ABCDABCABCDABCDABDE";
        String pattern = "ABCDABD";

        int[] arr = getNext(pattern);
        int index = kmp(src, pattern, arr);
        System.out.println(index);
    }

    private static int[] getNext(String pattern) {
        int[] arr = new int[pattern.length()];
        arr[0] = 0;
        for (int i = 1, j = 0; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = arr[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            arr[i] = j;
        }

        return arr;
    }

    private static int kmp(String src, String pattern, int[] arr) {
        for (int i = 0, j = 0; i < src.length(); i++) {
            while (j > 0 && src.charAt(i) != pattern.charAt(j)) {
                j = arr[j - 1];
            }

            if (src.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            if(j == pattern.length()) {
                return i - j + 1;
            }

        }

        return -1;
    }

}
