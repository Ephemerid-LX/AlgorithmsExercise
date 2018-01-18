package _4_Graphs._4_2_DirectedGraphs;

import edu.princeton.cs.algs4.*;

import java.util.Iterator;

/******************************************************************************
 *  Compilation:  javac BreadthFirstDirectedPaths.java
 *  Execution:    java BreadthFirstDirectedPaths digraph.txt s
 *  Dependencies: Digraph.java Queue.java Stack.java
 *  Data files:   https://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/mediumDG.txt
 *                https://algs4.cs.princeton.edu/42digraph/largeDG.txt
 *
 *  Run breadth-first search on a digraph.
 *  Runs in O(E + V) time.
 *
 *  % java BreadthFirstDirectedPaths tinyDG.txt 3
 *  3 to 0 (2):  3->2->0
 *  3 to 1 (3):  3->2->0->1
 *  3 to 2 (1):  3->2
 *  3 to 3 (0):  3
 *  3 to 4 (2):  3->5->4
 *  3 to 5 (1):  3->5
 *  3 to 6 (-):  not connected
 *  3 to 7 (-):  not connected
 *  3 to 8 (-):  not connected
 *  3 to 9 (-):  not connected
 *  3 to 10 (-):  not connected
 *  3 to 11 (-):  not connected
 *  3 to 12 (-):  not connected
 *
 ******************************************************************************/
public class BreadthFirstDirectedPaths {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    /**
     * 单一起点的构造函数
     *
     * @param G 图
     * @param s 起点
     */
    public BreadthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        for (int i = 0; i < G.V(); i++)
            distTo[i] = INFINITY;
        validateVertex(s);
        bfs(G, s);
    }

    /**
     * 多起点的构造函数
     *
     * @param G 图
     * @param sources 起点
     */
    public BreadthFirstDirectedPaths(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        for (int i = 0; i < G.V(); i++)
            distTo[i] = INFINITY;
        bfs(G, sources);
    }

    private void bfs(Digraph G, int s) {
        Queue<Integer> q = new Queue<>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (! q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (! marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    q.enqueue(w);
                }
            }
        }
    }

    private void bfs(Digraph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }

        while (! q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (! marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     * 是否有从起点s到达结点v的路径
     *
     * @param v 结点v
     * @return 有:true; 没有:false
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * 从起点s到结点v的路径
     *
     * @param v 结点v
     * @return 起点s到结点v的路径
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

    /**
     * 返回起点s到结点v的距离
     *
     * @param v 结点v
     * @return 起点s到结点v的距离
     */
    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public static void main(String[] args) {
        // args[0]: file of graph
        // args[1]: source of the graph
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        int s = Integer.parseInt(args[1]);
        BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d): ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v))
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-> " + x);
                StdOut.println();
            } else {
                StdOut.printf("%d to %d (-): not connected\n", s, v);
            }
        }
    }
}
