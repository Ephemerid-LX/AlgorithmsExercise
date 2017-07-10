package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * exercise 1.1.31
 */
public class Exe_1_1_31 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StdOut.println("请输入需要点的个数：");
        int N = in.nextInt();
        StdOut.println("请输入概率(0~1):");
        double p = in.nextDouble();

        draw(N,p);
        in.close();
    }

    public static void draw(int N, double p){
        StdDraw.setCanvasSize(1024,1024);
        StdDraw.setScale(-1.1,1.1);
        StdDraw.setPenRadius(0.05);
        //画点
        double[][] coordinate = new double[N][2];
        for (int i = 0; i < N; i++) {
            coordinate[i][0] = Math.cos(2*Math.PI*i/N);
            coordinate[i][1] = Math.sin(2*Math.PI*i/N);
            StdDraw.point(coordinate[i][0], coordinate[i][1]);
        }
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.GRAY);
        //画线
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (StdRandom.bernoulli(p)) {
                    StdDraw.line(
                            coordinate[i][0],coordinate[i][1],
                            coordinate[j][0],coordinate[j][1]
                    );
                }
            }
        }

    }
}
