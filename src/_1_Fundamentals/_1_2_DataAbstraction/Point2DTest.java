package _1_Fundamentals._1_2_DataAbstraction;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

/**
 * exercise 1.2.1
 */
public class Point2DTest {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);

        //画点
        Point2D[] point2DS = new Point2D[n];
        for (int i = 0; i < n; i++) {
            double x = Math.random();
            double y = Math.random();
            Point2D p = new Point2D(x, y);
            point2DS[i] = p;
            StdDraw.setPenRadius(.005);
            p.draw();
        }
        //计算所有的距离
        double[] distance = new double[sum(n-1)];
        int index = 0;
        for (int i = 0; i < n-1; i++)
            for (int j = i+1; j < n; j++)
                distance[index++] = point2DS[i].distanceTo(point2DS[j]);

        double minDis = StdStats.min(distance);
        StdOut.println(minDis);
    }

    /**
     * n+(n-1)+(n-2)+..+1
     * @param n 数
     * @return 结果
     */
    public static int sum(int n) {
        if (n < 0) throw new IllegalArgumentException();
        if (n == 0) return 0;
        int result = 0;
        for (int i = 1; i <= n; i++)
            result += i;
        return result;
    }
}
