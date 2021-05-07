package com.demo.websocket.practice.datastructure;

/**
 * 二叉树
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

        TreeNode node0 = new TreeNode(4);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(7);

        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(5);

        TreeNode node5 = new TreeNode(8);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(6);

        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(6);

        node0.setLeft(node1);
        node0.setRight(node2);

        node1.setLeft(node3);
        node1.setRight(node4);

        node4.setLeft(node8);
        node4.setRight(node9);

        node2.setRight(node5);
        node5.setRight(node6);
        node6.setLeft(node7);


        //求最大树深
        int maxHigh = getMaxHigh(node0);

        //求最小树深
        int minHigh = getMinHigh(node0);

        //求两个节点的最小公共祖先
        TreeNode parent = getCommonParent(node0, node3, node9);

        //序列化
        String serializeNode = serializeNode(node0);

        //反序列化
        TreeNode treeNode = deserializeNode(serializeNode);

        System.out.println("最大树深:" + maxHigh);
        System.out.println("最小树深:" + minHigh);
        System.out.println("两个节点的最小公共祖先:" + parent.getVal());
        System.out.println("序列化:" + serializeNode);
        System.out.println("反序列化:" + treeNode.getVal());
    }

    private static TreeNode deserializeNode(String serializeNode) {
        if(serializeNode == null || serializeNode.length() <= 0) {
            return null;
        }

        return deserialize(serializeNode.split("!"));
    }

    public static int index = 0;

    private static TreeNode deserialize(String[] split) {
        if("#".equals(split[index])){
            index++;
            return null;
        }

        TreeNode treeNode = new TreeNode();
        treeNode.setVal(Integer.valueOf(split[index]));
        index++;
        treeNode.left = deserialize(split);
        treeNode.right = deserialize(split);
        return treeNode;
    }

    private static String serializeNode(TreeNode node0) {
        StringBuilder sb = new StringBuilder();
        if (node0 == null) {
            return null;
        }
        serialize(node0, sb);
        return sb.toString();
    }

    private static void serialize(TreeNode node0, StringBuilder sb) {
        if (node0 == null) {
            sb.append("#!");
            return;
        }

        sb.append(node0.val + "!");
        serialize(node0.left, sb);
        serialize(node0.right, sb);
    }

    private static TreeNode getCommonParent(TreeNode node0, TreeNode node3, TreeNode node9) {
        if (findNode(node0.left, node3)) {
            if (findNode(node0.right, node9)) {
                return node0;
            } else {
                return getCommonParent(node0.left, node3, node9);
            }
        } else {
            if (findNode(node0.left, node9)) {
                return node0;
            } else {
                return getCommonParent(node0.right, node3, node9);
            }
        }
    }

    private static boolean findNode(TreeNode left, TreeNode node3) {
        if (left == null || node3 == null) {
            return false;
        }

        if (left == node3) {
            return true;
        }

        boolean flag = findNode(left.left, node3);
        if (!flag) {
            flag = findNode(left.right, node3);
        }

        return flag;
    }

    private static int getMinHigh(TreeNode node0) {
        if (node0 == null) {
            return 0;
        }

        return getMIn(node0);
    }

    private static int getMIn(TreeNode node0) {
        if (node0 == null) {
            return Integer.MAX_VALUE;
        }

        if (node0.left == null && node0.right == null) {
            return 1;
        }

        return Math.min(getMIn(node0.left), getMIn(node0.right)) + 1;
    }

    private static int getMaxHigh(TreeNode node0) {
        if (node0 == null) {
            return 0;
        }
        int left = getMaxHigh(node0.left);
        int right = getMaxHigh(node0.right);
        return Math.max(left, right) + 1;
    }
}
