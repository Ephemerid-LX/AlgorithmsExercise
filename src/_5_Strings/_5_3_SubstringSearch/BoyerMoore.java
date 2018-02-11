package _5_Strings._5_3_SubstringSearch;

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac BoyerMoore.java
 *  Execution:    java BoyerMoore pattern text
 *  Dependencies: StdOut.java
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  bad-character rule part of the Boyer-Moore algorithm.
 *  (does not implement the strong good suffix rule)
 *
 *  % java BoyerMoore abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:               abracadabra
 *
 *  % java BoyerMoore rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:         rab
 *
 *  % java BoyerMoore bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                                   bcara
 *
 *  % java BoyerMoore rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java BoyerMoore abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ******************************************************************************/
public class BoyerMoore {
    private final int R;
    private String pat;
    private int[] right;

    public BoyerMoore(String pat){
        this.R = 256;
        this.pat = pat;

        int m = pat.length();
        right = new int[R];

        for(int c = 0; c < R; c++)
            right[c] = -1;

        for(int j = 0; j < m; j++)
            right[pat.charAt(j)] = j;
    }

    public int search(String txt) {
        int n = txt.length(), m = pat.length();
        int skip;
        for(int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for(int j = m - 1; j >= 0; j--) {
                if(txt.charAt(i + j) != pat.charAt(j)) {
                    skip = Math.max(1, j - right[txt.charAt(i + j)]);
                }
            }
            if(skip == 0) return i;
        }
        return n;
    }

    public static void main(String[] args) {
        String pat = args[0], txt = args[1];

        BoyerMoore bm = new BoyerMoore(pat);

        int offset = bm.search(txt);

        StdOut.println("text:    " + txt);

        StdOut.print("patten:  ");
        for(int i = 0; i < offset; i++)
            StdOut.print(" ");
        StdOut.println(pat);
    }
}
