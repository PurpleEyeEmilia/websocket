package com.demo.websocket.practice.leetcode;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/1 15:35
 * @Desc
 */
public class Day1 {

    /**
     * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
     * <p>
     * 实现 NumArray 类：
     * <p>
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
     * <p>
     */
    public static void main(String[] args) {

        int[] array = new int[]{1, -2, 2, 6, 8, 0};

        System.out.println(sumRange(array, 2, 4));

    }

    private static int sumRange(int[] array, int i, int j) {
        if (array == null) {
            return 0;
        }
        if (i > array.length || j < 0 || j < i) {
            return 0;
        }

        int result = 0;
        for (; i <= j; i++) {
            result += array[i];
        }
        return result;
    }



}
