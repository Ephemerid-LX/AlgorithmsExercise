package _5_Strings._5_3_SubstringSearch;

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac Brute.java
 *  Execution:    java Brute pattern text
 *  Dependencies: StdOut.java
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using brute force.
 *
 *  % java Brute abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:               abracadabra
 *
 *  % java Brute rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:         rab
 *
 *  % java Brute rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java Brute bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                                   bcara
 *
 *  % java Brute abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ******************************************************************************/
public class Brute {

    private Brute() {
    }

    // 不回退
    public static int search1(String pat, String txt) {
        int m = pat.length(), n = txt.length();

        for(int i = 0; i < n - m; i++) {

            int j;
            for(j = 0; j < m; j++) {
                if(pat.charAt(j) != txt.charAt(i + j)) break;
            }

            if(j == m) return i;
        }

        return n;
    }

    // 回退
    public static int search2(String pat, String txt) {
        int m = pat.length(), n = txt.length();

        int i, j;
        for(i = 0, j = 0; i < n && j < m; i++) {
            if(pat.charAt(j) == txt.charAt(i)) j++;
            else {
                i -= j;
                j = 0;
            }
        }
        if(j == m) return i - j;
        return n;
    }

    public static void main(String[] args) {
        String pat = args[0], txt = args[1];

        int offset1 = search1(pat, txt);
        int offset2 = search2(pat, txt);

        StdOut.println("text:    " + txt);

        StdOut.print("patten1: ");
        for(int i = 0; i < offset1; i++)
            StdOut.print(" ");
        StdOut.println(pat);

        StdOut.print("patten2: ");
        for(int i = 0; i < offset2; i++)
            StdOut.print(" ");
        StdOut.println(pat);
    }
}
