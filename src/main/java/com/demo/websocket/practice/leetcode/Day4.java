package com.demo.websocket.practice.leetcode;

import com.demo.websocket.practice.leetcode.data.ListNode;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/12 14:25
 * @Desc
 */
public class Day4 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();

        ListNode l3 = l1;
        ListNode l4 = l2;

        for (int i = 0; i < 7; i++) {
            l1.val = 9;
            if (i == 6) {
                break;
            }
            l1.next = new ListNode();
            l1 = l1.next;
        }

        for (int i = 0; i < 4; i++) {
            l2.val = 9;
            if (i == 3) {
                break;
            }
            l2.next = new ListNode();
            l2 = l2.next;
        }

        ListNode listNode = addTwoNumbers(l3, l4);
        System.out.print("[");
        while (listNode != null) {
            System.out.print(listNode.val + ",");
            listNode = listNode.next;
        }

        System.out.print("]");
        System.out.println();
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode result1 = result;
        int j = 0;
        while (l1 != null || l2 != null) {
            int num;
            if (l1 == null) {
                num = l2.val + j;
            } else if (l2 == null) {
                num = l1.val + j;
            } else {
                num = l1.val + l2.val + j;
            }

            if (num - 10 >= 0) {
                j = 1;
                result.val = num - 10;

            } else {
                j = 0;
                result.val = num;

            }

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }

            if (l1 == null && l2 == null) {
                if (j != 0) {
                    result.next = new ListNode();
                    result = result.next;
                    result.val = 1;
                }
                break;
            } else {
                result.next = new ListNode();
                result = result.next;
            }
        }
        return result1;
    }

}
