package com.mytest.algorithm.sort;
import com.mytest.algorithm.util.*;
/**
 * 
 * @author wangyujiang
 * 选择排序Selection
 */
public class Selection {
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;//
			for(int j = i; j < N; j++) {
				if (Util.less(a[j], a[min])) {
					min = j ;
				}
			}
			Util.exch(a, min, i);
		}

	}
}


















