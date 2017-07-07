package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.1.9
 * 十进制、二进制的相互转换
 */
public class Exc_1_1_9 {
    public static void main(String[] args){
        String s = "";
        int N = StdIn.readInt();
        for (int n = N; n > 0; n /= 2) {
            //商除2取余
            s = n%2 + s;
        }
        StdOut.println(s);
    }
}
