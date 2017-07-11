package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * exercise 1.1.36 && 1.1.37
 */
public class ShuffleTest {
    public static void main(String[] args){
        StdOut.println("请输入整数M:");
        int M = StdIn.readInt();
        StdOut.println("请输入整数N:");
        int N = StdIn.readInt();
        printShuffle(M,N);
        printShuffle2(M,N);
    }

    /**
     * 1.1.36
     * @param M 数组大小
     * @param N 乱序次数
     */
    public static void printShuffle(int M, int N) {
        int[] a = new int[M];
        int[][] b = new int[M][M];
        int[][] c = new int[M][M];
        for (int k = 0; k < N; k++) {

            for (int l = 0; l < M; l++) {
                a[l] = l;
            }

            StdRandom.shuffle(a);

            // todo
            //此处的两种形式如何理解
            //两种结果只是产生互为转置矩阵的结果
            for (int i = 0; i < M; i++) {
                //
                b[i][indexOf(a, i)]++;
                //
                c[i][a[i]]++;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++)
                StdOut.printf("%-10d", b[i][j]);
            StdOut.println();
        }
        StdOut.println();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++)
                StdOut.printf("%-10d", c[i][j]);
            StdOut.println();
        }
        StdOut.println();
    }

    /**
     * 1.1.37
     * @param M 数组大小
     * @param N 乱序次数
     */
    public static void printShuffle2(int M, int N) {
        int[] a = new int[M];
        int[][] b = new int[M][M];
        for (int k = 0; k < N; k++) {

            for (int l = 0; l < M; l++) {
                a[l] = l;
            }

            for (int j = 0; j < M; j++) {
                int r = StdRandom.uniform(M);     // [0,M-1)
                int temp = a[j];
                a[j] = a[r];
                a[r] = temp;
            }
            for (int i = 0; i < M; i++) {
                b[i][indexOf(a, i)]++;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++)
                StdOut.printf("%-10d", b[i][j]);
            StdOut.println();
        }
    }

    public static int indexOf(int[]a ,int key){
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i;
        }
        return -1;
    }
}
