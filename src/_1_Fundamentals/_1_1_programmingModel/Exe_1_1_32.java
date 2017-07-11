package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

/**
 * exercise 1.1.32
 * args[0] = exe_1_1_32.txt
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

        // 统计区分分布
        // 用需要统计的值 除以宽 取商(商是整数)
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= l || a[i] >= r ) continue;
            int index = (int)((a[i] - l)/withd);
            statistics[index] += 1;
        }
        // setCanvasSize必须在之前设置，不然图像显示不出来
        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setXscale(l, r);
        StdDraw.setYscale(0, StdStats.max(statistics));

        // 计算每个矩形中心坐标点
        for (int i = 0; i < N; i++) {
            double x = l + (i+0.5)* withd;
            double y = statistics[i]/2.0;
            StdOut.println(x+","+y);
            StdDraw.filledRectangle(x, y, withd/2.0, statistics[i]/2.0);
        }

    }
}
