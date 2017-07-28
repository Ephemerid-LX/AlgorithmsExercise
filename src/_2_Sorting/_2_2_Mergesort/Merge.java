package _2_Sorting._2_2_Mergesort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 归并排序
 *
 * 使用:
 * java Merge < tiny.txt
 * 注意包名
 *
 * 或者
 *   控制台输入:
 *   M E R G E S O R T E X A M P L E
 *   回车----> ctrl+d(Windows)
 */
public class Merge {

    private static Comparable[] aux;// 辅助数组

    //自顶向下的归并排序
    public static void sort(Comparable[] a){
        int n = a.length;
        aux = new Comparable[n];
        sort(a, 0, n-1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, lo, mid); // 将左半边排序
        sort(a, mid+1, hi); // 将右半边排序
        merge(a, lo, mid, hi); // 归并结果
    }

    /**
     * 原地归并
     * a[lo...min] 和 a[min+1...hi]是有序的
     * @param a
     * @param lo
     * @param min
     * @param hi
     */
    public static void merge(Comparable[] a, int lo, int min, int hi){

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        int i = lo;
        int j = min + 1;
        for (int k = lo; k <= hi; k++){
            if (i > min) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    // 若v < w，则返回true
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i]+" ");
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        show(a);
    }
}
