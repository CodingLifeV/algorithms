package com.mytest.algorithm.search;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;//以该结点为根的子树中的结点总数
        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    //整颗树size
    public int size() {
        return size(root);
    }
    //子树size
    private int size(Node x) {
        if(x == null) return 0;
        else return x.N;
    }
    public Value get(Key key) {
        return get(root, key);
    }
    private Value get(Node x, Key key) {
        if (x == null) return null;

        int cmd = key.compareTo(x.key);
        if      (cmd < 0) return get(x.left, key);
        else if (cmd > 0) return get(x.right, key);
        else return x.val;
    }
    public void put(Key key, Value value) {
        //测试
        /*if (root == null) System.out.println("root==null");
        else System.out.println("root="+root.key);*/
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value val) {
        //如果key存在与以x为根结点的子树中则更新它的值
        //否则插入到适当位置
        if (x == null) return new Node(key, val, 1);
        int cmd = key.compareTo(x.key);

        //待测试,添加return，没有了x.left赋值，测试结果不可行，并没有将结点链接上
        /*if      (cmd < 0)  return put(x.left, key, val);
        else if (cmd > 0)  return put(x.right, key ,val);*/
        if      (cmd < 0)  x.left = put(x.left, key, val);
        else if (cmd > 0)  x.right = put(x.right, key ,val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;//结点计数器更新值
        return x;
    }
    public Key min() {
        return min(root).key;
    }
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }
    public Key max() {
        return max(root).key;
    }
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    //返回小于等于key的最大键
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t == null) return x;
        else           return t;
    }

    //返回排名为k的键，排名从0开始
    public Key select(int k) {
        return select(root, k).key;
    }
    private Node select(Node x, int k){
        System.out.println(x.N);
        if (x == null || x.N-1 < k)  return null;
        int t = size(x.left);
        if      (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else            return x;
    }

    //对给定的键进行排名
    public int rank(Key key){
        return rank(root, key);
    }
    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)     return size(x.left);
        else if (cmp < 0) return rank(x.left, key);
            //else            return rank(x.right, key)+size(x) ;//错误写法size(x)为整个树的节点数，包含了右子树
        else              return rank(x.right, key)+size(x.left) +1;//rank是select的逆方法
    }


    public void deleteMin() {
        root = deleteMin(root);
    }
    //删除最小的键
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    //删除给定的键
    public void delete(Key key) {
        root = delete(root, key);
    }
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null)  return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(x.right);//删除x结点后用它的后继结点填补它的位置
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> Keys() {
        return Keys(min(), max());
    }
    public Iterable<Key> Keys(Key lo, Key hi) {
        //Queue<Key> queue = new LinkedList<Key>();
        Queue<Key> queue = new LinkedList<Key>();
        keys(root,queue, lo, hi);
        return queue;
    }

    //范围查找
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <=0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(7,1);
        bst.put(5,1);
        bst.put(4,2);
        bst.put(6,1);
        bst.put(10,1);
        bst.delete(5);
        //System.out.println(bst.floor(6));
        // System.out.println(bst.floor(11));
        //System.out.println("key=3，val=" + bst.get(3) + ";"
        //   + "min=" + bst.min()+ ";"+"max="+ bst.max()
        //);
        //System.out.println(bst.select(4));
        //System.out.println(bst.rank(10));
        System.out.println(bst.get(5));
        System.out.println(bst.root.left.key);
    }
}
