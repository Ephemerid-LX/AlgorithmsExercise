package _4_Graphs._4_3_MinimumSpanningTrees;

import edu.princeton.cs.algs4.StdOut;

/**
 * 带权重的边
 */
public class Edge implements Comparable<Edge> {
    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 返回任意一个顶点
     *
     * @return 任意一个顶点
     */
    public int either() {
        return v;
    }

    /**
     * 返回指定顶点的另外一个顶点
     *
     * @param vertex 指定
     * @return 另外一个顶点
     */
    public int other(int vertex) {
        if (this.v == vertex) return w;
        else if (this.w == vertex) return v;
        else throw new IllegalArgumentException("Illegal  endpoint");
    }

    /**
     * 返回权重
     *
     * @return 权重
     */
    public double weight() {
        return weight;
    }


    /**
     * 比较边的大小，如果此边大于指定边，则返回1; 小于，则返回-1；否则返回0;
     *
     * @param o 另外一边
     * @return 如果此边大于指定边，则返回1; 小于，则返回-1；否则返回0;
     */
    @Override
    public int compareTo(Edge o) {
        if (this.weight > o.weight) return 1;
        else if (this.weight < o.weight) return - 1;
        return 0;
    }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    public static void main(String[] args) {
        Edge e = new Edge(2, 3, 4.3);
        StdOut.println(e);
    }
}
