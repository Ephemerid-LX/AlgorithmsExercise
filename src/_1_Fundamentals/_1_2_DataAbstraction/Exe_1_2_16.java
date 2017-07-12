package _1_Fundamentals._1_2_DataAbstraction;

import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.2.16
 */
public class Exe_1_2_16 {
    public static void main(String[] args){
        int numerator1 = Integer.parseInt(args[0]);
        int denominator1 = Integer.parseInt(args[1]);
        int numerator2 = Integer.parseInt(args[2]);
        int denominator2 = Integer.parseInt(args[3]);

        Rational a = new Rational(numerator1, denominator1);
        Rational b = new Rational(numerator2, denominator2);

        StdOut.println(a);
        StdOut.println(b);
        StdOut.println(a.plus(b));
        StdOut.println(a.minus(b));
        StdOut.println(a.divides(b));
        StdOut.println(a.times(b));
    }
}
