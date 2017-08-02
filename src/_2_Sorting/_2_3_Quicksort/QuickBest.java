package _2_Sorting._2_3_Quicksort;

import edu.princeton.cs.algs4.StdOut;
/**
 * creative problem 2.3.16
 *
 * quick算法最优的情况：
 *     所有的 j 取的都是下标中位数
 *
 ******************************************************************************
 *  Compilation:  javac QuickBest.java
 *  Execution:    java QuickBest n
 *  Dependencies: StdOut.java
 *
 *  Generate a best-case input of size n for standard quicksort.
 *
 *  % java QuickBest 3
 *  BAC
 *
 *  % java QuickBest 7
 *  DACBFEG
 *
 *  % java QuickBest 15
 *  HACBFEGDLIKJNMO
 *
 ******************************************************************************
 */
public class QuickBest {
    private static void best(int[] a, int lo, int hi){
        for (int i = lo; i <= hi; i++)
            assert a[i] == i;

        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        best(a, lo, mid-1);
        best(a, mid+1, hi);
        exch(a, lo, mid);
    }

    public static int[] best(int n){
        int[] a = new int[n];//改数组用来存储 下标的排序
        for (int i = 0; i < n; i++)
            a[i] = i;
        best(a, 0, n-1);
        return a;
    }

    // 交换 a[i] 和 a[j] 区别
    private static void exch(int[] a, int j, int i){
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int n = Integer.parseInt(args[0]);
        int[] a = best(n);
        for (int i = 0; i < n; i++)
            StdOut.print(alphabet.charAt(a[i]));
        StdOut.println();
    }
}
