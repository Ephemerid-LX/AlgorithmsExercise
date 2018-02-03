package _5_Strings._5_1_StringSorts;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac Count.java
 *  Execution:    java Count alpha < input.txt
 *  Dependencies: Alphabet.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/50strings/abra.txt
 *                https://algs4.cs.princeton.edu/50strings/pi.txt
 *
 *  Create an alphabet specified on the command line, read in a
 *  sequence of characters over that alphabet (ignoring characters
 *  not in the alphabet), computes the frequency of occurrence of
 *  each character, and print out the results.
 *
 *  %  java Count ABCDR < abra.txt
 *  A 5
 *  B 2
 *  C 1
 *  D 1
 *  R 2
 *
 *  % java Count 0123456789 < pi.txt
 *  0 99959
 *  1 99757
 *  2 100026
 *  3 100230
 *  4 100230
 *  5 100359
 *  6 99548
 *  7 99800
 *  8 99985
 *  9 100106
 *
 ******************************************************************************/
public class Count {
    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet(args[0]);
        final int R = alphabet.radix();
        int[] count = new int[R];
        In in = new In(args[1]);
        while(!in.isEmpty()) {
            char c = in.readChar();
            if(alphabet.contains(c)) count[alphabet.toIndex(c)]++;
        }
        for(int i = 0; i < R; i++)
            StdOut.println(alphabet.toChar(i) + ":" + count[i]);
    }

}
