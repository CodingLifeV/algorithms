package com.mytest.algorithm.priorityqueue;

import com.mytest.algorithm.util.Util;

/**
 * @author wyj
 * 堆排序
 */
public class StackSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        //构造一个有序堆,只需扫描数组中的一半元素
        for (int k = N/2 - 1; k >= 0; k--) {
            sink(a, k, N);
        }
        //将一个堆进行从小到大排序
        while(N > 0) {
            exch(a, 0, --N);
            sink(a, 0, N);
        }

    }
    //下沉元素
    private static void sink(Comparable[] a, int k, int N) {
        while(2*k+1 <= N-1) {
            int j = 2*k+1;
            if (j < N-1 && Util.less(a[j],a[j+1])) j++;
            if (!Util.less(a[k], a[j])) break;
            exch(a, j, k);
        }
    }
    //交换元素
    private static void exch(Comparable[] a, int j, int k) {
        Comparable tem = a[j];
        a[j] = a[k];
        a[k] = tem;
    }


}
