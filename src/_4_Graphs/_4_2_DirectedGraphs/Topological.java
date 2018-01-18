package _4_Graphs._4_2_DirectedGraphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;

/**
 *
 */
public class Topological {
    private Iterable<Integer> order;
    private int[] rank;

    public Topological(Digraph G){
        DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
            rank = new int[G.V()];
            int i = 0;
            for (int v : order) {
                rank[v] = i++;
            }
        }
    }

    /**
     * 返回是否有拓扑排序
     * @return 有: true; 没有: false
     */
    public boolean hasOrder(){
        return order != null;
    }

    /**
     * 返回拓扑排序
     * @return 拓扑排序
     */
    public Iterable<Integer> order(){
        return order;
    }

    /**
     * 返回结点v拓扑排序中的排名
     * @param v 结点v
     * @return 结点v在拓扑排序中的排名
     */
    public int rank(int v){
        validateVertex(v);
        return rank[v];
    }

    private void validateVertex(int v) {
        int V = rank.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args){
        String filename = args[0];
        String delimiter = args[1];

        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        Topological topological = new Topological(sg.digraph());
        for (int v : topological.order)
            StdOut.println(sg.nameOf(v));
    }
}
