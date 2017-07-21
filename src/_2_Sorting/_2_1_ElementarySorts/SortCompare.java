package _2_Sorting._2_1_ElementarySorts;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;


/**
 * 比较两种排序算法
 */
public class SortCompare {
    /**
     * 进行一次某种排序所需要的时间
     *
     */
    public static double time(String alg, Comparable[] a){
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("Merge")) Merge.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        if (alg.equals("Heap")) Heap.sort(a);
        return timer.elapsedTime();
    }

    /**
     * 生成大小为n的数组，进行t次某种(alg)排序，返回花费的时间
     * @param alg 排序算法名称
     * @param n 数组大小
     * @param t 试验次数
     * @return 花费时间
     */
    public static double timeRandomInput(String alg, int n, int t){
        // 使用算法1将T个长度为n的数组排序
        double total = 0.0;// 总时间
        Double[] a = new Double[n];
        for (int i = 0; i < t; i++){
            // 进行一次测试(生成一个数组并排序)
            for (int j = 0; j < n; j++)
                a[j] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args){
        String alg1 = args[0];
        String alg2 = args[1];
        int n = Integer.parseInt(args[2]);
        int t = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, n ,t);
        double t2 = timeRandomInput(alg2, n, t);
        StdOut.printf("For %d random Doubles\n    %s is ", n, alg1);
        StdOut.printf("  %.1f times faster than %s\n", t2/t1, alg2);
    }
}
