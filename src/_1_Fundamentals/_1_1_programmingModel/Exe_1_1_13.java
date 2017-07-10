package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * exercise 1.1.13
 */
public class Exe_1_1_13 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StdOut.println("请输入行数：");
        int row = in.nextInt();
        StdOut.println("请输入列数：");
        int line = in.nextInt();
        int[][] array = getIntTwoDimensionalArray(row,line);
        //转置数组
        int[][] tranArray = new int[array[0].length][array.length];
        //打印原始二维数组，同时转置
        StdOut.println("原始数组：");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                //打印原始数组
                StdOut.printf("%4d", array[i][j]);
                //转置
                tranArray[j][i] = array[i][j];
            }
            StdOut.println();
        }
        StdOut.println();
        StdOut.println("转置数组：");
        for (int i = 0; i < tranArray.length; i++) {
            for (int j = 0; j < tranArray[i].length; j++) {
                //打印转置数组
                StdOut.printf("%4d", tranArray[i][j]);
            }
            StdOut.println();
        }
        in.close();
    }

    /**
     * 随机获取一个二维数组
     * @param row 行数
     * @param line 列数
     * @return 二维数组
     */
    private static int[][] getIntTwoDimensionalArray(int row, int line){
        int[][] array = new int[row][line];
        for (int i = 0; i < row; i++) {
            for (int j =0; j < line; j++) {
                array[i][j] =StdRandom.uniform(10);
            }
        }
        return array;
    }
}
