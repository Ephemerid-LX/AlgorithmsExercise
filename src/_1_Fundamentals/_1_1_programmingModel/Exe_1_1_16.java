package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.1.16
 */
public class Exe_1_1_16 {
    public static void main(String[] args){
        StdOut.println(exR1(6));
    }

    public static String exR1(int n){
        if (n <= 0) return "";
        return exR1(n-3) + n + exR1(n-2) + n;
    }
}
