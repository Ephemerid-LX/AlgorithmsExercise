package _2_Sorting._2_1_ElementarySorts;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * creative problem 2.1.18
 * 可视轨迹的Insertion 算法
 *
 * args:
 *    [String-需要排序的字符串] 例如：SORTEXAMPLE
 */
public class TraceInsertion {
    public static void sort(String[] a){
        int n = a.length;
        for (int i = 0; i < n; i++){
            int j ;
            for (j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
            draw(a, i, i, j);
        }
    }

    /**
     * 交换数组中两个元素的位置
     * @param a 数组
     * @param i 下标i
     * @param j 下标j
     */
    private static void exch(String[] a, int i, int j){
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * 比较两个元素的大小
     * @param v 元素v
     * @param w 元素w
     * @return 如果v < w，则返回true；否则，返回false
     */
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /**
     *
     * @param a 当前数组
     * @param row 当前数组下标
     * @param ith 交换数组下标
     * @param jth 交换下标
     */
    private static void draw(String[] a, int row, int ith, int jth){
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(-2.50, row, ith + "");
        StdDraw.text(-1.25, row, jth + "");
        for (int i = 0; i < a.length; i++) {
            // 将交换后的位置标红
            if (i == jth) StdDraw.setPenColor(StdDraw.BOOK_RED);
            // 将i 之后的字体颜色变为灰色
            else if (i > ith) StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            // 将j 之前的字体颜色变为灰色
            else if (i < jth) StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            // 被比较的位置标为黑色
            else StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(i, row, a[i]);
        }
    }

    private static void header(String[] a){
        int n = a.length;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(n/2.0, -3, "a[]");
        for (int i = 0; i < n; i++)
            StdDraw.text(i, -2, i+"");
        StdDraw.text(-2.50, -2, "i");
        StdDraw.text(-1.25, -2, "j");
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.line(-3, -1.65, n - 0.5, -1.65);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < n; i++)
            StdDraw.text(i, -1, a[i]);
    }

    private static void footer(String[] a){
        int n = a.length;
        StdDraw.setPenColor(StdDraw.BLACK);
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
