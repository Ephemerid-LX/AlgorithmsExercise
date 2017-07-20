package _1_Fundamentals._1_5_CaseStudyUnionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 */
public class UF {
    private int[] parent;
    private byte[] rank;
    private int count;

    public UF(int n){
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int count(){
        return count;
    }

    // 查找的同时压缩路径
    public int find(int p){
        validate(p);
        while (p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    // 验证下标的值是否超出范围
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n)
            throw new IndexOutOfBoundsException("下标" + p + "不在0~" + (n - 1) + "范围内。");
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if (rank[rootQ] < rank[rootP]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        UF uf = new UF(n);
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
