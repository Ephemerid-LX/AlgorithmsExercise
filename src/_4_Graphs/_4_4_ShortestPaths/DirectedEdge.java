package _4_Graphs._4_4_ShortestPaths;

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac DirectedEdge.java
 *  Execution:    java DirectedEdge
 *  Dependencies: StdOut.java
 *
 *  Immutable weighted directed edge.
 *
 * 加权有向边
 ******************************************************************************/
public class DirectedEdge {
    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (w < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 返回起点
     *
     * @return 起点
     */
    public int from() {
        return v;
    }

    /**
     * 返回终点
     *
     * @return 终点
     */
    public int to() {
        return w;
    }

    /**
     * 返回权重
     *
     * @return 权重
     */
    public double weight() {
        return weight;
    }

    public String toString() {
        return v + "->" + w + String.format("%5.2f", weight);
    }

    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(2, 3, 5.5);
        StdOut.println(e);
    }
}
