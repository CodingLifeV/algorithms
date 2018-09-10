package com.mytest.algorithm.search;

/**
 * 基于拉链法的散列表
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {

    private int N;//键值对总数
    private int M;//散列表大小
    private SequentialSearch<Key, Value>[] st;//存放链表对象的数组

    public SeparateChainingHashST() {
        this(997);//this用法
    }
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearch<Key, Value>[]) new SequentialSearch[M];//泛型数组初始化
        for (int i = 0; i < st.length; i++) {
            st[i] = new SequentialSearch();
        }
    }

    //产生一个0到M-1的整数
    private int hash(Key key) {
        return (key.hashCode() & 0xfffffff) % M;
    }

    public Value get(Key key) {
        return  (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }
}
