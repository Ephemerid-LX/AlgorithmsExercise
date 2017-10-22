package _4_Graphs._4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.Queue;

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] = 是否有路径s-v.
    private int[] edgeTo; // edgeTo[v] = s-v路径上的父结点
    private int[] distTo; // distTo[v] = 最短路径s-v上的结点数量

    public BreadthFirstPaths(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);
    }

    public BreadthFirstPaths(Graph G, Integer[] sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        validateVertices(sources);
    }

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
}

