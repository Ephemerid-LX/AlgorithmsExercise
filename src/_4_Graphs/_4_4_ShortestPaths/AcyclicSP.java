package _4_Graphs._4_4_ShortestPaths;

import _1_Fundamentals._1_3_BagQueuesAndStacks.Stack;
import _4_Graphs._4_2_DirectedGraphs.Topological;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac AcyclicSP.java
 *  Execution:    java AcyclicSP V E
 *  Dependencies: EdgeWeightedDigraph.java DirectedEdge.java Topological.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/tinyEWDAG.txt
 *
 *  Computes shortest paths in an edge-weighted acyclic digraph.
 *
 *  % java AcyclicSP tinyEWDAG.txt 5
 *  5 to 0 (0.73)  5->4  0.35   4->0  0.38
 *  5 to 1 (0.32)  5->1  0.32
 *  5 to 2 (0.62)  5->7  0.28   7->2  0.34
 *  5 to 3 (0.61)  5->1  0.32   1->3  0.29
 *  5 to 4 (0.35)  5->4  0.35
 *  5 to 5 (0.00)
 *  5 to 6 (1.13)  5->1  0.32   1->3  0.29   3->6  0.52
 *  5 to 7 (0.28)  5->7  0.28
 *
 *  无环加权有向图的改良Dijkstra算法:最短路径
 ******************************************************************************/
public class AcyclicSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0;

        Topological topological = new Topological(G);
        if(!topological.hasOrder())
            throw new IllegalArgumentException("Digraph is not acyclic");

        for(int v : topological.order())
            for(DirectedEdge e : G.adj(v))
                relax(e);
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if(distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if(v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * 是否有到达指定结点v的路径
     *
     * @param v 指定结点v
     * @return 有:true; 否则:false
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    /**
     * 返回到达指定结点v的权重
     *
     * @param v 指定结点v
     * @return 指定结点的权重
     */
    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * 返回到达指定结点v的路径
     *
     * @param v 指定结点v
     * @return 到达v的路径
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        Stack<DirectedEdge> path = new Stack<>();
        if(!hasPathTo(v)) {
            for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
                path.push(e);
            }
        }
        return path;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        // find shortest path from s to each other vertex in DAG
        AcyclicSP sp = new AcyclicSP(G, s);
        for(int v = 0; v < G.V(); v++) {
            if(sp.hasPathTo(v)) {
                StdOut.printf("%d to %d (%.2f)  ", s, v, sp.distTo(v));
                for(DirectedEdge e : sp.pathTo(v)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d         no path\n", s, v);
            }
        }
    }
}
