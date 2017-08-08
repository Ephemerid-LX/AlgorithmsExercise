/******************************************************************************
 *  Compilation:  javac Distinct.java
 *  Execution:    java Distinct m n trials
 *  Dependencies: StdOut.java StdRandom.java
 *
 *  Perform specified number of trials of the following experiment:
 *  generate n random int values between 0 and m-1 and count the number of
 *  distinct values generated.
 *
 *  Probability theory says that the number of distinct values should
 *  be about m * (1 – e^(-alpha)), where alpha = n/m.
 *
 ******************************************************************************/
package _2_Sorting._2_5_SortingApplications;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

public class Distinct {

    public static int distinct(Comparable[] a){
        if (a.length == 0) return 0;
        Arrays.sort(a);
        int distinct = 1;
        for (int i = 1; i < a.length; i++){
            if (a[i].compareTo(a[i-1]) != 0)
                distinct++;
        }
        return distinct;
    }

    public static void main(String[] args){
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        int t = Integer.parseInt(args[2]);

        int[] distinct = new int[t];
        for (int i = 0; i < t; i++){
            Integer[] a = new Integer[n];
            for (int j = 0; j < n; j++){
                a[j] = StdRandom.uniform(m);
            }
            distinct[i] = distinct(a);
        }

        double empirival = StdStats.mean(distinct);// 取平均值
        double alpha = (double) n/m;
        double theoretical = m * (1 - Math.exp(-alpha));// 计算理论值
        StdOut.printf("Theoretical = %.3f\n", theoretical);
        StdOut.printf("Empirical   = %.3f\n", empirival);
    }
}
