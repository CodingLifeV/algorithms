package com.mytest.algorithm.sort;

import com.mytest.*;
import com.mytest.algorithm.util.Util;
/**
 * @author wangyujiang
 * 基于插入排序的希尔排序
 */
public class Shell {
	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while(h < N/3) h = 3*h+1;
		while(h>=1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j > 0 && Util.less(a[j], a[j-h]); j -= h ) {
					Util.exch(a, j, j-h);
				}
			}
			h = h/3;
		}
	}
}

