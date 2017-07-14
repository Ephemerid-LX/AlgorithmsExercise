package _1_Fundamentals._1_3_BagQueuesAndStacks;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * exercise 1.3.14
 * 用数组实现Queue
 * 需要理解当数组在length-1的位置存值后，
 * 数组不一样被占满，因为数组可能从开始处被置为null
 */
public class ResizingArrayQueue<Item> implements Iterable<Item>{
    private Item[] q;
    private int n;
    private int first;
    private int last;

    //初始化
    public ResizingArrayQueue(){
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    // 入列
    public void enqueue(Item item){
        if (n == q.length) resize(2*q.length);
        q[last++] = item;
        n++;
        // 当last == q.length，且数组并没有满时，重头开始[0,first-1]
        if (last == q.length) last = 0;
    }

    //出列
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;
        n--;
        first++;
        // 当first == q.length，且数组并没有满时，重头开始[0,last]
        if (first == q.length) first = 0;
        if (n > 0 && n < q.length/4) resize(q.length/2);
        return item;
    }

    /**
     * 重置数组大小
     * @param capacity 数组大小
     */
    public void resize(int capacity){
        //assert capacity >= n;这里不知道为什么需要这个断言
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            /*
            first + i < q.length ----> [first,q.length-1]
            first + i >= q.length ---->[0,first)
             */
            temp[i] = q[(first+i)%q.length];
        q = temp;
        first = 0;
        last = n;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class ArrayIterator implements Iterator<Item>{
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (isEmpty()) throw new NoSuchElementException();
            return q[(first + i++) % q.length];
        }
    }

    public static void main(String[] args){
        ResizingArrayQueue<String> q = new ResizingArrayQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) q.enqueue(s);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
