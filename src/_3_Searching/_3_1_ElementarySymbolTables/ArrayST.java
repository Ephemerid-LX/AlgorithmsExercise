package _3_Searching._3_1_ElementarySymbolTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac ArrayST.java
 *  Execution:    java ArrayST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/31elementary/tinyST.txt
 *
 *
 *  Symbol table implementation with unordered array. Uses repeated
 *  doubling to resize the array.
 *
 *  % java ArrayST < tiny.txt
 *  S 0
 *  H 5
 *  X 7
 *  R 3
 *  C 4
 *  L 11
 *  A 8
 *  M 9
 *  P 10
 *  E 12
 *
 ******************************************************************************/
public class ArrayST<Key, Value> {
    private static final int INIT_SIZE=8;

    private Key[] keys;
    private Value[] vals;
    private int n;

    public ArrayST() {
        keys = (Key[]) new Object[INIT_SIZE];
        vals = (Value[]) new Object[INIT_SIZE];
        n = 0;
    }

    // return the number of key-value pairs in the symbol table
    public int size() {
        return n;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // resize the parallel arrays to the given capacity
    private void resize(int capacity) {
        assert capacity > n;
        Key[] tempKeys = (Key[]) new Object[capacity];
        Value[] tempVals = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++){
            tempKeys[i] = keys[i];
            tempVals[i] = vals[i];
        }
        keys = tempKeys;
        vals = tempVals;
    }

    // insert the key-value pair into the symbol table
    public void put(Key key, Value val) {
        // to deal with duplicates
        delete(key);

        if (n >= vals.length) resize(2 * n);

        keys[n] = key;
        vals[n] = val;
        n++;
    }

    public Value get(Key key) {
        for (int i = 0; i < n; i++) {
            if (key.equals(keys[i])) return vals[i];
        }
        return null;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < n; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    // remove given key (and associated value)
    public void delete(Key key) {
        for (int i = 0; i < n; i++) {
            if (key.equals(keys[i])) {
                keys[i] = keys[n-1];
                vals[i] = vals[n-1];
                keys[n-1] = null;
                vals[n-1] = null;
                n--;
                if (n > 0 && n == keys.length/4) resize(keys.length/2);
                return;
            }
        }
    }

    public static void main(String[] args){
        ArrayST<String, Integer> st = new ArrayST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
