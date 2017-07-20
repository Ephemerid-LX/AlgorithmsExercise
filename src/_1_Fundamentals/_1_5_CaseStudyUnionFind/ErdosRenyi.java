package _1_Fundamentals._1_5_CaseStudyUnionFind;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * creative problems 1.5.17
 */
public class ErdosRenyi {
    public static int count(int n){
        int edges = 0;
        UF uf = new UF(n);
        while (uf.count() > 1) {
            int i = StdRandom.uniform(n);
            int j = StdRandom.uniform(n);
            uf.union(i, j);
            edges ++;
        }
        return edges;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        int[] edges = new int[trials];

        for (int i = 0; i < trials; i++) {
            edges[i] = count(n);
        }

        // 1.5.21
        StdOut.println("1/2 n ln n = " + 0.5 * n * Math.log(n));
        StdOut.println("mean       = " + StdStats.mean(edges));
        StdOut.println("stddev     = " + StdStats.stddev(edges));
    }
}
