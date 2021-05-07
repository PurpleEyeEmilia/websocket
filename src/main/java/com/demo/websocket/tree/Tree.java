package com.demo.websocket.tree;

/**
 * Copyright (c) 2017-2018  Company LTD.
 * All rights reserved.
 *
 * @Author: pengnian
 * @Date: 2018/9/20 10:06:10:06
 * @Description: 树
 */
public interface Tree<T extends Comparable> {
    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 节点数
     *
     * @return
     */
    int size();

    /**
     * 树的高度，或者叫深度，即节点的最大层次
     *
     * @return
     */
    int height();

    /**
     * 前序遍历，也叫先根次序遍历
     *
     * @return
     */
    String preOrder();

    /**
     * 中序遍历，也叫中根次序遍历
     *
     * @return
     */
    String inOrder();

    /**
     * 后序遍历，也叫后根次序遍历
     *
     * @return
     */
    String postOrder();

    /**
     * 层次遍历
     *
     * @return
     */
    String levelOrder();

    /**
     * 插入数据
     *
     * @param data
     */
    void insert(T data);

    /**
     * 移除数据
     *
     * @param data
     */
    void remove(T data);

    /**
     * 查找最小值
     *
     * @return
     */
    T findMin();

    /**
     * 查找最大值
     *
     * @return
     */
    T findMax();

    /**
     * 根据值找到节点
     *
     * @param data
     * @return
     */
    Tree<T> findNode(T data);

    /**
     * 是否包含某个值
     *
     * @param data
     * @return
     */
    boolean contains(T data);

    /**
     * 清空
     */
    void clear();
}
