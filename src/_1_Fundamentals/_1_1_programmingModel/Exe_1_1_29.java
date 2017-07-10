package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * exercise 1.1.29
 */
public class Exe_1_1_29 {
    public static void main(String[] args){
        // 读取文件
        In in = new In("src/_1_Fundamentals/_1_1_programmingModel/exe_1_1_29.txt");
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);
        int i = rank(4, whitelist);
        int j = count(4, whitelist);

        for (int n = i; n <= i + j - 1; n++) {
            StdOut.println(whitelist[n]);
        }
    }

    /**
     * 返回等于该键的元素的数量
     * @param key 键
     * @param a 数组
     * @return 等于该键的元素的数量
     */
    public static int count(int key, int[] a){
        return Exe_1_1_28.indexOf(a,key).length;
    }

    /**
     * 返回数组中小于该键的元素数量
     * @param key 键
     * @param a 数组
     * @return 小于该键的元素数量
     */
    public static int rank(int key, int[] a){
        int[] count = Exe_1_1_28.indexOf(a, key);
        if (count.length == 0) return 0;
        return count[0];
    }
}
