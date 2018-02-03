package _5_Strings._5_1_StringSorts;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/******************************************************************************
 *  Compilation:  javac Quick3string.java
 *  Execution:    java Quick3string < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/51radix/words3.txt
 *                https://algs4.cs.princeton.edu/51radix/shells.txt
 *
 *  Reads string from standard input and 3-way string quicksort them.
 *
 *  % java Quick3string < shell.txt
 *  are
 *  by
 *  sea
 *  seashells
 *  seashells
 *  sells
 *  sells
 *  she
 *  she
 *  shells
 *  shore
 *  surely
 *  the
 *  the
 *
 *
 ******************************************************************************/
public class Quick3String {
    private static int CUTOFF = 15;

    private Quick3String() {}

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    /**
     * 返回字符串指定下标字符的key
     *
     * @param s 字符串
     */
    private static int charAt(String s, int d) {
        if(d < 0 || d > s.length()) throw new IllegalArgumentException("The method's second argument is error.");
        if(d == s.length()) return -1;
        return s.charAt(d);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        //小范围内使用插入排序
        if(hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while(i <= gt) {
            int t = charAt(a[i], d);
            if(v < t) exch(a, i, gt--);
            else if(v > t) exch(a, lt++, i++);
            else i++;
        }

        sort(a, lo, lt - 1, d);
        if(v >= 0) sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    //插入排序
    private static void insertion(String[] a, int lo, int hi, int d) {
        for(int i = lo; i <= hi; i++) {
            for(int j = i; j > lo && less(a[j], a[j - 1], d); j--)
                exch(a, j, j - 1);
        }
    }

    //比较: 字符串v是否比字符串w小
    private static boolean less(String v, String w, int d) {
        for(int i = d; i < Math.min(v.length(), w.length()); i++) {
            if(v.charAt(i) < w.charAt(i)) return true;
            else if(v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

    //交换数组中两个元素
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();

        sort(a);
        for(String s : a)
            StdOut.println(s);
    }
}
