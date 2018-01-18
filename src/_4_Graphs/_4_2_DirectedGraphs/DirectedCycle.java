package _4_Graphs._4_2_DirectedGraphs;

import _1_Fundamentals._1_3_BagQueuesAndStacks.Stack;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac DirectedCycle.java
 *  Execution:    java DirectedCycle input.txt
 *  Dependencies: Digraph.java Stack.java StdOut.java In.java
 *  Data files:   https://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/tinyDAG.txt
 *
 *  Finds a directed cycle in a digraph.
 *  Runs in O(E + V) time.
 *
 *  % java DirectedCycle tinyDG.txt
 *  Directed cycle: 3 5 4 3
 *
 *  %  java DirectedCycle tinyDAG.txt
 *  No directed cycle
 *
 ******************************************************************************/
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph G){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }

    private void dfs(Digraph G, int v){
        onStack[v] = true;
        marked[v] = true;

        for (int w : G.adj(v)) {
            if (cycle != null) return;

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]){
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[v])
                    cycle.push(x);
                cycle.push(v);
                cycle.push(w);
            }
        }
        onStack[v] = false;
    }

    /**
     * 是否有环
     * @return 有环:true; 无环:false
     */
    public boolean hasCycle(){
        return cycle != null;
    }

    /**
     * 返回环
     * @return 环
     */
    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle)
                StdOut.print(v + " ");
            StdOut.println();
        } else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();

    }
}
