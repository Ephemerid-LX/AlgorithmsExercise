/******************************************************************************
 *  Compilation:  javac QuickX.java
 *  Execution:    java QuickX < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                http://algs4.cs.princeton.edu/23quicksort/words3.txt
 *
 *  Uses the Bentley-McIlroy 3-way partitioning scheme,
 *  chooses the partitioning element using Tukey's ninther,
 *  and cuts off to insertion sort.
 *
 *  Reference: Engineering a Sort Function by Jon L. Bentley
 *  and M. Douglas McIlroy. Softwae-Practice and Experience,
 *  Vol. 23 (11), 1249-1265 (November 1993).
 *
 ******************************************************************************/
package _2_Sorting._2_3_Quicksort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;


/**
 * creative problem 2.3.22
 */
public class QiuckX {
    // 用来判断使用 插入排序 的情况
    private static final int INSERTION_SORT_CUTOFF = 8;

    // cutoff to median-of-3 partitioning
    private static final int MEDIAN_OF_3_CUTOFF = 40;

    // 这个类不能被实例化
    private QiuckX(){ }

    public static void sort(Comparable[] a){
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        int n = hi - lo + 1;

        // cutoff to insertion sort
        if (n < INSERTION_SORT_CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }

        // todo:下面两个 分支 步骤会使排序算法加快少？
        // use median-of-3 ad partioning element
        else if (n <= MEDIAN_OF_3_CUTOFF) {
            int m = median3(a, lo, lo+n/2, hi);
            exch(a, lo, m);
        }

        // use Tukey ninther as partitioning element
        else  {
            int eps = n/8;
            int mid = lo + n/2;
            int m1 = median3(a, lo, lo+eps, lo+eps+eps);
            int m2 = median3(a, mid-eps, mid, mid+eps);
            int m3 = median3(a, hi-eps-eps, hi-eps, hi);
            int ninther = median3(a, m1, m2, m3);
            exch(a, ninther, lo);
        }

        // Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi+1;
        int p = lo, q = hi+1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i],v))
                if (i == hi)  break;
            while (less(v, a[--j]))
                if (j == lo) break;

            //pointer cross
            if (i == j && eq(a[i], v))
                exch(a, ++p, i);
            if (i >= j) break;

            exch(a, i, j);
            if (eq(a[i], v)) exch(a, ++p, i);
            if (eq(a[j], v)) exch(a, --q, j);

        }

        // 将 =v 的部分移至中间
        i = j+1;
        for (int k = lo; k <= p; k++)
            exch(a, k, j--);
        for (int k = hi; k >= q; k--)
            exch(a, k, i++);

        sort(a, lo, j);
        sort(a, i, hi);
    }

    // sort from a[lo] to a[hi] using insertion sort
    private static void insertionSort(Comparable[] a, int lo, int hi){
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }

    // return the index of the median element among a[i], a[j], a[k]
    private static int median3(Comparable[] a, int i, int j, int k){
        return (less(a[i], a[j])?
                (less(a[j], a[k])? j : less(a[i], a[k])? k : i) :
                (less(a[k], a[j])? j : less(a[k], a[i])? k : i));
    }


    /*****************************************
     * Helper sorting functions
     *****************************************/
    // is v < w?
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static boolean eq(Comparable v, Comparable w){
        return v.compareTo(w) == 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /***********************************************************
     * Check if array id sorted - useful for debugging.
     ***********************************************************/
    private static boolean isSorted(Comparable[] a){
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i]+" ");
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        QiuckX.sort(a);
        assert isSorted(a);
        show(a);
    }
}

