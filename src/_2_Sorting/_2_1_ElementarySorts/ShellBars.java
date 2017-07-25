package _2_Sorting._2_1_ElementarySorts;


import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Font;

/**
 * Shell排序的棒状轨迹
 *
 * 参数: 数组大小
 *  如: args[0] = 150;
 */
public class ShellBars {
    // 因子, 将整个轨迹的 长 放大的倍数
    private static final int FF = 2;

    private static void sort(double[] a){
        int n = a.length;
        int k = 1;
        int h = 1;
        while (h < n/3) {
            h = 3 * h + 1;
            k++;
        }

        show(a, k , "input");

        while (h >= 1) {
            for (int i = h; i < n; i++){
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            if (h < n) show(a, --k, h+"-sorted");
            h = h/3;
        }

    }

    private static void show(double[] a, int k, String message){
        for (int i = 0; i < a.length; i++) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.line(i, FF*k, i, FF*k + a[i]*(FF-1));
        }
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.text(0, FF*k-0.3, message);
    }

    private static boolean less(double v, double w){
        return v < w;
    }

    private static void exch(double[] a, int i, int j){
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }



    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        double[] a = new double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(0.0, 1.0);
        // 提前计算k的值，用于 设置 纵坐标范围
        int k = (int) Math.round(Math.log(n)/Math.log(3));

        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(756, 900);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 9));
        StdDraw.setXscale(-10, n+1);
        StdDraw.setYscale(-1, FF*(k+1));
        StdDraw.setPenRadius(0.005);

        sort(a);
        StdDraw.show();
    }
}
