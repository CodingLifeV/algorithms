package com.mytest.algorithm.sort;

import com.mytest.algorithm.util.Util;

/**
 * @author wangyujiang
 * 自顶向下的归并排序
 * 将二个子数组排序，通过归并将二个子数组来将整个数组排序
 */
public class Merge {
	//原地归并
	public static  void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid+1;
		Comparable[] aux = new Comparable[a.length];
		//复制新数组
		for(int k=0; k < a.length; k++)
			aux[k] = a[k];

		//将二个子数组aux[lo,...,mid],aux[j,...,hi]归并到数组a中
		for (int k=lo; k <= hi; k++){
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (Util.less(aux[i],aux[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
	}
	public static void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi) return;//排序结束
		int mid = lo + (hi-lo)/2;
		sort(a,lo,mid);//左半边排序
		sort(a,mid+1,hi);//右半边排序
		merge(a,lo,mid,hi);//左右归并
	}
}
















