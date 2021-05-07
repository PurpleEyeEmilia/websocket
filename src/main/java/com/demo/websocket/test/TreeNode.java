package com.demo.websocket.test;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/8/23 15:20:15:20
 * @Description: 二叉树
 */
public class TreeNode {

    private int val;

    private TreeNode left;

    private TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(-1);
        TreeNode node5 = new TreeNode(-2);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(6);
        TreeNode node8 = new TreeNode(9);


        root.setLeft(node1);

        node1.setLeft(node4);
        node1.setRight(node6);
        node6.setLeft(node7);
        node6.setRight(node8);

        node4.setLeft(node5);

        root.setRight(node2);
        node2.setRight(node3);

        //求最大数深度
        int max = treeMaxHigh(root);

        //求最小数深度
        int min = treeMinHigh(root);

        //最低公共祖先
        TreeNode parent = getLastCommonParent(root, node5, node8);


        System.out.println("最大数深度：" + max);
        System.out.println("最小数深度：" + min);
        System.out.println("最低公共祖先值：" + parent.getVal());
    }

    private static TreeNode getLastCommonParent(TreeNode root, TreeNode node5, TreeNode node8) {
        if (findNode(root.left, node5)) {
            if (findNode(root.right, node8)) {
                return root;
            } else {
                return getLastCommonParent(root.left, node5, node8);
            }
        } else {
            if (findNode(root.left, node8)) {
                return root;
            } else {
                return getLastCommonParent(root.right, node5, node8);
            }
        }
    }

    private static boolean findNode(TreeNode left, TreeNode node5) {
        if (left == null || node5 == null) {
            return false;
        }

        if (left == node5) {
            return true;
        }

        boolean flag = findNode(left.left, node5);

        if (!flag) {
            flag = findNode(left.right, node5);
        }

        return flag;
    }

    private static int treeMinHigh(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return getMin(root);
    }

    private static int getMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if(root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }

    private static int treeMaxHigh(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = treeMaxHigh(root.left);
        int right = treeMinHigh(root.right);
        return Math.max(left, right) + 1;
    }


}
