package _1_Fundamentals._1_5_CaseStudyUnionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * creative problems 1.5.12
 *
 */
public class QuickUnionPathCompressionUF {
    private int[] id;
    private int count;

    public QuickUnionPathCompressionUF(int n){
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    public int count(){
        return count;
    }


    // 是否连接
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int p){
        int root = p;
        // 找根
        while (root != id[root]) root = id[root];

        // 将路径上的每一个节点的跟都指向root
        while (p != id[p]){
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
    }

    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}

