package _2_Sorting._2_2_Mergesort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 自底向上的归并排序
 * 使用:
 *  1.java MergeBU < tiny.text
 *    注意包名和文件夹位置
 *  2.控制输入:
 *   M E R G E S O R T E X A M P L E
 *   最后回车后以 ctrl+d(windows)结束
 *
 *
 * Data files:   http://algs4.cs.princeton.edu/22merge/tiny.txt
 *               http://algs4.cs.princeton.edu/22merge/words3.txt
 *
 */
public class MergeBU {

    public static void sort(Comparable[] a){
        int n = a.length;
        Comparable[] aux = new Comparable[n];

        // 自底向上归并
        for (int sz = 1; sz < n; sz *= 2) {
            for (int lo = 0; lo < n - sz; lo += sz + sz){
                int mid = lo + sz - 1;
                int hi = Math.min(lo+sz+sz-1, n-1);//最后一个子数组的大小可能小于sz;
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        // 复制元素到辅助数组
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo;//左边从lo开始,到mid结束
        int j = mid + 1;//右边从mid+1开始，到hi结束
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)           a[k] = aux[j++];//左半边一已经比较完
            else if (j > hi)            a[k] = aux[i++]; // 右半边已经比较完
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];//右边<左边，则赋值为右边
            else                        a[k] = aux[i++]; // 否则赋值为左边
        }

    }

    // 数组是否已经排序
    private static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // 比较
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }


    // 打印数组
    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        sort(a);
        show(a);
    }
}
