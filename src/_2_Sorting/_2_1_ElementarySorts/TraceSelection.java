package _2_Sorting._2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * 绘出选择排序算法的轨迹
 * args:
 *   [String-需要排序的字符串] 例如：SORTEXAMPLE
 */
public class TraceSelection {
    public static void sort(String[] a){
        int n = a.length;
        for (int i = 0; i < n; i++){
            int min = i;
            for (int j = i+1; j < n; j++){
                if (less(a[j], a[min])) min = j;
            }
            draw(a, i, i, min);
            exch(a, i, min);
        }
    }

    public static boolean less(String v , String w){
        return v.compareTo(w) < 0;
    }

    public static void exch(String[] a, int i, int j){
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void draw(String[] a, int row, int ith, int min){
        int n = a.length;
        StdDraw.text(-2.5, row, ith+"");
        StdDraw.text(-1.25, row, min+"");
        for (int i = 0; i < n; i++) {
            if (i == min) StdDraw.setPenColor(StdDraw.BOOK_RED);
            else if (i < ith) StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(i, row, a[i]);
        }
    }

    // 画头部
    private static void header(String[] a){
        int n = a.length;
        StdDraw.text(n/2.0, -3, "a[]");
        StdDraw.text(-2.5, -2, "i");
        StdDraw.text(-1.25, -2, "min");
        for (int i = 0; i < n; i++)
            StdDraw.text(i, -2, i+"");
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(-3, -1.65, n-0.5, -1.65);

        //打印初始数组元素
        for (int i = 0; i < n; i++)
            StdDraw.text(i, -1, a[i]);
    }

    // 画尾部
    private static void footer(String[] a){
        int n = a.length;
        for (int i = 0; i < n; i++)
            StdDraw.text(i, n, a[i]);
    }
    // test
    public static void main(String[] args){
        String s = args[0];
        int n = s.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = s.substring(i, i+1);

        StdDraw.setCanvasSize(30*(n+3), 30*(n+3));
        StdDraw.setXscale(-3, n+1);
        StdDraw.setYscale(n+1, -3);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 13));

        header(a);

        sort(a);

        footer(a);
    }
}
