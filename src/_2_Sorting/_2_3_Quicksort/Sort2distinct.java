package _2_Sorting._2_3_Quicksort;

import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 2.3.5
 */
public class Sort2distinct {

    public static void sort(Comparable[] a){
        int lt = 0, gt = a.length-1;
        int i = 0;
        while (i <= gt) {
            int cmp = a[i].compareTo(a[lt]);
            if      (cmp < 0) exch(a, lt++, i++);// 小元素往左移动
            else if (cmp > 0) exch(a, i, gt--);// 大元素往后移动
            else              i++;
        }
    }

    // 交换 a[i] 和 a[j]
    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args){
        // 参数
        String s = args[0];
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i,i+1);

        // 排序打印
        sort(a);
        for (int i = 0; i < n; i++)
            StdOut.print(a[i]);
        StdOut.println();
    }
}
