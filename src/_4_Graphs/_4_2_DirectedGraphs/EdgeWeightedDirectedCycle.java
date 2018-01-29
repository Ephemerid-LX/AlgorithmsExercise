package _4_Graphs._4_2_DirectedGraphs;

import _1_Fundamentals._1_3_BagQueuesAndStacks.Stack;
import _4_Graphs._4_4_ShortestPaths.DirectedEdge;
import _4_Graphs._4_4_ShortestPaths.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


/******************************************************************************
 *  Compilation:  javac EdgeWeightedDirectedCycle.java
 *  Execution:    java EdgeWeightedDirectedCycle V E F
 *  Dependencies: EdgeWeightedDigraph.java DirectedEdge.java Stack.java
 *
 *  Finds a directed cycle in an edge-weighted digraph.
 *  Runs in O(E + V) time.
 *
 ******************************************************************************/
public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for(int v = 0; v < G.V(); v++) {
            if(!marked[v] && cycle != null) dfs(G, v);
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        onStack[v] = true;
        marked[v] = true;

        for(DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if(cycle != null) return;

            if(!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            }

            if(onStack[w]) {
                cycle = new Stack<>();
                DirectedEdge f;
                for(f = e; f.from() != w; f = edgeTo[f.from()])
                    cycle.push(e);
                cycle.push(f);
                return;
            }
            onStack[v] = false;
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    public static void main(String[] args) {

        // create random DAG with V vertices and E edges; then add F random edges
        int V = Integer.parseInt(args[0]);
        int E = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        int[] vertices = new int[V];
        for(int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for(int i = 0; i < E; i++) {
            int v, w;
            do {
                v = StdRandom.uniform(V);
                w = StdRandom.uniform(V);
            } while(v >= w);
            double weight = StdRandom.uniform();
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        // add F extra edges
        for(int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = StdRandom.uniform(0.0, 1.0);
            G.addEdge(new DirectedEdge(v, w, weight));
        }

        StdOut.println(G);

        // find a directed cycle
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if(finder.hasCycle()) {
            StdOut.print("Cycle: ");
            for(DirectedEdge e : finder.cycle()) {
                StdOut.print(e + " ");
            }
            StdOut.println();
        }

        // or give topologial sort
        else {
            StdOut.println("No directed cycle");
        }
    }
}
