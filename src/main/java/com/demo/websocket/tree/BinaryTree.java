package com.demo.websocket.tree;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/9/20 10:29:10:29
 * @Description: 二叉树
 */
public class BinaryTree<T extends Comparable> implements Tree<T> {

    public BinaryTree<T> left;

    public BinaryTree<T> right;

    public T data;

    public BinaryTree(BinaryTree<T> left, BinaryTree<T> right, T data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BinaryTree(T data) {
        this.left = null;
        this.right = null;
        this.data = data;
    }

    /**
     * 判断是否是叶子节点
     *
     * @return
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public String preOrder() {
        return null;
    }

    @Override
    public String inOrder() {
        return null;
    }

    @Override
    public String postOrder() {
        return null;
    }

    @Override
    public String levelOrder() {
        return null;
    }

    @Override
    public void insert(T data) {

    }

    @Override
    public void remove(T data) {

    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public T findMax() {
        return null;
    }

    @Override
    public Tree<T> findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public void clear() {

    }
}