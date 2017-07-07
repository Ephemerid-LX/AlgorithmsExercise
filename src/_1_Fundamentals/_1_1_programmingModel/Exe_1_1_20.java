package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * exercise 1.1.20
 * 阶乘:
 *  n! = 1*2*3*...*n
 * 对数运算:
 *  ln是以e为底的对数
 *  logn(xy) = logn(x)+logn(y)\\公式打不出来
 *
 */
public class Exe_1_1_20 {
    public static void main(String[] args){
        StdOut.println(exe1_1_20(1));

    }

    /**
     * 计算阶乘的自然对数
     * @param n
     * @return
     */
    public static double exe1_1_20(int n){
        if (n == 1) return 1;
        return Math.log(n)+Math.log(n-1);
    }
}
