package _4_Graphs._4_2_DirectedGraphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/******************************************************************************
 *  Compilation:  javac Digraph.java
 *  Execution:    java Digraph filename.txt
 *  Dependencies: Bag.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/mediumDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/largeDG.txt
 *
 *  A graph, implemented using an array of lists.
 *  Parallel edges and self-loops are permitted.
 *
 *  % java Digraph tinyDG.txt
 *  13 vertices, 22 edges
 *  0: 5 1
 *  1:
 *  2: 0 3
 *  3: 5 2
 *  4: 3 2
 *  5: 4
 *  6: 9 4 8 0
 *  7: 6 9
 *  8: 6
 *  9: 11 10
 *  10: 12
 *  11: 4 12
 *  12: 9
 *
 *
 * 有向图
 ******************************************************************************/
public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private int V;//顶点数
    private int E;//边数
    private Bag<Integer>[] adj;
    private int[] indegree;//入度

    /**
     * 创建有向图
     *
     * @param V 顶点个数
     */
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];

        adj = (Bag<Integer>[]) new Bag[this.V];
        for (int i = 0; i < this.V; i++) {
            adj[i] = new Bag<>();
        }
    }

    /**
     * 创建有向图
     *
     * @param in 输入流
     */
    public Digraph(In in) {
        this.V = in.readInt();
        if (this.V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        adj = (Bag<Integer>[]) new Bag[this.V];
        for (int i = 0; i < this.V; i++) {
            adj[i] = new Bag<>();
        }
        this.indegree = new int[this.V];
        this.E = in.readInt();
        if (this.E < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        for (int i = 0; i < this.E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }


    public Digraph(Digraph G) {
        this.V = G.V;
        this.E = G.E;
        this.adj = G.adj;
        this.indegree = G.indegree;
        //        adj = (Bag<Integer>[]) new Bag[this.V];
        //        for (int v = 0; v < this.V; v++) {
        //            Stack<Integer> reverse = new Stack<>();
        //            for (int w : G.adj[V])
        //                reverse.push(w);
        //            for (int w : reverse)
        //                this.addEdge(v, w);
        //        }
    }

    /**
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int outDegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public int inDegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    public Digraph reverse() {
        Digraph reverse = new Digraph(this.V);
        for (int v = 0; v < this.V; v++) {
            for (int w : adj[v]) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * 返回顶点个数
     *
     * @return 顶点个数
     */
    public int V() {
        return V;
    }

    /**
     * 返回边数
     *
     * @return 边数
     */
    public int E() {
        return E;
    }

    // 添加边
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
    }

    // 检查顶点是否合理
    private void validateVertex(int v) {
        if (v < 0 || v >= this.V)
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (this.V - 1));
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph digraph = new Digraph(in);
        System.out.println(digraph);
    }
}
