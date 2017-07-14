package _1_Fundamentals._1_3_BagQueuesAndStacks;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Creative Problems
 * exercise 1.3.37
 * 人编号队列,依次存入临时队列,遇到 M 的倍数,进入输入队列;循环;
 * 最后剩余次数带进下次循环中
 * 直到人编号队列size为1
 */
public class Josephus {
    public static Queue<Integer> survive(int n, int m){
        Queue<Integer> queue = new Queue<>();
        Queue<Integer> temp = new Queue<>();
        Queue<Integer> result = new Queue<>();
        int remainder = 0;
        int length;
        for (int i = 0; i < n; i++){
            queue.enqueue(i);
        }

        while (queue.size() > 1){
            length = queue.size();
            for (int i = 0; i < length; i++) {
                if ((remainder + i + 1 ) % m == 0)
                    result.enqueue(queue.dequeue());
                else
                    temp.enqueue(queue.dequeue());
            }
            remainder = m - (remainder + length) % m;
            queue = temp;
        }
        result.enqueue(queue.dequeue());
        return result;
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StdOut.println(survive(n,m));
    }
}
