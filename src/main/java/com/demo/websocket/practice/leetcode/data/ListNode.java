package com.demo.websocket.practice.leetcode.data;

/**
 * @author pengnian
 * @version V1.0
 * @date 2021/3/12 14:26
 * @Desc
 */
public class ListNode {
    /**
     *
     */
   public int val;

    /**
     *
     */
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
