package _1_Fundamentals._1_5_CaseStudyUnionFind;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 加权 QuickUnion实现
 */
public class WeightedQuickUnionUF {
    private int[] id;
    private int[] size;
    private int count;

    public WeightedQuickUnionUF(int n){
        count = n;
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int count(){
        return count;
    }

    // 是否连接
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    // 找根节点
    public int find(int p){
        while (p != id[p]) p = id[p];
        return p;
    }

    // 归并
    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        int pSize = size[pRoot];
        int qSize = size[qRoot];

        if (pSize < qSize) {
            id[pRoot] = qRoot;
            size[qRoot] = pSize + qSize;
        }else {
            id[qRoot] = pRoot;
            size[pRoot] = pSize + qSize;
        }
        count--;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
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
