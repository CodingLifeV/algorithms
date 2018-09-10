package com.mytest.algorithm.util;

import com.mytest.algorithm.priorityqueue.MaxPQ;
import com.mytest.algorithm.priorityqueue.StackSort;
import com.mytest.algorithm.sort.*;
/**
 * 
 * @author wangyujiang
 * 测试类
 */
public class test {
	public static void main(String[] args) {
		Comparable a[] = {10,21,9,1,23};
		
		//Selection.sort(a);
		//Insertion.sort(a);
		//Merge.sort(a, 0, a.length-1);
		//Shell.sort(a);
		//MergeBU.sort(a);
		//Quick.sort(a);
		//Quick3way.sort(a);
		StackSort.sort(a);
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
		System.out.println(Util.isSorted(a));
	}
}
