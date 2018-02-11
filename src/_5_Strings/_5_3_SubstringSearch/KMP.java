package _5_Strings._5_3_SubstringSearch;

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac KMP.java
 *  Execution:    java KMP pattern text
 *  Dependencies: StdOut.java
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  KMP algorithm.
 *
 *  % java KMP abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:               abracadabra
 *
 *  % java KMP rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:         rab
 *
 *  % java KMP bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                                   bcara
 *
 *  % java KMP rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java KMP abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ******************************************************************************/
public class KMP {
    private final int R;
    private int[][] dfa;

    private String pat;
    private char[] pattern;

    private int m;

    public KMP(String pat) {
        this.R = 256;
        this.pat = pat;

        this.m = pat.length();

        dfa = new int[R][m];

        dfa[pat.charAt(0)][0] = 1;
        for(int j = 1, x = 0; j < m; j++) {
            for(int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];

            dfa[pat.charAt(j)][j] = j + 1;
            x = dfa[pat.charAt(j)][x];
        }
    }

    public int search(String txt) {
        int n = txt.length();
        int i, j;
        for(i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }

        if(j == m) return i - j;
        return n;
    }

    public static void main(String[] args) {
        String pat = args[0], txt = args[1];

        KMP kmp = new KMP(pat);

        int offset = kmp.search(txt);

        StdOut.println("text:    " + txt);

        StdOut.print("patten:  ");
        for(int i = 0; i < offset; i++)
            StdOut.print(" ");
        StdOut.println(pat);
    }
}
