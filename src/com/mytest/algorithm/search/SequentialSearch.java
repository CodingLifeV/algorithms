package com.mytest.algorithm.search;

/**
 * @author wyj
 *  基于无序链表的顺序查找(低效查找)
 */
public class SequentialSearch<Key,Value> {
    private Node first;//链表首结点
    private class Node { //结点定义
        Key key;
        Value value;
        Node next;
        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    public Value get(Key key){//查找给定的键，返回其值
        for (Node node = first; node != null; node = node.next)
            if (node.key.equals(key)) return node.value;
        return null;
    }
    public void put(Key key, Value value) {
        //查找，找到则更新，否则在表中新建结点
        for (Node no = first; no != null; no = no.next) {
            if (key.equals(no.key)) {
                no.value = value;
                return;
            }
            //else first = node;
        }
        first = new Node(key, value, first);//新建结点，插入到链表的开头
    }
    public  static void main(String[] args) {
    }
}
