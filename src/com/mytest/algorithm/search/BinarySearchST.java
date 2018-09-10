package com.mytest.algorithm.search;

/**
 * @author wyj
 *  基于有序数组的二分查找
 */

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity) {
        keys = (Key[])new Comparable[capacity];
        vals = (Value[])new Comparable[capacity];
    }
    public int size(){
        return N;
    }
    //二分查找
    public int rank(Key key) {
         int lo = 0, hi = N-1;
         while(lo <= hi) {
             int mid = lo + (hi-lo)/2;
             int cmd = key.compareTo(keys[mid]);
             if      (cmd < 0) hi = mid-1;
             else if (cmd > 0) lo = mid+1;
             else return mid;
         }
          return lo;//插入位置，之后的数据向后移动一位
    }
    public int rank(Key key, int lo, int hi) {
        if(hi < lo) return lo;
        int mid = lo + (hi-lo) / 2;
        int cmd = key.compareTo(keys[mid]);
        if (cmd < 0) return rank(key, lo, mid-1);
        else if (cmd > 0) return rank(key, mid+1, hi);
        else return mid;
    }
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if(i < N && key.compareTo(keys[i]) == 0) return vals[i];
        else return null;
    }
    private boolean isEmpty() {
        return size() == 0 ;
    }
    public void put(Key key, Value value) {
        int i = rank(key);
        /*if(i < N && key.compareTo(keys[i]) == 0) vals[i] = value;
        //i在中间
        else if(i < N && key.compareTo(keys[i]) != 0) {
            for(int j = N-1; j >= i; j--) {
                keys[j+1] = keys[j];
                vals[j+1] = vals[j];
            }
        }
        //i在尾部
        else { keys[N] = key; vals[N] = value; N++; }*///把elseif和else二种情况合并
        if(i < N && key.compareTo(keys[i]) == 0) { vals[i] = value; return; }
        for (int j = N-1; j >= i; j--) {//测试
            keys[j+1] = keys[j]; vals[j+1] = vals[j];
        }
        //另一种for()循环写法
        /*for (int j = N; j > i; j--) {
            keys[j] = keys[j-1]; vals[j] = vals[j-1];
        }*/
        keys[i] = key;
        vals[i] = value;
        N++;
    }
    public Key min() {
        return keys[0];
    }
    public Key max() {
        return keys[N-1];
    }
    public Key select(int k) {
        return keys[k];
    }
    //大于等于key的最小键
    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }
    //小于等于key的最大键
    public Key floor(Key key) {
        return key;
    }
    public Key delete(Key key) {
        int i = rank(key);
        if(i < N && key.compareTo(keys[i]) == 0) {
            if (i != N-1) {//不在链表尾部，需要把[i+1,...,N-1]元素前移一位
                for (int j = i; j < N-1; j++) {
                    keys[j] = keys[j+1]; vals[j] = vals[j+1];
                }
            } else { //在链表尾部，不需要前移移元素
                keys[i] = null; vals[i] = null;
            }
            N--;
            return key;
        }
        else return null;
    }
}
