package com.demo.websocket.test;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/16 14:41:14:41
 * @Description: 排序复习
 */
public class Sort {

    public static void main(String[] args) {
//        int[] array = {
//                4, 8, 5, 7, 1, 3, 6, 2
//        };
//        int[] array = {
//                5, 2, 4, 6, 2, 1, 3, 7
//        };

        int[] array = {-1, 0, 1, 2, -1, -4};
//        int[] array = new int[1000000];
//        for (int i = 1; i <= array.length; i++) {
//            if (i % 2 != 0) {
//                array[(i + 1) / 2 - 1] = i;
//            } else {
//                array[(array.length + i) / 2 - 1] = i;
//            }
//        }

        long currentTimeMillis = System.currentTimeMillis();

        /**
         * 冒泡排序
         */
//        bubbleSort(array);

        /**
         * 选择排序
         */
//        selectSort(array);

        /**
         * 快速排序
         */
        quick(array);

        /**
         * 直接插入排序
         */
//        insertSort(array);

        /**
         * 希尔排序
         */
//        shellSort(array);

        /**
         * 堆排序
         */
//        heapSort(array);

        /**
         * 归并排序
         */
//        mergeSort(array);

        long l = System.currentTimeMillis() - currentTimeMillis;
        int count = 0;
        for (Integer integer : array) {
            System.out.print(integer + " ");
            count++;
            if (count != 0 && count % 50 == 0) {
                System.out.println();
            }
        }

        System.out.println("排序耗时：" + l + "ms");
    }

    private static void mergeSort(int[] array) {
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
    }

    private static void mergeSort(int[] array, int start, int end, int[] temp) {
        if (start < end) {
            int mid = (start + end) >> 1;
            mergeSort(array, start, mid, temp);
            mergeSort(array, mid + 1, end, temp);
            merge(array, start, mid, end, temp);
        }
    }

    private static void merge(int[] array, int start, int mid, int end, int[] temp) {
        int i = start;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= end) {
            if (array[i] < array[j]) {
                temp[t++] = array[i++];
            } else {
                temp[t++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[t++] = array[i++];
        }

        while (j <= end) {
            temp[t++] = array[j++];
        }

        t = 0;
        while (start <= end) {
            array[start++] = temp[t++];
        }

    }

    private static void heapSort(int[] array) {

    }

    private static void selectSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    private static void bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    private static void shellSort(int[] arr) {
        int length = arr.length;
        while (length != 0) {
            length = length / 2;
            for (int x = 0; x < length; x++) {
                for (int i = x + length; i < arr.length; i += length) {
                    int insertNum = arr[i];
                    int j = i - length;
                    while (j >= 0 && arr[j] > insertNum) {
                        arr[j + length] = arr[j];
                        j -= length;
                    }
                    arr[j + length] = insertNum;
                }
            }
        }
    }

    private static void insertSort(int[] array) {
        int length = array.length;
        int insertNum;
        for (int i = 1; i < length; i++) {
            insertNum = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > insertNum) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = insertNum;
        }
    }

    private static void quick(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int index = getIndex(array, start, end);
            quickSort(array, start, index - 1);
            quickSort(array, index + 1, end);
        }
    }

    private static int getIndex(int[] array, int start, int end) {
        int i = start, j = end, position = array[start];
        int temp;
        while (i < j) {

            while (array[j] >= position && j > i) {
                j--;
            }

            while (array[i] <= position && j > i) {
                i++;
            }

            if (i < j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        array[start] = array[i];
        array[i] = position;
        return i;
    }


    //    private static void quick(int[] array) {
//        if (array == null || array.length <= 0) {
//            return;
//        }
//        quickSort(array, 0, array.length - 1);
//    }
//
//    /**
//     * [7, 5, 8, 2, 9, 3, 6, 8, 4, 9]
//     */
//    private static void quickSort(int[] arr, int start, int end) {
//        int level = arr[start];
//        int i = start, j = end;
//        int temp;
//        do {
//            while (i <= end && arr[i] < level) {
//                i++;
//            }
//
//            while (j >= start && arr[j] > level) {
//                j--;
//            }
//
//            if (i <= j && arr[i] >= arr[j]) {
//                temp = arr[i];
//                arr[i] = arr[j];
//                arr[j] = temp;
//                i++;
//                j--;
//            }
//        } while (i < j);
//
//        if (i < end) {
//            quickSort(arr, i, end);
//        }
//
//        if (j > start) {
//            quickSort(arr, start, j);
//        }
//    }




}
