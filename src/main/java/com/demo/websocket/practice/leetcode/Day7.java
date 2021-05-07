package com.demo.websocket.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/4/2 15:21
 * @Desc
 */
public class Day7 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>(nums.length);
        if (nums.length <= 2) {
            return resultList;
        }

        quickSort(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                return resultList;
            }

            int x = i + 1;
            int y = nums.length - 1;

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            while (x < y) {
                int sum = nums[i] + nums[x] + nums[y];
                if (sum == 0) {
                    List<Integer> array = new ArrayList<>(3);
                    array.add(nums[i]);
                    array.add(nums[x]);
                    array.add(nums[y]);
                    resultList.add(array);
                    x++;
                    y--;
                } else if (sum > 0) {
                    y--;
                } else {
                    x++;
                }
            }
        }
        return resultList;
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int position = position(nums, start, end);
            quickSort(nums, position + 1, end);
            quickSort(nums, start, position -1);
        }
    }

    private static int position(int[] nums, int start, int end) {
        int i = start, j = end, pos = nums[start];
        int temp;
        while (i < j) {
            while (nums[j] >= pos && j > i) {
                j--;
            }

            while (nums[i] <= pos && j > i) {
                i++;
            }

            if (i < j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        //设置基准点
        nums[start] = nums[i];
        nums[i] = pos;
        return i;
    }

}
