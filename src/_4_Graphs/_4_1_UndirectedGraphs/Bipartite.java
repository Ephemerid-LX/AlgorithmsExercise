package _4_Graphs._4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Graph;

/******************************************************************************
 *  Compilation:  javac Bipartite.java
 *  Execution:    java  Bipartite V E F
 *  Dependencies: Graph.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  Given a graph, find either (i) a bipartition or (ii) an odd-length cycle.
 *  Runs in O(E + V) time.
 *
 *
 ******************************************************************************/
public class Bipartite {
    private boolean isBipartite;
    private boolean[] marked;
    private boolean[] color;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public Bipartite(Graph G){
        isBipartite = true;
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }

        assert check(G);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return;

            // 对未被标记过的结点进行标记，并上色；
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(G, w);
            }
            // 对标记过的结点，判断相邻颜色是否相同；
            else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    /**
     * 是否是二分图
     * @return 是: true;否: false
     */
    public boolean isBipartite() {
        return isBipartite;
    }

    /**
     * 返回结点v的颜色
     * @param v 结点v
     * @return 结点v的颜色
     */
    public boolean color(int v){
        validateVertex(v);
        if (!isBipartite)
            throw new UnsupportedOperationException("Graph is not bipartite");
        return color[v];
    }

    /**
     * 返回环
     * @return 环
     */
    public Iterable<Integer> oddCycle() {
        return cycle;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v > V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    private boolean check(Graph G) {
        if (isBipartite) {
            for (int v = 0; v < G.V(); v++) {
                for (int w : G.adj(v)) {
                    if (color[w] == color[v]) {
                        System.err.printf("edge %d-%d with %d and %d in same side of bipartition\n", v, w, v, w);
                        return false;
                    }
                }
            }
        }

        else {
            int first = -1, last = -1;
            for (int v : oddCycle()) {
                if (first == -1) first = v;
                last = v;
            }

            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int V1 = Integer.parseInt(args[0]);
        int V2 = Integer.parseInt(args[1]);
        int E = Integer.parseInt(args[2]); // 边数
        int F = Integer.parseInt(args[3]);

        Graph G = GraphGenerator.bipartite(V1, V2, E);
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V1 + V2);
            int w = StdRandom.uniform(V1 + V2);
            G.addEdge(v, w);
        }

        StdOut.println(G);

        Bipartite b = new Bipartite(G);
        if (b.isBipartite()) {
            StdOut.println("Graph is bipartite");
            for (int v = 0; v < G.V(); v++) {
                StdOut.println(v + ": " + b.color(v));
            }
        }
        else {
            StdOut.println("Graph has an add-length cycle");
            for (int x : b.oddCycle()) {
                StdOut.print(x + " ");
            }
        }
    }
}
