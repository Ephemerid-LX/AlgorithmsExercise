package _2_Sorting._2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Compilation:  javac MaxPQ.java
 *  Execution:    java MaxPQ < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/24pq/tinyPQ.txt
 *
 *  Generic max priority queue implementation with a binary heap.
 *  Can be used with a comparator instead of the natural order,
 *  but the generic Key type must still be Comparable.
 *
 *  % java MaxPQ < tinyPQ.txt
 *  Q X P (6 left on pq)
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *  Can be optimized by replacing full exchanges with half exchanges
 *  (ala insertion sort).
 *
 ******************************************************************************
 *
 * 用二叉树实现的优先队列
 *
 */
public class MaxPQ<Key> implements Iterable<Key>{
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MaxPQ(int capacity){
        pq = (Key[]) new Object[capacity+1];
        int n = 0;
    }

    public MaxPQ(){
        this(1);
    }

    public MaxPQ(int capacity, Comparator<Key> comparator){
        this.comparator = comparator;
        pq = (Key[]) new Object[capacity+1];
        int n = 0;
    }

    public MaxPQ(Comparator<Key> comparator){
        this(1, comparator);
    }

    public MaxPQ(Key[] keys) {
        //todo
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    /**
     * 返回队列中最大的值
     * @return 队列中最大的值
     * @throws NoSuchElementException 如果队列为空，则抛出NoSuchElementException
     */
    public Key max(){
        if(isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    // 调整数组容量
    private void resize(int capacity){
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++)
            temp[i] = pq[i];
        pq = temp;
    }

    /**
     * 向优先队列中添加一个元素
     * @param x 添加的新元素
     */
    public void insert(Key x){
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
        assert isMaxHeap();
    }

    /**
     * 删除并返回最大的元素
     * @return 优先队列中最大的元素
     * @throws NoSuchElementException 如有优先队列为空，则抛出改异常
     */
    public Key delMax(){
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key max = pq[1];
        pq[1] = pq[n--];
        sink(1);
        pq[n+1] = null;
        if ((n>0) && (n == (pq.length-1)/4)) resize(pq.length / 2);
        assert isMaxHeap();
        return max;
    }
    /******************************************************
     * Helper functions to restore the heap invariant
     ******************************************************/
    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /******************************************************
     * Helper functions for compares and swaps
     ******************************************************/
    private boolean less(int i, int j){
        if (comparator == null)
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        else
            return comparator.compare(pq[i], pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // 判断是否是最大堆
    private boolean isMaxHeap(){
        return isMaxHeap(1);
    }

    // 判断已 k 为顶的二叉堆是否是最大堆
    private boolean isMaxHeap(int k){
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k+1;
        if (left <= n && less(k, left)) return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }

    /*************************************************************
     * Iterator
     *************************************************************/
    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key>{
        private MaxPQ copy;

        public HeapIterator(){
            if (comparator == null) copy = new MaxPQ(size());
            else                    copy = new MaxPQ(size(), comparator);
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
            return (Key) copy.delMax();
        }
    }

    public static void main(String[] args){
        MaxPQ<String> pq = new MaxPQ<>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }
}
