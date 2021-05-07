package com.demo.websocket.practice;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author pengnian
 * @version V1.0
 * @date 2019/6/28 16:52
 * @Desc
 */
public class NumberCirclePractice {

    /**
     * 右
     */
    private static final String RIGHT = "right";

    /**
     * 下
     */
    private static final String DOWN = "down";

    /**
     * 左
     */
    private static final String LEFT = "left";

    /**
     * 上
     */
    private static final String UP = "up";

    /**
     * 给定一个数n，打印回环数
     * <p>
     * n = 4
     * <p>
     * 1  2  3  4
     * 12 13 14 5
     * 11 16 15 6
     * 10 9  8  7
     */
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("exit~~~");
                return;
            }

            int[][] arr = new int[n][n];
            int x = 0, y = 0, a = 1, sum = n * n;
            String curPoint = RIGHT;

            while (a <= sum) {
                switch (curPoint) {
                    case RIGHT:
                        if (y < n && arr[x][y] == 0) {
                            arr[x][y++] = a++;
                            break;
                        } else {
                            curPoint = getNextPoint(curPoint);
                            x++;
                            y--;
                            break;
                        }
                    case DOWN:
                        if (x < n && arr[x][y] == 0) {
                            arr[x++][y] = a++;
                            break;
                        } else {
                            curPoint = getNextPoint(curPoint);
                            x--;
                            y--;
                            break;
                        }
                    case LEFT:
                        if (y >= 0 && arr[x][y] == 0) {
                            arr[x][y--] = a++;
                            break;
                        } else {
                            curPoint = getNextPoint(curPoint);
                            x--;
                            y++;
                            break;
                        }
                    case UP:
                        if (x >= 0 && arr[x][y] == 0) {
                            arr[x--][y] = a++;
                            break;
                        } else {
                            curPoint = getNextPoint(curPoint);
                            x++;
                            y++;
                            n--;
                            break;
                        }
                    default:
                        break;
                }
            }



            for (int i = 0; i < arr.length; i++) {
                for (int num : arr[i]) {
//                    System.out.print(num + " ");
                    if (i == 0) {
                        System.out.print(num + "  ");
                    } else {
                        System.out.print(num + " ");
                    }
                }

                System.out.println();
            }
        }
    }

    private static String getNextPoint(String curPoint) {
        if (StringUtils.isBlank(curPoint)) {
            return curPoint;
        }

        switch (curPoint) {
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case UP:
                return RIGHT;
            default:
                return "";
        }
    }
}
