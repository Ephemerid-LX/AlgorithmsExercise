package _1_Fundamentals._1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *  Compilation:  javac BitonicMax.java
 *  Execution:    java BitonicMax N
 *  Dependencies: StdOut.java
 *
 *  Find the maximum in a bitonic array (strictly increasing, then strictly
 *  decreasing) of size N in log N time.
 *
 *  % java BitonicMax N
 *
 ******************************************************************************/
public class BitonicMax {
    // 创建一个大小为n的双调数组
    public static int[] bitonic(int n){
        int mid = StdRandom.uniform(n);
        int[] a = new int[n];
        for (int i = 1; i < mid; i++) {
            a[i] = a[i-1] + 1 + StdRandom.uniform(9);
        }

        if (mid > 0) a[mid] = a[mid - 1] + StdRandom.uniform(10) - 5;

        for (int i = mid + 1; i < n; i++) {
            a[i] = a[i-1] - 1 - StdRandom.uniform(9);
        }

        for (int i = 0; i < n; i++){
            StdOut.println(a[i]);
        }
        return a;
    }

    // 在双调数组的子数组[lo...hi]中找最大值
    public static int max (int[] a, int lo, int hi){
        if (hi == lo) return hi;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] < a[mid + 1]) return max(a, mid+1, hi);
        if (a[mid] > a[mid + 1]) return max(a, lo, mid);
        else return mid;
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int[] a = bitonic(n);
        StdOut.printf("Max = " + a[max(a, 0, n-1)]);
    }
}
