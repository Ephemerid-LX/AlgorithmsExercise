package _2_Sorting._2_5_SortingApplications;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/******************************************************************************
 *  Compilation:  javac Frequency.java
 *  Execution:    java Frequency < strings.txt
 *  Dependencies: StdOut.java StdIn.java Record.java
 *
 *  Read strings from standard input and print the number of times
 *  each string occurs, in descending order.
 *
 *  % java Frequency < tale.txt
 *    7515 the
 *    4751 and
 *    4071 of
 *    3458 to
 *    2830 a
 *  ...
 *
 ******************************************************************************
 *
 * exercise 2.5.8
 */
public class Frequency {

    public static void main(String[] args) {
        // 读取输入流中的字符串，并排序
        String[] a = StdIn.readAllStrings();
        int n = a.length;
        Arrays.sort(a);

        // 创建重复字符以及频率
        Record[] records = new Record[n];
        String word = a[0];
        int frequency = 1;  // 记录频率
        int m = 0;          // 作为records的下标
        for (int i = 1; i < n; i++) {
            if(!a[i].equals(word)){
                records[m++] = new Record(word, frequency);
                word = a[i];
                frequency = 0;
            }
            frequency++;
        }
        records[m++] = new Record(word, frequency);

        // 对记录的字符按频率排序
        Arrays.sort(records, 0, m);
        for (int i = m-1; i >= 0; i--)
            StdOut.println(records[i]);
    }
}
