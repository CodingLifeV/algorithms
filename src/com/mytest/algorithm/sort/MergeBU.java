package com.mytest.algorithm.sort;

import com.mytest.algorithm.util.Util;
/**
 * 
 * @author wangyujiang
 * 自顶向上的归并排序
 * 多次遍历整个数组，根据整个数组大小进行两两归并
 */

public class MergeBU {
	//原地归并
	public static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid+1;
		Comparable[] aux = new Comparable[a.length];
		for(int k = lo; k <= hi; k++) {//复制数组
			aux[k] = a[k];
		}
		for(int k = lo; k <= hi; k++) {
			if(i>mid) {//左半边用尽
				a[k] = aux[j++];
			} else if(j>hi) {//右半边用尽
				a[k] = aux[i++];
			} else if(Util.less(aux[i], aux[j])) {
				a[k] = aux[i++];
			} else{
				a[k] = aux[j++];
			}
		}
	}
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int sz = 1; sz < N; sz = sz+sz) {//进行每一轮归并，下一轮子数组的大小会翻倍
			for(int lo = 0; lo < N-sz; lo += sz+sz) { //确定归并子数组lo的位置
				merge(a, lo, lo+sz-1, lo+sz+sz-1);//进行归并
			}
		}
	}
	
}
