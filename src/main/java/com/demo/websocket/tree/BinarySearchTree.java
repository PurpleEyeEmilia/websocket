package com.demo.websocket.tree;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/9/21 09:22:9:22
 * @Description:
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T> {

    protected BinaryTree<T> root;

    public BinarySearchTree() {
        root = null;
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
        if (data == null) {
            return;
        }
        root = insert(data, root);
    }

    private BinaryTree<T> insert(T data, BinaryTree<T> node) {
        if (node == null) {
            return new BinaryTree<T>(data);
        }

        int result = data.compareTo(node.data);
        if (result > 0) {
            insert(data, node.right);
        } else if (result < 0) {
            insert(data, node.left);
        }

        return node;
    }

    @Override
    public void remove(T data) {
        Class<Boolean> type = Boolean.TYPE;
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
