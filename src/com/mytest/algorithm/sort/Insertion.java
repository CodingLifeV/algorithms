package com.mytest.algorithm.sort;

import com.mytest.algorithm.util.Util;

/**
 * 
 * @author wangyujiang
 * 插入排序Insertion
 */
public class Insertion {
	public static void sort(Comparable[] a) {
		int N = a.length;
		for(int i = 0; i < N; i++)//i=0或者i=1都可行
			for(int j = i; j > 0 && Util.less(a[j],a[j-1]); j-- )
				Util.exch(a,j, j-1);
	}
}
