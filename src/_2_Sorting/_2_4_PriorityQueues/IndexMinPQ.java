package _2_Sorting._2_4_PriorityQueues;

import java.util.Iterator;

/******************************************************************************
 *  Compilation:  javac IndexMinPQ.java
 *  Execution:    java IndexMinPQ
 *  Dependencies: StdOut.java
 *
 *  Minimum-oriented indexed PQ implementation using a binary heap.
 *
 ******************************************************************************/
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Key>{
    private int maxN;   // maximum number of elements on PQ
    private int n;      // number of elements on PQ
    private int[] pq;   // binary heap using 1-based indexing
    private int[] qp;   // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys; // keys[i] = priority of i

    public IndexMinPQ(int maxN){

    }




    @Override
    public Iterator<Key> iterator() {
        return null;
    }
}
