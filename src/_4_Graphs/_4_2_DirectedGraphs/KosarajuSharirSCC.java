package _4_Graphs._4_2_DirectedGraphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class KosarajuSharirSCC {
    private boolean[] marked;
    private int count;
    private int[] id;

    public KosarajuSharirSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        DepthFirstOrder dfs = new DepthFirstOrder(G.reverse());

        for (int v : dfs.reversePost()) {
            if (! marked[v]) {
                dfs(G, v);
                count++;
            }

        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;

        for (int w : G.adj(v)) {
            if (! marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * 判断结点v和结点w是否是强连通
     *
     * @param v 结点v
     * @param w 结点w
     * @return v和w强连通:true; 否则: false
     */
    public boolean stronglyConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }


    /**
     * 返回强连通分量的个数
     *
     * @return 强连通分量的个数
     */
    public int count() {
        return count;
    }

    /**
     * 返回结点v所在强连通分量id
     *
     * @param v 结点v
     * @return 结点v所在强连通分量id
     */
    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);

        int m = scc.count();
        StdOut.println(m + " strong components");

        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }

        for (int v = 0; v < G.V(); v++) {
            components[scc.id(v)].enqueue(v);
        }

        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

    }
}
