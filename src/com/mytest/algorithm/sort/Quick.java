package com.mytest.algorithm.sort;

import com.mytest.algorithm.util.Util;

/**
 * 
 * @author wangyujiang
 * 快速排序
 * 思想：切分数组，直到二个子数组都有序时整个数组也就自然有序了
 */
public class Quick {
	//切分
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi+1;
		Comparable v = a[lo];
		while(true) {
			while(Util.less(a[++i], v));
			while(Util.less(v, a[--j]));
			if(i >= j) break;//该语句必须放在这里，不能放在while(true)第一行中
			Util.exch(a, i, j);
		}
		Util.exch(a, lo, j);
		return j;
	}
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length-1);
	}
	private static void sort(Comparable[] a, int lo, int hi) {
		if(lo >= hi) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
}
