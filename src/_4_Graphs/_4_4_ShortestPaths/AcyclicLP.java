package _4_Graphs._4_4_ShortestPaths;

import _1_Fundamentals._1_3_BagQueuesAndStacks.Stack;
import _4_Graphs._4_2_DirectedGraphs.Topological;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac AcyclicLP.java
 *  Execution:    java AcyclicP V E
 *  Dependencies: EdgeWeightedDigraph.java DirectedEdge.java Topological.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/tinyEWDAG.txt
 *
 *  Computes longeset paths in an edge-weighted acyclic digraph.
 *
 *  Remark: should probably check that graph is a DAG before running
 *
 *  % java AcyclicLP tinyEWDAG.txt 5
 *  5 to 0 (2.44)  5->1  0.32   1->3  0.29   3->6  0.52   6->4  0.93   4->0  0.38
 *  5 to 1 (0.32)  5->1  0.32
 *  5 to 2 (2.77)  5->1  0.32   1->3  0.29   3->6  0.52   6->4  0.93   4->7  0.37   7->2  0.34
 *  5 to 3 (0.61)  5->1  0.32   1->3  0.29
 *  5 to 4 (2.06)  5->1  0.32   1->3  0.29   3->6  0.52   6->4  0.93
 *  5 to 5 (0.00)
 *  5 to 6 (1.13)  5->1  0.32   1->3  0.29   3->6  0.52
 *  5 to 7 (2.43)  5->1  0.32   1->3  0.29   3->6  0.52   6->4  0.93   4->7  0.37
 *
 * 无环加权有向图的改良Dijkstra算法
 ******************************************************************************/
public class AcyclicLP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        validateVertex(s);

        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        Topological topological = new Topological(G);
        for(int v : topological.order()) {
            for(DirectedEdge e : G.adj(v)) {
                relax(e);
            }
        }
    }

    // 放松边
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
     * 返回达到指定结点v的权重
     *
     * @param v 指定结点v
     * @return 到达结点v的权重
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int s = Integer.parseInt(args[1]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);

        AcyclicLP lp = new AcyclicLP(G, s);

        for(int v = 0; v < G.V(); v++) {
            if(lp.hasPathTo(v)) {
                StdOut.printf("%d to %d (%.2f)  ", s, v, lp.distTo(v));
                for(DirectedEdge e : lp.pathTo(v)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            } else {
                StdOut.printf("%d to %d         no path\n", s, v);
            }
        }
    }
}
