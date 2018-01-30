package _4_Graphs._4_4_ShortestPaths;

import _4_Graphs._4_2_DirectedGraphs.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac BellmanFordSP.java
 *  Execution:    java BellmanFordSP filename.txt s
 *  Dependencies: EdgeWeightedDigraph.java DirectedEdge.java Queue.java
 *                EdgeWeightedDirectedCycle.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/tinyEWDn.txt
 *                https://algs4.cs.princeton.edu/44sp/mediumEWDnc.txt
 *
 *  Bellman-Ford shortest path algorithm. Computes the shortest path tree in
 *  edge-weighted digraph G from vertex s, or finds a negative cost cycle
 *  reachable from s.
 *
 *  % java BellmanFordSP tinyEWDn.txt 0
 *  0 to 0 ( 0.00)
 *  0 to 1 ( 0.93)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   6->4 -1.25   4->5  0.35   5->1  0.32
 *  0 to 2 ( 0.26)  0->2  0.26
 *  0 to 3 ( 0.99)  0->2  0.26   2->7  0.34   7->3  0.39
 *  0 to 4 ( 0.26)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   6->4 -1.25
 *  0 to 5 ( 0.61)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52   6->4 -1.25   4->5  0.35
 *  0 to 6 ( 1.51)  0->2  0.26   2->7  0.34   7->3  0.39   3->6  0.52
 *  0 to 7 ( 0.60)  0->2  0.26   2->7  0.34
 *
 *  % java BellmanFordSP tinyEWDnc.txt 0
 *  4->5  0.35
 *  5->4 -0.66
 *
 * BellmanFordSP算法
 ******************************************************************************/
public class BellmanFordSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private Queue<Integer> queue;
    private boolean[] onQueue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];

        validateVertex(s);
        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;

        queue = new Queue<>();
        queue.enqueue(s);
        onQueue[s] = true;
        while(!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for(DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if(!onQueue[w]) {
                    onQueue[w] = true;
                    queue.enqueue(w);
                }
            }

            // TODO:为什么是这个条件
            if(cost++ % G.V() == 0) {
                findNegativeCycle();
                if(hasNegativeCycle()) return;
            }

        }
    }

    /**
     * 返回是否有负权重环
     *
     * @return 有:true;没有:false
     */
    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    /**
     * 返回负权重环
     *
     * @return 负权重环
     */
    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    /**
     * 检查负权重环
     */
    public void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for(DirectedEdge e : edgeTo)
            if(e != null) spt.addEdge(e);
//        for(int v =0; v < V; v++) {
//            if(edgeTo[v] != null)
//                spt.addEdge(edgeTo[v]);
//        }
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }


    private void validateVertex(int v) {
        int V = distTo.length;
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }


    public double distTo(int v) {
        validateVertex(v);
        if(hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if(hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        BellmanFordSP sp = new BellmanFordSP(G, s);

        // print negative cycle
        if(sp.hasNegativeCycle()) {
            for(DirectedEdge e : sp.negativeCycle())
                StdOut.println(e);
        }

        // print shortest paths
        else {
            for(int v = 0; v < G.V(); v++) {
                if(sp.hasPathTo(v)) {
                    StdOut.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
                    for(DirectedEdge e : sp.pathTo(v)) {
                        StdOut.print(e + "   ");
                    }
                    StdOut.println();
                } else {
                    StdOut.printf("%d to %d           no path\n", s, v);
                }
            }
        }

    }
}
