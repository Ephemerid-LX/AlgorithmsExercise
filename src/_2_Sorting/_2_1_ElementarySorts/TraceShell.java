package _2_Sorting._2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * 绘制希尔算法的可视轨迹
 *
 */
public class TraceShell {
    // 行数
    private static int line = 0;

    public static void sort(String[] a){
        int n = a.length;
        int h = 1;
        while (h < n/3) h = 3*h+1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int j;
                for (j = i; j >= h && less(a[j], a[j-h]); j -= h){
                    exch(a, j ,j-h);
                }
                draw(a, h, i, j);
                line++;
            }
            h /= 3;
            footer(a);
            line++;
        }

    }

    private static boolean less(String v , String w){
        return v.compareTo(w) < 0;
    }

    private static void exch(String[] a, int i, int j){
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void draw(String[] a, int h, int ith, int jth){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(-3.75, line, h+"");
        StdDraw.text(-2.50, line, ith+"");
        StdDraw.text(-1.25, line, jth+"");
        for (int i = 0; i < a.length; i++) {
            if (i == jth) StdDraw.setPenColor(StdDraw.BOOK_RED);
            else if (i > ith) StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else if (i < jth) StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else if ( (i % h) == (jth % h)) StdDraw.setPenColor(StdDraw.BLACK);
            else StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            StdDraw.text(i, line, a[i]);
        }
    }

    private static void header(String[] a){
        int n = a.length;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(n/2.0, -3, "a[ ]");
        for (int i = 0; i < n; i++)
            StdDraw.text(i, -2, i + "");
        StdDraw.text(-3.75, -2, "h");
        StdDraw.text(-2.50, -2, "i");
        StdDraw.text(-1.25, -2, "j");
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(-4, -1.65, n - 0.5, -1.65);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < a.length; i++)
            StdDraw.text(i, -1, a[i]);
    }

    private static void footer(String[] a){
        int n = a.length;
        for (int i = 0; i < n; i++)
            StdDraw.text(i, line, a[i]);
    }

    public static void main(String[] args) {
        String s = args[0];
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i, i+1);

        int rows = 0;
        int h = 1;
        while (h < 3/n) {
            rows += (n - h + 1);
            h = 3*h + 1;
        }
        rows += (n - h + 1);

        StdDraw.setCanvasSize(30*(n+3), 30*(rows+3));
        StdDraw.setXscale(-4, n+1);
        StdDraw.setYscale(rows, -4);
        StdDraw.setFont(new Font("SanSerif", Font.PLAIN, +13));

        header(a);

        sort(a);
    }
}
