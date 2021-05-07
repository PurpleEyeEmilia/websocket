package com.demo.websocket.practice.datastructure;

/**
 * 链表
 */
public class Node {

    private int data;

    private Node next;

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
        Node node0 = new Node(0);
        Node temp = node0;
        for (int i = 1; i <= 10; i++) {
            Node node = new Node(i);
            temp.setNext(node);
            temp = node;
        }

        temp = node0;
        while (node0 != null) {
            System.out.println(node0.getData());
            node0 = node0.getNext();
        }

        //遍历法翻转
//        node0 = forEach(temp);

        //递归法翻转
        node0 = reverseNode(temp);

        System.out.println("===========================");
        while (node0 != null) {
            System.out.println(node0.getData());
            node0 = node0.getNext();
        }
    }

    private static Node reverseNode(Node node0) {
        if (node0 == null || node0.getNext() == null) {
            return node0;
        }

        Node head = reverseNode(node0.next);
        node0.getNext().setNext(node0);
        node0.setNext(null);
        return head;
    }

    private static Node forEach(Node node0) {
        if (node0 == null) {
            return node0;
        }

        Node pre = node0;
        Node cur = node0.next;
        Node tem;

        while (cur != null) {
            tem = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = tem;
        }

        node0.setNext(null);
        return pre;
    }
}
