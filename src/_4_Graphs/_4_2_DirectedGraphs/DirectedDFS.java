package _4_Graphs._4_2_DirectedGraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac DirectedDFS.java
 *  Execution:    java DirectedDFS digraph.txt s
 *  Dependencies: Digraph.java Bag.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/mediumDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/largeDG.txt
 *
 *  Determine single-source or multiple-source reachability in a digraph
 *  using depth first search.
 *  Runs in O(E + V) time.
 *
 *  % java DirectedDFS tinyDG.txt 1
 *  1
 *
 *  % java DirectedDFS tinyDG.txt 2
 *  0 1 2 3 4 5
 *
 *  % java DirectedDFS tinyDG.txt 1 2 6
 *  0 1 2 3 4 5 6 8 9 10 11 12
 *
 * 单点可达性
 ******************************************************************************/
public class DirectedDFS {
    private boolean[] marked;

    private int count; //能够从s到达的顶点数量

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        count = 0;
        validateVertex(s);
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        count = 0;
        for (int s : sources)
            dfs(G, s);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (! marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * 指定的结点v能否从s到达.
     *
     * @param v 结点v
     * @return 能够从s到达：true; 不能：false
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * 返回能够从结点s到达的结点数量
     */
    public int count() {
        return count;
    }

    // 结点是否合理
    private void validateVertex(int v) {
        int length = marked.length;
        if (v < 0 || v >= length)
            throw new IllegalArgumentException("vertex " + v + "is not between 0 and " + (length - 1));
    }

    private void validateVertex(Iterable<Integer> vertices) {
        if (vertices == null) throw new IllegalArgumentException("argument is null");
        int length = marked.length;
        for (int v : vertices) {
            if (v < 0 || v >= length)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (length - 1));
        }
    }

    public static void main(String[] args) {
        // args[0] : file
        // args[1..] : sources
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        Bag<Integer> sources = new Bag<>();
        for (int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }

        DirectedDFS dfs = new DirectedDFS(G, sources);

        for (int v = 0; v < G.V(); v++) {
            StdOut.println(v + ": " + dfs.marked(v));
        }
    }
}
