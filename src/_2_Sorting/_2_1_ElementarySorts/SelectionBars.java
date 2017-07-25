package _2_Sorting._2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 绘制 选择算法 棒状轨迹
 *
 * 参数: 数组大小
 *  如: args[0] = 20;
 */
public class SelectionBars {
    public static void sort(Double[] a){
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1 ; j < n; j++)
                if (less(a[j], a[min])) min = j;
            show(a, i, min);
            exch(a, i, min);
        }
    }

    public static void show(Double[] a, int i, int min){
        StdDraw.setYscale(-a.length+i+1, i+1);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < i; k++)
            StdDraw.line(k, 0, k, a[k] * 0.6);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = i; k < a.length; k++)
            StdDraw.line(k, 0, k, a[k] * 0.6);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(min, 0, min, a[min] * 0.6);

    }

    // v < w , return true; else false
    private static boolean less(Double v , Double w) {
        return v.compareTo(w) < 0;
    }

    // switch i and j
    private static void exch(Double[] a, int i, int j){
        Double swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        StdDraw.setCanvasSize(160, 640);
        StdDraw.setXscale(-1, n+1);
        StdDraw.setPenRadius(0.006);
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(0.0, 1.0);
        sort(a);
    }
}
