package _4_Graphs._4_4_ShortestPaths;


import _1_Fundamentals._1_3_BagQueuesAndStacks.Bag;
import _1_Fundamentals._1_3_BagQueuesAndStacks.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *  Compilation:  javac EdgeWeightedDigraph.java
 *  Execution:    java EdgeWeightedDigraph digraph.txt
 *  Dependencies: Bag.java DirectedEdge.java
 *  Data files:   https://algs4.cs.princeton.edu/44st/tinyEWD.txt
 *                https://algs4.cs.princeton.edu/44st/mediumEWD.txt
 *                https://algs4.cs.princeton.edu/44st/largeEWD.txt
 *
 *  An edge-weighted digraph, implemented using adjacency lists.
 *
 * 加权有向图
 ******************************************************************************/
public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    private int[] indegree;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<>();
    }

    /**
     * 根据指定的结点数v和边数E，生成随机的加权有向图
     *
     * @param V 结点数V
     * @param E 边数E
     */
    public EdgeWeightedDigraph(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    /**
     * 根据输入流构建加权有向图
     *
     * @param in 输入流
     */
    public EdgeWeightedDigraph(In in) {
        this.V = in.readInt();
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
        this.V = G.V();
        this.E = G.E();
        for (int v = 0; v < this.V; v++) {
            Stack<DirectedEdge> reverse = new Stack<>();
            for (DirectedEdge e : G.adj(v))
                reverse.push(e);
            for (DirectedEdge e : reverse)
                adj[v].add(e);
        }
    }

    /**
     * 添加指定边
     *
     * @param e 指定边
     */
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    /**
     * 返回结点数量
     *
     * @return 结点数量
     */
    public int V() {
        return V;
    }

    /**
     * 返回有向边的数量
     *
     * @return 有向边数量
     */
    public int E() {
        return E;
    }

    /**
     * 返回从指定结点指出的边
     *
     * @param v 指定结点
     * @return 指定结点指出的边
     */
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * 返回指定结点的出度
     *
     * @param v 指定结点
     * @return 指定结点的出度
     */
    public int outdegree(int v) {
        return adj[v].size();
    }

    /**
     * 返回指定结点的入度
     *
     * @param v 指定结点
     * @return 指定结点的入度
     */
    public int indegreee(int v) {
        return indegree[v];
    }

    /**
     * 返回所有边
     *
     * @return 所有边
     */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edges = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v))
                edges.add(e);
        }
        return edges;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G);
    }
}
