package _1_Fundamentals._1_3_BagQueuesAndStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 算法 1.3
 *      链表实现Queue
 */
public class Queue<Item> {
    private Node first;//最早添加的结点
    private Node last;//最晚添加的结点
    private int n;

    private class Node{
        private Item item;
        private Node next;
    }

    //入列，添加到队尾
    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) first = last;//如果队列本来就是空的，入队后first和last指向同一结点
        else oldLast.next = last;
        n++;
    }
    //出列，从队首取出
    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;// 如最后一个元素出列，则last也为null
        n--;
        return item;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size (){
        return n;
    }

//    //测试代码
//    public static void main(String[] args){
//        Queue queue = new Queue();
//        In in = new In("src/_1_Fundamentals/_1_3_BagQueuesAndStacks/tobe.txt");
//        while (!in.isEmpty()) {
//            String s = in.readString();
//            if (!s.equals("-"))
//                queue.enqueue(s);
//            else if (!queue.isEmpty())
//                StdOut.print(queue.dequeue() + " ");
//        }
//        StdOut.println("(" + queue.size() + " left on queue)");
//    }
}
