package _4_Graphs._4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import javax.naming.directory.SearchControls;

/**
 *
 */
public class DepthFirstSearch {
    private boolean[] marked; // 是否能够到达s
    private int count;        // 与s相通的顶点的数量

    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    // 路径有限搜索
    private void dfs(Graph G, int v){
        count++;
        marked[v] = true;
        for (int w : G.adj(v)){
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * 顶点v和起点s之间是否有路径
     * @param v 顶点v
     * @return 有路径:true;无路径:true
     */
    public boolean marked(int v){
        validateVertex(v);
        return marked[v];
    }

    /**
     * 返回能够到达起点s的顶点数量
     * @return 能够到达起点s的顶点数量
     */
    public int count(){
        return count;
    }

    // 指定参数是否合理(图中的顶点)
    private void validateVertex(int v){
        int V = marked.length;
        if (v < 0 || v > V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.count != G.V()) StdOut.println("NOT connected");
        else StdOut.println("connected");
    }
}
