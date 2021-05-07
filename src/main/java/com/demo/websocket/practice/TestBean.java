package com.demo.websocket.practice;

public class TestBean {

    String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    StringBuilder result = null;

    public String add(String numA, String numB) {
        result = new StringBuilder();
        //将两个相加的数字转换成十进制
        int numa = this.threeSixTo10(numA);
        int numb = this.threeSixTo10(numB);
        //算出十进制总和
        int sum = numa + numb;
        return this.tenTo36(sum);
    }

    public int threeSixTo10(String num) {
        //将36位数字转化成字符数组
        char[] chs = num.toCharArray();
        //定义一个参数用来存放所有值
        int number = 0;
        for (int i = 0; i < chs.length; i++) {
            //取出36位数字的每个位上的数字
            char ch = chs[i];
            //找到该数字所在的str中的位置
            int index = str.indexOf((ch + "").toUpperCase());
            //循环计算每个位置的十进制值
            for (int j = 0; j < chs.length - i - 1; j++) {
                index = index * 36;
            }
            number = number + index;
        }
        return number;
    }

    public String tenTo36(int num) {

        int flag = num % 36;
        if (flag <= 1) {
            result.append(str.charAt(num));
        } else {
            result.append(str.charAt(flag));
            this.tenTo36(num % 36);
        }
        return result.toString().toLowerCase();
    }

    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        testBean.add("zzz", "1");
    }
}
