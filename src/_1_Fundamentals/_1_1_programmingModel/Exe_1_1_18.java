package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.1.18
 */
public class Exe_1_1_18 {
    public static void main(String[] args){
        StdOut.println(mystery(2,25));
        StdOut.println(mystery(3,11));
        StdOut.println(mystery2(2,25));
        StdOut.println(mystery2(3,11));
    }

    public static int mystery(int a, int b){
        if (b== 0) return 0;
        if (b % 2 == 0) return mystery(a+a, b/2);
        return mystery(a+a, b/2) + a;
    }

    public static int mystery2(int a, int b){
        if (b== 0) return 0;
        if (b % 2 == 0) return mystery(a*a, b/2);
        return mystery(a*a, b/2) + a;
    }

}
