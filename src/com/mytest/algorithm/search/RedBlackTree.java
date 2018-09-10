package com.mytest.algorithm.search;

/**
 * 红黑树
 * @param <Key>
 * @param <Value>
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private static boolean RED   = true;
    private static boolean BLACK = false;

    private Node root;
    private class Node {
        Key key; //键
        Value val; //值
        Node left, right; //左右子树
        int N; //这颗子树中的结点总数
        boolean color; //由父节点指向它的链接的颜色

        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRED(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    //左旋转，红色右链接转化为左链接
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;

        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private int size(Node x) {
        if(x == null) return 0;
        else return x.N;
    }

    public void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val = val;

        if (isRED(x.right) && !isRED(x.left)) x = rotateLeft(x);
        if (isRED(x.left) && isRED(x.left.left)) x = rotateRight(x);
        if (isRED(x.left) && isRED(x.right)) flipColors(x);

        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

}













