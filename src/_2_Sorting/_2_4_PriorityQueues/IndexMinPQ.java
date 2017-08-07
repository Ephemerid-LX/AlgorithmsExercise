package _2_Sorting._2_4_PriorityQueues;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Compilation:  javac IndexMinPQ.java
 *  Execution:    java IndexMinPQ
 *  Dependencies: StdOut.java
 *
 *  Minimum-oriented indexed PQ implementation using a binary heap.
 *
 ******************************************************************************/
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer>{
    private int maxN;   // maximum number of elements on PQ
    private int n;      // number of elements on PQ
    private int[] pq;   // binary heap using 1-based indexing, 数组下标为排序后数组下标，值为keys中数组下标
    private int[] qp;   // inverse of pq - qp[pq[i]] = pq[qp[i]] = i, 数组下标为keys数组下标， 值为排序后数组下标
    private Key[] keys; // keys[i] = priority of i

    public IndexMinPQ(int maxN){
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        keys = (Key[]) new Comparable[maxN+1];
        n = 0;
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    /**
     * 空则返回true
     * @return 空则true
     */
    public boolean isEmpty(){
        return n == 0;
    }

    /**
     * 元素个数
     * @return 元素个数
     */
    public int size(){
        return n;
    }

    /**
     * i 是否在队列中
     * @param i index
     * @return 在队列中则返回true,否则返回false
     */
    public boolean contains(int i){
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        return qp[i] != -1;
    }

    /**
     * Associates key with index i
     * @param i an index
     * @param key the key to associate with index i
     */
    public void insert(int i, Key key){
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        pq[n] = i;
        qp[i] = n;
        keys[i] = key;
        swim(n);
    }

    /**
     * Return an index associate with a minimum key.
     * @return an index associate with a minimum key
     */
    public int minIndex(){
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /**
     * Returns a minimum key
     * @return a minimum key
     */
    public Key minKey(){
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    /**
     * Removes a minimum key and returns its associated index
     * @return an index associated with a minimum key
     */
    public int delMin(){
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, n--);
        sink(1);
        assert min == pq[n+1];
        qp[min] = -1;
        keys[min] = null;
        pq[n+1] = -1;
        return min;
    }

    /**
     * Returns the key associated with index i
     * @param i the index of the key to return
     * @return the key associated with index i
     */
    public Key keyOf(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        else return keys[i];
    }

    /**
     * change the key associated with index i to the specified value
     * @param i the index of the key to continue
     * @param key change the key associated with index i to this key
     */
    public void changeKey(int i, Key key){
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the prriority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * Decrease the key associated with index i to the specified value.
     * @param i thi index of the key to decrease
     * @param key decrease the key associated with index i to this key
     */
    public void decreaseKey(int i, Key key){
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[qp[i]].compareTo(key) <= 0)
            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
        keys[i] = key;
        swim(qp[i]);
    }

    /**
     * Increase the key associated with index i to the specified value.
     * @param i the index of the key of increase
     * @param key increase the key associated with index i to this key
     */
    public void increaseKey(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) <= 0)
            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
        keys[i] = key;
        sink(qp[i]);
    }

    /**
     * Remove the key associated with index i
     * @param i the index of the key to remove
     */
    public  void delete(int i){
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    /***************************************************************************
     * General helper functions.
     ***************************************************************************/
    private boolean greater(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j){
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /***************************************************************************
     * Heap helper functions.
     ***************************************************************************/
    /**
     *
     * @param k 二叉堆数组的下标，并不是keys的下标
     */
    private void swim(int k){
        while (k > 1 && greater(k/2,k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (greater(j, k)) break;
            exch(k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Iterator
     ***************************************************************************/
    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        private IndexMinPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPQ<Key>(pq.length-1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
    /***************************************************************************
     * Test
     ***************************************************************************/
    public static void main(String[] args){
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> pq = new IndexMinPQ<>(strings.length);
        for (int i = 0; i < strings.length; i++)
            pq.insert(i, strings[i]);

        //delete and print each key
        StdOut.println("delete and print each key");
        while (!pq.isEmpty()) {
            int i = pq.delMin();
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++)
            pq.insert(i, strings[i]);

        // print each key using the iterator
        StdOut.println("print each key using the iterator");
        for (int i : pq) {
            StdOut.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty())
            pq.delMin();

    }

}
