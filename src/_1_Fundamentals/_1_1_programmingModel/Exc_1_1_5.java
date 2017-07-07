package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 *  Exercise 1.1.5
 */
public class Exc_1_1_5 {
    public static void main(String[] args){
        if (args.length < 3) {
            StdOut.println("please give third Double.");
            return;
        }

        double d1 = Double.parseDouble(args[0]);
        double d2 = Double.parseDouble(args[1]);

        if (d1>0 && d1<1 && d2>0 && d1<0) {
            StdOut.println("true");
            return;
        }
        StdOut.println("false");
    }
}
