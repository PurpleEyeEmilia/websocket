package com.demo.websocket.practice.datastructure;

/**
 * @author pengnian
 * @version V1.0
 * @date 2020/10/13 14:58
 * @Desc 红黑树
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * 根节点
     */
    private RedBlackNode<T> root;

    /**
     * 节点的颜色，红色
     */
    private static final Boolean RED = false;

    /**
     * 节点的颜色，黑色
     */
    private static final Boolean BLACK = true;


    public class RedBlackNode<T extends Comparable<T>> {
        /**
         * 节点值
         */
        private T val;

        /**
         * 颜色
         */
        private Boolean color;

        /**
         * 左节点
         */
        private RedBlackNode<T> left;

        /**
         * 右节点
         */
        private RedBlackNode<T> right;

        /**
         * 父节点
         */
        private RedBlackNode<T> parent;

    }

    public void insert(RedBlackNode<T> node) {
        RedBlackNode<T> x = this.root;
        RedBlackNode<T> y = null;
        int cap;

        while (x != null) {
            y = x;
            cap = node.val.compareTo(x.val);
            if (cap < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y != null) {
            cap = node.val.compareTo(y.val);
            if (cap < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        }

        //设置为红色
        node.color = RED;

        //将他重新修正为一颗红黑树
        insertFix(node);

    }

    private void insertFix(RedBlackNode<T> node) {
        RedBlackNode<T> parent, gParent;
        while ((parent = parentOf(node)) != null && isRed(parent)) {
            gParent = parentOf(parent);
            //父节点是祖父节点的左孩子
            if (parent == gParent.left) {
                //叔叔节点是红色
                RedBlackNode<T> uncle = gParent.right;
                if (uncle != null && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gParent);
                    node = gParent;
                    continue;
                }

                //叔叔节点是黑色，且当前节点是右孩子
                if (parent.right == node) {
                    RedBlackNode<T> temp;
                    //TODO 左旋，这里为什么要旋转？为什么是左旋，而不是右旋？
                    leftRotate(node);
                    temp = parent;
                    parent = node;
                    node = temp;
                }

                //叔叔节点是黑色，且当前节点是左孩子
                setBlack(parent);
                setRed(gParent);
                rightRotate(gParent);

            } else {



            }
        }

    }

    private void rightRotate(RedBlackNode<T> node) {


    }

    /**
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)--           / \                #
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     *
     */
    private void leftRotate(RedBlackNode<T> x) {
        RedBlackNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            this.root = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }

        //
        y.left = x;

        //
        x.parent = y;
    }

    private void setRed(RedBlackNode<T> node) {
        node.color = RED;
    }

    private void setBlack(RedBlackNode<T> node) {
        node.color = BLACK;
    }

    private boolean isRed(RedBlackNode<T> node) {
        return !node.color;
    }

    private RedBlackNode<T> parentOf(RedBlackNode<T> node) {
        return node.parent;
    }
}


