package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.1.24
 * 欧几里得算法（Euclidean algorithm，辗转相除法）
        */
public class Exe_1_1_24 {
    public static void main(String[] args){
        StdOut.println(euclid(1111111,1234567));
    }
    // 欧几里得算法
    public static int euclid(int a, int b){
        //StdOut.println("a="+a+"; b="+b);
        if (b == 0) return a;
        return euclid(b, a%b);
    }
}
