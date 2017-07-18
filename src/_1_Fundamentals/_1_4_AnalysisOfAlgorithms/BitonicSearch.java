package _1_Fundamentals._1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * creative problems
 * 1.4.20
 * 在双调数组中查找值
 * arguments:src\_1_Fundamentals\_1_4_AnalysisOfAlgorithms\bitonic.txt 109
 */
public class BitonicSearch {
    public static int indexOf(int[] a, int key){
        // 查找数组中最大值得下标,以此为分界查找
        int max = BitonicMax.max(a, 0, a.length-1);

        // 在前半部分查找
        int[] increasing = Arrays.copyOfRange(a, 0, max);
        int index = BinarySearch.indexOf(increasing, key);
        if (index > -1) return index;

        // 在后边部分查找
        int[] decreasing = Arrays.copyOfRange(a, max, a.length-1);
        index = indexOfByDecreasingOrder(decreasing, key);
        if (index > -1) return max + index;

        return -1;
    }

    // 降序数组中的二分法
    public static int indexOfByDecreasingOrder(int[] a, int key){
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key > a[mid]) hi = mid - 1;
            else if (key < a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        int key = Integer.parseInt(args[1]);
        int[] a = in.readAllInts();
        StdOut.println(indexOf(a, key));
    }
}
