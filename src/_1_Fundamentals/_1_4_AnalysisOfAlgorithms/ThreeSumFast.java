package _1_Fundamentals._1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 计算和为0的三元组的数目
 */
public class ThreeSumFast {
   public static int count(int[] a){
       Arrays.sort(a);
       int n = a.length;
       int cnt = 0;
       for (int i = 0; i < n; i++)
           for (int j = i+1; j < n; j++)
               if (BinarySearch.indexOf(a, -(a[i]+a[j])) > i)
                   cnt++;
       return cnt;
   }

   public static void main(String[] args){
       In in = new In(args[0]);
       int[] a = in.readAllInts();
       StdOut.println(count(a));
   }
}
