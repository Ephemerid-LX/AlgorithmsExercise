package _5_Strings._5_1_StringSorts;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac LSD.java
 *  Execution:    java LSD < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/51radix/words3.txt
 *
 *  LSD radix sort
 *
 *    - Sort a String[] array of n extended ASCII strings (R = 256), each of length w.
 *
 *    - Sort an int[] array of n 32-bit integers, treating each integer as
 *      a sequence of w = 4 bytes (R = 256).
 *
 *  Uses extra space proportional to n + R.
 *
 *
 *  % java LSD < words3.txt
 *  all
 *  bad
 *  bed
 *  bug
 *  dad
 *  ...
 *  yes
 *  yet
 *  zoo
 *
 * 低位优先的字符串排序
 ******************************************************************************/
public class LSD {

    private LSD() {}

    /**
     * 字符串的低位优先排序
     *
     * @param a 字符串数组
     * @param w 按前w位排序
     */
    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;
        String[] aux = new String[n];

        for(int i = 0; i < n; i++)
            if(a[i].length() < w) throw new IllegalArgumentException("Strings(" + a[i] + ") must have fixed length");

        for(int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 1];

            for(int i = 0; i < n; i++)
                count[a[i].charAt(d) + 1]++;

            for(int r = 0; r < R; r++)
                count[r + 1] += count[r];

            for(int i = 0; i < n; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            for(int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }

    public static void sort(int[] a) {
        //todo
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();
        int n = a.length;
        int w = a[0].length();

        for(int i = 0; i < n; i++)
            if(a[i].length() != w) throw new IllegalArgumentException("Strings(" + a[i] + ") must have fixed length");

        sort(a, w);

        for(int i = 0; i < n; i++)
            StdOut.println(a[i]);
    }
}
