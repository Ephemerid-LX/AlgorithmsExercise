package _2_Sorting._2_5_SortingApplications;

import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;


/**
 * 处理器
 */
public class Processor implements Comparable<Processor>{
    private Queue<Job> jobs;
    private double totalTime;
    private int n;

    public Processor(){
        jobs = new Queue<Job>();
        totalTime = 0;
        int n = 0;
    }

    /**
     * 有没有任务
     * @return 没有任务返回true,否则返回false
     */
    public boolean isEmpty(){
        return n == 0;
    }

    /**
     * 返回任务数量
     * @return 任务数量
     */
    public int amount(){
        return n;
    }

    /**
     * 添加一个任务
     * @param job 任务
     */
    public void add(Job job){
        jobs.enqueue(job);
        totalTime += job.getTime();
    }

    public String toString(){
        StringBuffer sb = new StringBuffer("Processor:\n");
        Iterator<Job> it = jobs.iterator();
        while (it.hasNext()){
            sb.append(" ").append(it.next()).append("\n");
        }
        sb.append("totalTime:").append(totalTime).append("\n");
        return sb.toString();
    }

    @Override
    public int compareTo(Processor that) {
        if (this.totalTime < that.totalTime) return -1;
        if (this.totalTime > that.totalTime) return 1;
        return 0;
    }
}
