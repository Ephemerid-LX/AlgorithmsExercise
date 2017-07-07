package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.1.7
 */
public class Exc_1_1_7 {
    public static void main(String[] args){
        //a.
        double t = 9.0;
        while (Math.abs(t - 9.0/t) > .001) {
            t = (9.0 / t + t) / 2.0;
        }
        StdOut.printf("%.5f\n", t);

        //b.
        int sumb = 0;
        for (int i=1; i<1000; i++)
            for (int j=0; j<1000; j++)
                sumb++;
        StdOut.println(sumb);

        /*
         * c.
         *
         *  for (int n = 0; 2^n < 1000; n++)
         */
        int sumc = 0;
        for (int i = 1; i < 1000; i *= 2)
            for (int j = 0; j < 1000; j++)
                sumc ++;
        StdOut.println(sumc);
    }
}
