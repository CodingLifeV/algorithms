package com.mytest.algorithm.search;

/**
 * 基于线性探测的符号表
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key, Value> {

    private int M = 16;//符号表大小
    private int N;//符号表中键值对总数
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[])   new Object[M];
        vals = (Value[]) new Object[M];
    }

    //调整数组大小,保证散列表的使用率永远都不会超过1/2
    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++)
            if (keys[i] != null) t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0xfffffff) % M;
    }

    public void put(Key key, Value val) {
        if (N >= M/2) resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i+1)%M )
            if (keys[i].equals(key)) { vals[i] = val; return; }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i+1)%M )
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }

    public void  delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i+1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i+1) % M;
        while (keys[i] != null) {
            Key   keyToRedo   = keys[i];
            Value valueToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valueToRedo);
            i = (i+1) % M;
        }
        N--;
        //调整数组大小,使其使用率在1/8到1/2之间
        if (N > 0 && N == M/8) resize(M/2);
    }

    private boolean contains(Key key) {
        /*for (int i = 0; i < keys.length; i++) {
            if (key.equals(keys[i]))
                return true;
        }*/
        int i = hash(key);
        if (keys[i] != null) return true;
        return false;
    }

}










