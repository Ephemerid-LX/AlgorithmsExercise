package _4_Graphs._4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac Cycle.java
 *  Execution:    java  Cycle filename.txt
 *  Dependencies: Graph.java Stack.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  Identifies a cycle.
 *  Runs in O(E + V) time.
 *
 *  % java Cycle tinyG.txt
 *  3 4 5 3
 *
 *  % java Cycle mediumG.txt
 *  15 0 225 15
 *
 *  % java Cycle largeG.txt
 *  996673 762 840164 4619 785187 194717 996673
 *
 ******************************************************************************/
public class Cycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public Cycle(Graph G){
        if (hasSelfLoop(G)) return;
        if (hasParallelEdges(G)) return;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        // 对每一个结点进行深度优先搜索，是否能找到到达自己的环路
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                // 中间参数传入的是父节点，由于起点没有父节点，所以传入一个不可能的值
                dfs(G, -1, v);
            }
        }
    }

    // 是否自环
    private boolean hasSelfLoop(Graph G){
        // 循环每一个结点
        for (int v = 0; v < G.V(); v++) {
            // 查找每个相邻的结点是否有和该结点一样的，有则自环
            for (int w : G.adj(v)) {
                if (v == w) {
                    cycle = new Stack<>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;
    }

    // 是否有平行边
    private boolean hasParallelEdges(Graph G){
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++) {

            for (int w : G.adj(v)) {
                // todo: 无平行边的时候也有这种情况？
                if (marked[v]) {
                    cycle = new Stack<>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 图G是否有环
     * @return 有环:true;无环:false;
     */
    public boolean hasCycle(){
        return cycle != null;
    }

    /**
     * 返回在图中的环
     * @return 图中的环
     */
    public Iterable<Integer> cycle(){
        return cycle;
    }

    private void dfs(Graph G, int u, int v){
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return;

            if (!marked[w]) { // 如果未标记，则继续
                edgeTo[w] = v;
                dfs(G, v, w);
            } else if (u != w){ // 如果标记过，则说明出现环路，则开始找环路
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }

    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Graph G = new Graph(in);
        Cycle finder = new Cycle(G);

        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("Graph is acyclic");
        }
    }

}
