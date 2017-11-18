package _4_Graphs._4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.*;
//import edu.princeton.cs.algs4.Graph;

/**
 *
 */
public class SymbolGraph {
    private ST<String, Integer> st;// string -> index
    private String[] keys;// index -> string
    private Graph graph;// Graph

    public SymbolGraph(String filename, String delimiter) {
        // 建立索引和反向索引
        st = new ST<>();
        In in = new In(filename);
        // 建立string -> index 索引
        while (! in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (! st.contains(a[i])) st.put(a[i], st.size());
            }
        }
        StdOut.println("Done read " + filename);

        // 建立index - > string 反向索引
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // 构建符号表
        graph = new Graph(st.size());
        in = new In(filename);
        while (! in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                graph.addEdge(v, st.get(a[i]));
            }
        }
    }

    /**
     * 图是否包含结点s
     *
     * @param s 结点s
     * @return 包含
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * 返回结点s的下标
     *
     * @param s 结点s
     * @return 结点s的下标
     */
    public int indexOf(String s) {
        return st.get(s);
    }

    /**
     * 根据下标查找值
     *
     * @param v 下标v
     * @return 结点值
     */
    public String nameOf(int v) {
        return keys[v];
    }

    /**
     * 返回关联的图
     *
     * @return 图
     */
    public Graph graph() {
        return graph;
    }

    public static void main(String[] args) {
        // args[0] : ${projectName}/algs4-data/movies.txt
        // args[1] : /
        String filename = args[0];
        String delimiter = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.indexOf(source);
                for (int v : graph.adj(s)) {
                    StdOut.println("    " + sg.nameOf(v));
                }
            } else {
                StdOut.println("input not contain '" + source + "'");
            }
        }
    }
}

