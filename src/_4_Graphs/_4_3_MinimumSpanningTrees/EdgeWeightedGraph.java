package _4_Graphs._4_3_MinimumSpanningTrees;

import _1_Fundamentals._1_3_BagQueuesAndStacks.Bag;
import _1_Fundamentals._1_3_BagQueuesAndStacks.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *  Compilation:  javac EdgeWeightedGraph.java
 *  Execution:    java EdgeWeightedGraph filename.txt
 *  Dependencies: Bag.java Edge.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/43mst/tinyEWG.txt
 *                https://algs4.cs.princeton.edu/43mst/mediumEWG.txt
 *                https://algs4.cs.princeton.edu/43mst/largeEWG.txt
 *
 *  An edge-weighted undirected graph, implemented using adjacency lists.
 *  Parallel edges and self-loops are permitted.
 *
 *  % java EdgeWeightedGraph tinyEWG.txt
 *  8 16
 *  0: 6-0 0.58000  0-2 0.26000  0-4 0.38000  0-7 0.16000
 *  1: 1-3 0.29000  1-2 0.36000  1-7 0.19000  1-5 0.32000
 *  2: 6-2 0.40000  2-7 0.34000  1-2 0.36000  0-2 0.26000  2-3 0.17000
 *  3: 3-6 0.52000  1-3 0.29000  2-3 0.17000
 *  4: 6-4 0.93000  0-4 0.38000  4-7 0.37000  4-5 0.35000
 *  5: 1-5 0.32000  5-7 0.28000  4-5 0.35000
 *  6: 6-4 0.93000  6-0 0.58000  3-6 0.52000  6-2 0.40000
 *  7: 2-7 0.34000  1-7 0.19000  0-7 0.16000  5-7 0.28000  4-7 0.37000
 *
 * 加权图
 ******************************************************************************/
public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private int V;
    private int E;
    private Bag<Edge>[] adj;

    /**
     *
     * @param V
     */
    public EdgeWeightedGraph(int V){
        if ( V < 0 ) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();
    }

    /**
     *
     * @param V
     * @param E
     */
    public EdgeWeightedGraph(int V, int E){
        this(V);
        this.E = E;
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = Math.round( 100*  StdRandom.uniform()) / 100.0;
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    /**
     *
     * @param in
     */
    public EdgeWeightedGraph(In in){
        this(in.readInt());
        this.E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    /**
     *
     * @param G
     */
    public EdgeWeightedGraph(EdgeWeightedGraph G){
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < V; v++) {
            Stack<Edge> reserve = new Stack<>();
            for (Edge e : G.adj(v)) {
                reserve.push(e);
            }

            for (Edge e : reserve) {
                adj[v].add(e);
            }
        }
    }

    /**
     *
     * @return
     */
    public int V(){
        return this.V;
    }

    /**
     *
     * @return
     */
    public int E(){
        return this.E;
    }


    /**
     *
     * @param v
     * @return
     */
    public Iterable<Edge> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    public void addEdge(Edge e){
        int v = e.either(), w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        //E++;
    }

    private void validateVertex(int v){
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is not between 0 and " + (V-1));
    }

    /**
     *
     * @param v
     * @return
     */
    public int degree(int v){
       validateVertex(v);
       return adj[v].size();
    }

    public Iterable<Edge> edges(){
        Bag<Edge> list = new Bag<>();
        for (int v = 0; v < V; v++ ) {
            int selfLoops = 0;
            for (Edge e : adj[v]) {
                if (v < e.other(v)) list.add(e);
                else if (v == e.other(v))
                    if (selfLoops % 2 == 0) list.add(e);
                selfLoops++;
            }
        }
        return list;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        StdOut.println(G);
    }

}
