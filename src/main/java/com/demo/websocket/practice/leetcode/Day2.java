package com.demo.websocket.practice.leetcode;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/1 15:57
 * @Desc
 */
public class Day2 {
    /**
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{7, 6, 4, 3, 1, 11};
        System.out.println(getMaxMoney(array));
    }

    private static int getMaxMoney(int[] array) {
        int[] temp = new int[10000];
        int j = 0;
        for (int i = 1; i < array.length; i++) {

            int m = i;

            while (m != 0) {
                final int mon2 = array[i];
                final int mon1 = array[--m];
                temp[j++] = mon2 - mon1;
            }

        }

        int result = temp[0] <= 0 ? 0 : temp[0];

        for (int i = 1; i < temp.length; i++) {
            if (temp[i] > result) {
                result = temp[i];
            }
        }

        return result;
    }

}
