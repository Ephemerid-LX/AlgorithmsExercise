package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * exercise 1.1.15
 */
public class Exe_1_1_15 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int l = in.nextInt();
        int[] a = randomArray(M,l);
        int[] b = histogram(a,M);

        int sumb = 0;
        for (int i = 0; i < b.length; i++){
            sumb += b[i];
        }
        StdOut.print("返回数组b[]的元素和:");
        StdOut.println(sumb);
        StdOut.print("数组a[]的长度:");
        StdOut.println(a.length);
    }

    /**
     * 题目描述内容：
     *  接受一个整型数组a[]和一个整数M，返回一个大小为M的数组，
     *  其中第i个元素的值为整数i在参数数组中出现的次数。如果a[]
     *  中的值均在0到M-1之间,返回数值中所有元素之和应该和a.length
     *  相等。
     * @param a 整型数组
     * @param M 整数
     * @return 数组
     */
    public static int[] histogram(int[] a, int M){
        int[] b = new int[M];
        for (int i = 0; i < M; i++) {
            b[i] = times(a,i);
        }
        return b;
    }


    /**
     * 计算整数x在,数组a中出现出的次数
     * @param a 数组
     * @param x 整数
     * @return 次数
     */
    private static int times(int[] a, int x){
        int times = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) times++;
        }
        return  times;
    }

    /**
     * 获取一个随机的整型数组,
     * 数组大小为0~l-1,
     * 元素大小为0~k-1
     * @param l 数组最大长度
     * @param k 整数
     * @return 整型数组
     */
    private static int[] randomArray(int k, int l){
        int[] array = new int[StdRandom.uniform(l)];
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(k);
        }
        return array;
    }
}
