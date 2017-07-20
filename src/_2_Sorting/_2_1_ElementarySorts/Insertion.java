package _2_Sorting._2_1_ElementarySorts;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 插入排序
 */
public class Insertion {
    /**
     * 升序排序
     */
    public static void sort(Comparable[] a){
        int n = a.length;
        for (int i = 1; i < n; i++) {
            //将 a[i] 插入到a[i-1]、a[i-2]、a[i-3]...之中
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    /**
     * 比较,v < w 则返回true
     */
    public static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /**
     * 交换数组元素
     * @param a 数组
     * @param i 下标 i
     * @param j 下标 j
     */
    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 在单行中打印数组
     * @param a 数组
     */
    public static void show(Comparable[] a){
        // 在单行中答应数组
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    /**
     * 测试数组元素是否有序
     * @param a 数组
     * @return 有序则true
     */
    public static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public static void main(String[] args){
        // 测试用例
        In in = new In();
        String[] a = in.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
