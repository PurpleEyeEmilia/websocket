package com.demo.websocket.practice;

public class Demo {

    static String str = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {

        //第一题 输出
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 4, 8, 12, 6, 2, 10};

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};


        //求输入数组
        int[] reverse = reverse(arr);

        for (int i = 0; i < reverse.length; i++) {
            System.out.print(reverse[i] + " ");
        }

        System.out.println("");

        //第二题，两个36进制的数相加，得到结果
        System.out.println(add("zzz", "1"));
    }

    private static int[] reverse(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[arr[i] - 1] = arr[i];
        }
        return result;
    }

    public static String add(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int numbera = numberToTen(a);
        int numberb = numberToTen(b);
        int sum = numbera + numberb;
        return conversion(sum, sb);
    }

    private static String conversion(int sum, StringBuilder result) {
        int flag = sum % 36;
        if (sum != 0) {
            result.append(str.charAt(flag));
            sum = sum / 36;
            conversion(sum, result);
        }
        return result.reverse().toString();

    }

    private static int numberToTen(String a) {
        char[] charArray = a.toCharArray();
        int number = 0;
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            int index = str.indexOf((ch));
            for (int j = 0; j < charArray.length - i - 1; j++) {
                index = index * 36;
            }
            number = number + index;
        }
        return number;
    }
}
