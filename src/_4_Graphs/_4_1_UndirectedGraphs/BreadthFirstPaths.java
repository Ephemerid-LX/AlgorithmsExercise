package _4_Graphs._4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac BreadthFirstPaths.java
 *  Execution:    java BreadthFirstPaths G s
 *  Dependencies: Graph.java Queue.java Stack.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyCG.txt
 *                https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  Run breadth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *
 *  %  java Graph tinyCG.txt
 *  6 8
 *  0: 2 1 5
 *  1: 0 2
 *  2: 0 1 3 4
 *  3: 5 4 2
 *  4: 3 2
 *  5: 3 0
 *
 *  %  java BreadthFirstPaths tinyCG.txt 0
 *  0 to 0 (0):  0
 *  0 to 1 (1):  0-1
 *  0 to 2 (1):  0-2
 *  0 to 3 (2):  0-2-3
 *  0 to 4 (2):  0-2-4
 *  0 to 5 (1):  0-5
 *
 *  %  java BreadthFirstPaths largeG.txt 0
 *  0 to 0 (0):  0
 *  0 to 1 (418):  0-932942-474885-82707-879889-971961-...
 *  0 to 2 (323):  0-460790-53370-594358-780059-287921-...
 *  0 to 3 (168):  0-713461-75230-953125-568284-350405-...
 *  0 to 4 (144):  0-460790-53370-310931-440226-380102-...
 *  0 to 5 (566):  0-932942-474885-82707-879889-971961-...
 *  0 to 6 (349):  0-932942-474885-82707-879889-971961-...
 *
 ******************************************************************************/
public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] = 是否有路径s-v.
    private int[] edgeTo; // edgeTo[v] = s-v路径上的父结点
    private int[] distTo; // distTo[v] = 最短路径s-v上的结点数量

    // 计算单个起点
    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);
    }

    // 计算多个起点
    public BreadthFirstPaths(Graph G, Integer[] sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        validateVertices(sources);
    }

    // 单个起点
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int w = q.dequeue();
            for (int v : G.adj(w)) {
                if (!marked[v]) {
                    marked[v] = true;
                    edgeTo[v] = w;
                    distTo[v] = distTo[w] + 1;
                    q.enqueue(v);
                }
            }
        }
    }

    // 多个起点
    private void bfs(Graph G, Integer[] sources) {
        Queue<Integer> q = new Queue<>();
        for (int s : sources){
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }

        while (!q.isEmpty()){
            int w = q.dequeue();
            for (int v : G.adj(w)) {
                if (!marked[v]) {
                    marked[v] = true;
                    edgeTo[v] = w;
                    distTo[v] = distTo[w] + 1;
                    q.enqueue(v);
                }
            }
        }
    }

    /**
     * 是否有v到s的路径
     * @param v 结点v
     * @return 有:true;无:true
     */
    public boolean hasPathTo(int v){
        validateVertex(v);
        return marked[v];
    }

    /**
     * 从v到s的路径上有多少结点
     * @param v v结点
     * @return v到s路径上的结点数量
     */
    public int distTo(int v){
        validateVertex(v);
        return distTo[v];
    }

    /**
     * 返回v到s路径上的所有结点
     * @param v 结点v
     * @return v到s路径上的所有结点
     */
    public Iterable<Integer> pathTo(int v){
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]){
            path.push(x);
        }
        path.push(x);
        return path;
    }

    public boolean check(Graph G, int s){

        // check that the distance of s = 0
        // 起点到起点的距离应该是0
        if (distTo[s] != 0) {
            StdOut.println("distance of source " + s + " to itself = " + distTo[s]);
            return false;
        }

        // check that for each edge v-w dist[w] <= dist[v] + 1
        // provided v is reachable from s
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                // 相邻的结点应该都能够到达起点s
                if (hasPathTo(v) != hasPathTo(w)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    StdOut.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                }

                // 相邻的结点之间的距离应该是1
                if (hasPathTo(v) && (distTo[v] > distTo[w]+1)) {
                    StdOut.println("edge " + v + "-" + w);
                    StdOut.println("distTo[" + v + "] = " + distTo[v]);
                    StdOut.println("distTo[" + w + "] = " + distTo[w]);
                }
            }
        }

        // check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
        // provided v is reachable from s
        // 相邻结点之间的距离应该是1
        for (int w = 0; w < G.V(); w++) {
            if (!hasPathTo(w) || w == s) continue;
            int v = edgeTo[w];
            if (distTo[w] != distTo[v]+1) {
                StdOut.println("shortest path edge " + v + "-" + w);
                StdOut.println("distTo[" + v + "] = " + distTo[v]);
                StdOut.println("distTo[" + w + "] = " + distTo[w]);
            }
        }

        return true;
    }

    // 判断结点是否合理
    private void validateVertex(int v) {
        if (v < 0 || v >= marked.length)
            throw new IllegalArgumentException("vertex " + v + "is not between 0 and " + (marked.length - 1));
    }

    private void validateVertices(Integer[] vertices) {
        if (vertices == null)
            throw new IllegalArgumentException("argument is null");
        int V = marked.length;
        for (int v : vertices){
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    public static void main(String[] args){
        // args[0] = $PROJECT_HOME/algs4-data/tinyG.txt
        In in = new In(args[0]);
        Graph G = new Graph(in);
        // StdOut.println(G);

        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v ++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d): ", s ,v , bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else StdOut.print("-" + x);
                }
                StdOut.println();
            }else{
                StdOut.printf("%d to %d (-): not connected\n", s, v);
            }
        }
    }
}

