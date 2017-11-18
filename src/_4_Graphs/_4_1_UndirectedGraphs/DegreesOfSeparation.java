package _4_Graphs._4_1_UndirectedGraphs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class DegreesOfSeparation {

    public static void main(String[] args){
        // the path of the file: ${projectPath}/algs4-data/routes.txt or moves.txt
        // delimiter : " ", /
        // sources of the bfs : JFK      Bacon, Kevin

        // 构建符号图
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        // args[2] 设置起点
        String source = args[2];
        if (!sg.contains(source)) {
            StdOut.println(source + "not in database");
            return;
        }
        int index = sg.indexOf(source);
        // 获取符号图的内建图
        Graph g = sg.graph();
        // 构建深度优先路径
        BreadthFirstPaths bfs = new BreadthFirstPaths(g, index);
        // 以输入为终点，打印起点到终点的路径
        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.indexOf(sink);
                if (bfs.hasPathTo(t)) {
                    for (int v : bfs.pathTo(t)) {
                        StdOut.println("  " + sg.nameOf(v));
                    }
                } else {
                    StdOut.println(sink + " not connected to " + source);
                }
            } else {
                StdOut.println(sink + "not in database");
            }
        }
    }
}
