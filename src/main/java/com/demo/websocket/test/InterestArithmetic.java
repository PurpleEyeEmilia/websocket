package com.demo.websocket.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/28 15:29:15:29
 * @Description: 有趣的算法
 */
public class InterestArithmetic {

    public static void main(String[] args) {
        //题目：一副从1到n的牌，每次从牌堆顶取一张放桌子上，再取一张放牌堆底，直到手上没牌，最后桌子上的牌是从1到n有序，设计程序，输入n，输出牌堆的顺序数组。
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入牌的数量：");
            n = scanner.nextInt();

            if (n <= 0) {
                return;
            }

            Queue<Integer> queue = new ArrayBlockingQueue<>(n);
            for (int i = 1; i <= n; i++) {
                queue.add(i);
            }

            List<Integer> tempList = getTempList(queue);

            int[] resultList = getResult(tempList);
            System.out.print("结果为：");
            for (Integer integer : resultList) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    private static int[] getResult(List<Integer> tempList) {
        int[] resultList = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            Integer value = tempList.get(i);
            resultList[value - 1] = i + 1;
        }
        return resultList;
    }

    private static List<Integer> getTempList(Queue<Integer> queue) {
        List<Integer> tempList = new ArrayList<>();
        while (queue.size() != 0) {
            tempList.add(queue.poll());
            if(queue.size() != 0) {
                queue.add(queue.poll());
            }
            /*if (list.size() == 1) {
                tempList.add(list.get(0));
                list.remove(0);
            } else {
                tempList.add(list.get(0));
                list.remove(0);
                Integer integer = list.get(0);
                list.remove(0);
                list.add(integer);
            }*/
        }
        return tempList;
    }


}
