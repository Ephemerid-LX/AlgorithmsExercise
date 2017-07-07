package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * exercise 1.1.11
 * 目前只有行和列都小于10时，是整齐的
 */
public class Exc_1_1_11 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int line = in.nextInt();
        boolean[][] array = getBooleanTwoDimensionalArray(row,line);
        String s = " ";
        for (int j = 0; j < array[0].length; j++) {
            s += " "+(j+1);
        }
        s += "\n";

        for (int i = 0; i < array.length; i++) {
            s = s+ (i+1);
            for (int j = 0; j < array[i].length; j++) {

                if (array[i][j] ) s = s + " *";
                else s = s + "  ";
            }
            s = s + "\n";
        }

        StdOut.println(s);
    }


    /**
     * 随机生成二维布尔数组
     * @param row 行数
     * @param line 列数
     * @return 随机的布尔数组
     */
    private static boolean[][] getBooleanTwoDimensionalArray(int row, int line){
        boolean[][] array = new boolean[row][line];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j< line; j++) {
                if (0.5 > StdRandom.uniform(2)) array[i][j] = false;
                else array[i][j] = true;
            }
        }
        return array;
    }
}
