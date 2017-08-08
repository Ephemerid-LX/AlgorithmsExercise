package _2_Sorting._2_5_SortingApplications;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/******************************************************************************
 *  Compilation:  javac SPT.java
 *  Execution:    java SPT < jobs.txt
 *  Dependencies: Job.java
 *
 *  Given a set of jobs and processing times, find a schedule
 *  that minimizes the average completion time of jobs.
 *
 *****************************************************************************
 *
 * creative problem 2.5.12
 * todo:为什么升序排列可以达到这样的效果？
 *
 */
public class SPT {
    public static void main(String[] args){
        // 任务数量
        int n = StdIn.readInt();
        Job[] jobs = new Job[n];

        // 创建任务
        for (int i = 0; i < n; i++){
            String name = StdIn.readString();
            double time = StdIn.readDouble();
            jobs[i] = new Job(name, time);
        }

        // 排序
        Arrays.sort(jobs);

        // 打印
        for (int i = 0; i < n; i++)
            StdOut.println(jobs[i]);
    }
}
