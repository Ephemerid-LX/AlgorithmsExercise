package _2_Sorting._2_3_Quicksort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/**
 * 快速排序算法
 *
 * 使用:
 *  1.java MergeBU < tiny.text
 *    注意包名和文件夹位置
 *  2.控制输入:
 *   M E R G E S O R T E X A M P L E
 *   最后回车后以 ctrl+d(windows)结束
 *
 */
public class Quick {

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);// 消除对输入的依赖，避免最糟糕的情况
        sort(a, 0, a.length-1);
    }

    // 快速排序算法
    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1,hi);
    }

    // 切分
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo;// 指针，从左往右扫描
        int j = hi + 1; //指针，从右往左扫描
        Comparable v = a[lo]; //切分元素
        while (true) {
            // 寻找大于等于 v 的元素，记下i
            while (less(a[++i], v))
                if (i == hi) break;
            // 寻找小于等于 v 的元素，记下j
            while (less(v, a[--j]))
                if (j == lo) break;
            // i和j相遇后跳出循环
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    // 比较
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    // 交换元素位置
    private static void exch(Comparable[] a,int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // 打印数组
    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i]+" ");
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        sort(a);
        show(a);
    }
}
