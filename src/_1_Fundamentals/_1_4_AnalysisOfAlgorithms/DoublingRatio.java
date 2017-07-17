package _1_Fundamentals._1_4_AnalysisOfAlgorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 */
public class DoublingRatio {
    public static double timeTrial(int n){
        // 为处理N个随机的六位整数的ThreeSum.count()
        int max = 1000000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(-max, max);
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args){
        double prev = timeTrial(125);
        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            StdOut.printf("%6d %7.1f ", n, time);
            StdOut.printf("%5.1f\n", time/prev);
            prev = time;
        }
    }
}
