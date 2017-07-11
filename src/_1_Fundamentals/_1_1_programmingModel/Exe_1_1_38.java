package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;

/**
 * exercise 1.1.38
 */
public class Exe_1_1_38 {
    public static void main(String[] args){
        batch(10000);
    }

    public static void batch(int T){
        int[] N = {1000, 10000, 100000, 1000000};
        for (int i = 0; i < N.length; i++) {
            StdOut.println(N[i]+":"+ calculationAverage(N[i], T));
        }
    }

    public static double calculationAverage(int N, int T){
        int[] count = new int[T];
        for (int i = 0; i < T; i++) {
            int[] a = new int[N], b = new int[N];
            for (int j = 0; j < N; j++) {
                a[j] = StdRandom.uniform(100000,1000000);
                b[j] = StdRandom.uniform(100000,1000000);
            }

            Arrays.sort(b);

            for (int j = 0; j < N; j++)
                if (BinarySearch.indexOf(b, a[j]) >= 0) count[i]++;
        }
        return StdStats.mean(count);
    }
}
