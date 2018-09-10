package com.mytest.algorithm.sort;

import com.mytest.algorithm.util.Util;

import java.util.concurrent.Callable;

/**
 * 
 * @author wangyujiang
 * 三向切分快速排序
 */
public class Quick3way {
	private static void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi) return;
		Comparable v = a[lo];
		int i = lo+1, lt = lo, gt = hi;//lt赋值为lo，赋值为i出错
		while(i <= gt) { //a[lt...i]部分为=v的部分
			int result = a[i].compareTo(v);
			if(result < 0) Util.exch(a, i++, lt++);
			else if(result > 0) Util.exch(a, i, gt--);
			else i++;
		}
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	}
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length-1);
	}
}
