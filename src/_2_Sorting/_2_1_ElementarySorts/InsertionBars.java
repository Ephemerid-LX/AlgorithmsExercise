package _2_Sorting._2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 绘制 插入算法 的棒状轨迹
 */
public class InsertionBars {

    private static void sort(double[] a){
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int j;
            for (j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a,j ,j-1);
            show(a, i, j);
        }
    }

    // 如果v < w，则返回true；否则返回false
    private static boolean less(double v, double w) {
        return v < w;
    }

    // 交换i和j的元素
    private static void exch(double[] a, int i, int j){
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(double[] a, int i, int j){
        // 这个纵坐标为什么这样设定
        // todo
        StdDraw.setYscale(-a.length + i + 1, i);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < i; k ++)
            StdDraw.line(k,0, k, a[i] * 0.6);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(j, 0, j, a[j]*0.6);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = j+1; k <= i; k++)
            StdDraw.line(k, 0,k ,a[k] * 0.6);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = i+i; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k]);
    }

    public static void main(String[] args){
        Integer n = Integer.parseInt(args[0]);
        StdDraw.setCanvasSize(160, 640);
        StdDraw.setXscale(-1, n+1);
        StdDraw.setPenRadius(0.006);
        double[] a = new double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(0.0, 1.0);
        sort(a);
    }
}

