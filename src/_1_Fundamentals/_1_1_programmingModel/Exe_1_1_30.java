package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;

/**
 * exercise1.1.30
 */
public class Exe_1_1_30 {
    public static void main(String[] args){
        boolean[][] array = new boolean[5][5];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = Exe_1_1_24.euclid(i ,j) == 1 ? true : false;
                StdOut.printf("%-8s", array[i][j]);
            }
            StdOut.println();
        }
    }
}
