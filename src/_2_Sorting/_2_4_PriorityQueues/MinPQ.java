package _2_Sorting._2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Compilation:  javac MinPQ.java
 *  Execution:    java MinPQ < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/24pq/tinyPQ.txt
 *
 *  Generic min priority queue implementation with a binary heap.
 *  Can be used with a comparator instead of the natural order.
 *
 *  % java MinPQ < tinyPQ.txt
 *  E A E (6 left on pq)
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *  Can be optimized by replacing full exchanges with half exchanges
 *  (ala insertion sort).
 *
 ******************************************************************************/
public class MinPQ<Key> implements Iterable<Key> {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MinPQ(int capacity){
        pq = (Key[]) new Object[capacity];
        n = 0;
    }

    public MinPQ(){
        this(1);
    }

    public MinPQ(int capacity, Comparator<Key> comparator){
        this.comparator = comparator;
        pq = (Key[]) new Object[capacity];
        n = 0;
    }

    public MinPQ(Comparator<Key> comparator){
        this(1, comparator);
    }

    public MinPQ(Key[] keys){
        n = keys.length;
        pq = (Key[]) new Object[n + 1];
        for (int i = 1; i <= n; i++)
            pq[i] = keys[i];
        for (int k = n/2; k >= 1; k--)
            sink(k);
        assert isMinHeap();
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    /**
     * 返回优先队列中的最小元素
     * @return 优先队列中的最小元素
     */
    public Key min(){
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // 调整内置数组大小
    private void resize(int capacity){
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) temp[i] = pq[i];
        pq = temp;
    }

    /**
     * Adds a new key to this priority queue
     * @param x x the key to add to this priority queue
     */
    public void insert(Key x){
        if (n == pq.length-1) resize(2*pq.length);
        // 添加新元素，并调整其在数组中的位置
        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     * @return a smallest key on this priority queue
     */
    public Key delMin(){
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        pq[1] = pq[n--];
        sink(1);
        pq[n+1] = null;
        // 如果元素小于数组可容纳数量的四分之一，则减小数组大小的二分之一
        if ((n>0) && (n == (pq.length-1)/4)) resize(pq.length/2);
        assert isMinHeap();
        return min;
    }

    /*******************************************************************
     * Hepler functions to restore the heap invariant
     *******************************************************************/
    public void swim(int k){
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    public void sink(int k){
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /*******************************************************************
     * Helper functions for compares and swaps
     *******************************************************************/
    private boolean greater(int i, int j){
        if (comparator == null)
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        else
            return comparator.compare(pq[i], pq[j]) > 0;
    }

    private void exch(int i, int j){
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean isMinHeap(){
        return isMinHeap(1);
    }

    private boolean isMinHeap(int k){
        // 2*k > n 结果应该一样？
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left <= n && greater(k, left)) return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }
    /*******************************************************************
     * Iterator
     *******************************************************************/
    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key>{
        MinPQ copy;

        public HeapIterator(){
            if (comparator == null) copy = new MinPQ(size());
            else copy = new MinPQ(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }
        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return delMin();
        }
    }

    /*******************************************************************
     * Test
     *******************************************************************/
    public static void main(String[] args){
        MinPQ<String> pq = new MinPQ<>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!"-".equals(item)) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMin() + " ");
        }
        StdOut.println("("+ pq.size() + " left on pq)");
    }
}
