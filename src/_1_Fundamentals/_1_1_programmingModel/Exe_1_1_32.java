package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

/**
 * exercise 1.1.32
 */
public class Exe_1_1_32 {
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        double l = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);

        double[] a = StdIn.readAllDoubles();

        draw(N, l, r, a);
    }

    public static void draw (int N, double l, double r, double[] a){
        double withd = (r-l)/N;
        int[] statistics =new int[N];

        for (int i = 0; i < a.length; i++) {
            if (a[i] <= l || a[i] >= r ) continue;
            int index = (int)((a[i] - l)/withd);
            a[index] += 1;
        }

        StdDraw.setCanvasSize(1024, 1024);

        for (int i = 0; i < N; i++) {
            double x = l + i*(r - l)/(2*N);
            double y = statistics[i]/2.0;
            StdDraw.filledRectangle(x, y, withd/2.0, statistics[i]/2.0);
        }

    }
}
