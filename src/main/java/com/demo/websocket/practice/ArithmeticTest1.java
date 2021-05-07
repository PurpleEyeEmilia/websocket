package com.demo.websocket.practice;

import com.demo.websocket.practice.datastructure.Node;

/**
 * @author pengnian
 * @version V1.0
 * @date 2020/12/3 14:57
 * @Desc
 */
public class ArithmeticTest1 {


    public static void main(String[] args) {
        //初始化链表
        linkNodeInit(10);
    }

    private static void linkNodeInit(int length) {
        //初始化链表
        Node headNode = new Node(0);
        Node nextNode = headNode;
        for (int i = 1; i <= length; i++) {
            Node node = new Node(i);
            nextNode.setNext(node);
            nextNode = node;
        }

        Node node = headNode;
        //遍历链表
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }

        //链表反转(递归法)
        Node newHeadNode = reversalNode(headNode);

//        //遍历链表
//        while (newHeadNode != null) {
//            System.out.println(newHeadNode.getData());
//            newHeadNode = newHeadNode.getNext();
//        }

        //链表反转(迭代法)
        Node node2 = forEachReversalNode(newHeadNode);

        //链表在值等于7的后面。插入一个节点
        insertNode(node2, 7, new Node(-8));

        //删除节点 -8
        deleteNode(node2, -8);

        //遍历链表
        while (node2 != null) {
            System.out.println(node2.getData());
            node2 = node2.getNext();
        }

    }

    private static void deleteNode(Node node2, int i) {
        if (node2 == null) {
            return;
        }

        Node temp = null;
        while (node2 != null) {
            Node next = node2.getNext();
            if (node2.getData() == i) {

            }
        }
    }

    private static void insertNode(Node node2, int i, Node insertNode) {
        if (node2 == null) {
            return;
        }

        while (node2 != null) {
            Node next = node2.getNext();
            if (node2.getData() == i) {
                node2.setNext(insertNode);
                insertNode.setNext(next);
                return;
            }
            node2 = next;
        }

    }

    private static Node forEachReversalNode(Node newHeadNode) {
        if (newHeadNode == null || newHeadNode.getNext() == null) {
            return newHeadNode;
        }

        Node head = newHeadNode;
        Node next = head.getNext();
        Node temp = null;

        while (next != null) {
            temp = next.getNext();
            next.setNext(head);
            head = next;
            next = temp;
        }

        newHeadNode.setNext(null);
        return head;
    }

    private static Node reversalNode(Node headNode) {
        if (headNode == null || headNode.getNext() == null) {
            return headNode;
        }

        //node为反转后的头结点，返回即可
        Node node = reversalNode(headNode.getNext());
        headNode.getNext().setNext(headNode);
        headNode.setNext(null);
        return node;
    }

}
