package com.demo.websocket.test;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/15 09:57:9:57
 * @Description: 单链表的反转
 */
public class Node {

    private int data;

    private Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        Node tem = head;

        for (int i = 1; i < 10; i++) {
            Node node = new Node(i);
            tem.setNext(node);
            tem = node;
        }

        tem = head;
        while (tem != null) {
            System.out.println(tem.getData());
            tem = tem.getNext();
        }

        //递归反转
//        head = reverse(head);

        //遍历反转
        head = forEach(head);

        while (head != null) {
            System.out.println(head.getData());
            head = head.getNext();
        }

    }

    private static Node forEach(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node pre = head;
        Node cur = head.getNext();
        Node temp;

        while (cur != null) {
            temp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = temp;
        }
        head.setNext(null);
        return pre;
    }

    private static Node reverse(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node node = reverse(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return node;
    }
}
