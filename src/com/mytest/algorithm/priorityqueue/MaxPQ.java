package com.mytest.algorithm.priorityqueue;

import com.mytest.algorithm.util.Util;

public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq; //存储堆
	private int N = 0; //存储在pq[1,...,N]中
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j])<0;
	}
	private void exch(int i, int j) {
		Key k = pq[i];
		pq[i] = pq[j];
		pq[j] = k;
	}
	public MaxPQ(int maxN) {
		pq = (Key[])new Comparable[maxN+1];
	}
	public MaxPQ(Comparable[] com) {
		pq = (Key[])new Comparable[com.length+1];
		for(int i = 0; i < com.length; i++) {
			pq[i+1] =  (Key)com[i];
		}
	}
	public boolean isEmpty() {
		return N == 0;
	}
	public int size() {
		return N;
	}
	public void insert(Key v) {
		pq[++N] = v;
		swim(N);
	}
	public Key delMax() {
		Key max = pq[1];
		exch(1, N--);
		pq[N++] = null;//防止游离
		sink(1);
		return max;
	}
	private void  swim(int k) {
		/*while(k/2 >= 1) {//待验证
			if (less(k/2, k)) {
				exch(k, k/2);
				k = k/2;
			} else {
				break;
			}
		}*/
		while(k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}
	private void sink(int k) {
		while(2*k <= N) {//是可以等于N的
			int j = 2*k;
			if(j < N && less(j, j+1)) j++;//需要判断j<N,如果j=N,pq[j+1]数组下标越界
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
	/*public void sort() {
		for (int i = 1; i <= pq.length ; i++) {
			swim(i);
		}
	}*///错误写法

}
