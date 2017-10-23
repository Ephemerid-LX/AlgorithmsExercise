package _4_Graphs._4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac CC.java
 *  Execution:    java CC filename.txt
 *  Dependencies: Graph.java StdOut.java Queue.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  Compute connected components using depth first search.
 *  Runs in O(E + V) time.
 *
 *  % java CC tinyG.txt
 *  3 components
 *  0 1 2 3 4 5 6
 *  7 8
 *  9 10 11 12
 *
 *  % java CC mediumG.txt
 *  1 components
 *  0 1 2 3 4 5 6 7 8 9 10 ...
 *
 *  % java -Xss50m CC largeG.txt
 *  1 components
 *  0 1 2 3 4 5 6 7 8 9 10 ...
 *
 *  Note: This implementation uses a recursive DFS. To avoid needing
 *        a potentially very large stack size, replace with a non-recurisve
 *        DFS ala NonrecursiveDFS.java.
 *
 ******************************************************************************/
public class CC {
    private boolean marked[];
    private int count;
    private int[] id;
    private int[] size;

    public CC(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    // 深度优先搜索
    private void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G,w);
            }
        }
    }

    /**
     * 返回结点的连通分量id
     * @param v 结点
     * @return 连通分量
     */
    public int id(int v){
        validateVertex(v);
        return id[v];
    }

    /**
     * 返回结点v所在连通分量结点数量
     * @param v 结点v
     * @return 结点v所在连通分量结点数量
     */
    public int size(int v){
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * 返回连通分量数量
     * @return 连通分量数量
     */
    public int count(){
        return count;
    }

    /**
     * 结点v和结点w是否连通
     * @param v 结点v
     * @param w 结点w
     * @return v和w联通:true;不连通:false
     */
    public boolean connected(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }

    // 结点是否合理
    private void validateVertex(int v){
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is not between 0 and " + (V-1));
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Graph G = new Graph(in);
        CC cc = new CC(G);

        // number of connected components
        int m = cc.count();
        StdOut.println(m + " components");

        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<>();
        }

        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v +" ");
            }
            StdOut.println();
        }
    }
}
