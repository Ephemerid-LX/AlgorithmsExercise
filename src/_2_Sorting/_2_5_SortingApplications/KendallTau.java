package _2_Sorting._2_5_SortingApplications;

import edu.princeton.cs.algs4.Inversions;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *  Compilation:  javac KendallTau.java
 *  Execution:    java KendallTau n
 *  Dependencies: StdOut.java Inversions.java
 *
 *  Generate two random permutations of size N and compute their
 *  Kendall tau distance (number of inversions).
 *
 ******************************************************************************/
public class KendallTau {

    // return Kendall tau distance between two permutations
    public static long distance(int[] a, int[] b){
        if (a.length != b.length)
            throw new IllegalArgumentException("Array dimensions disagree");
        int n = a.length;

        /*
        todo:为什么做这样几个步骤后能够就能够计算？
        如：
            a:{0,3,1,6,2,5,4},     b:{1,0,3,6,4,2,5}
        得到:
         ainv:{0,2,4,1,6,5,3},  bnew:{2,0,1,3,6,4,5}

         之后的步骤就是对bnew进行归并排序时,顺便计数


         */
        int[] ainv = new int[n];
        for (int i = 0; i < n; i++)
            ainv[a[i]] = i;

        Integer[] bnew = new Integer[n];
        for (int i = 0; i < n; i++)
            bnew[i] = ainv[b[i]];

        return Inversions.count(bnew);
    }

    public static int[] permutation(int n){
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i;
        StdRandom.shuffle(a);
        return a;
    }

    public static void main(String[] args){

        int n = Integer.parseInt(args[0]);
        int[] a = permutation(n);
        int[] b = permutation(n);

        for (int i = 0; i < n; i++)
            StdOut.println(a[i]+ " " +b[i] );
        StdOut.println();

        StdOut.println("inversions = " + KendallTau.distance(a, b));
    }
}
