package _3_Searching._3_1_ElementarySymbolTables;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/******************************************************************************
 *  Compilation:  javac BinarySearchST.java
 *  Execution:    java BinarySearchST
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/31elementary/tinyST.txt
 *
 *  Symbol table implementation with binary search in an ordered array.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *
 *  % java BinarySearchST < tinyST.txt
 *  A 8
 *  C 4
 *  E 12
 *  H 5
 *  L 11
 *  M 9
 *  P 10
 *  R 3
 *  S 0
 *  X 7
 *
 ******************************************************************************/
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n;

    public BinarySearchST(){
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity){
        keys = (Key[]) new  Comparable[capacity];
        vals = (Value[]) new Object[capacity];
        n = 0;
    }

    private void resize(int capacity){
        assert capacity > n;
        Key[] tempKeys = (Key[]) new Comparable[capacity];
        Value[] tempVals = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++){
            tempKeys[i] = keys[i];
            tempVals[i] = vals[i];
        }
        keys = tempKeys;
        vals = tempVals;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    /**
     * Does this symbol table contain the given key?
     * @param key key
     * @return true if thi symbol table contains key and false otherwise
     */
    public boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * 根据key，在符号表中查找value，并返回value
     * @param key key
     * @return value
     */
    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    /**
     * 返回符号表中小于key的数量
     * @param key key
     * @return 符号表中小于key的数量
     */
    public int rank(Key key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");

        int lo = 0, hi = n-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val){
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        // val为空，则删除键
        if (val == null) delete(key);

        int i = rank(key);
        //
        if (i < n && key.compareTo(keys[i]) == 0) {
            vals[i] = val;
            return;
        }

        //
        if (n == keys.length) resize(keys.length*2);

        for (int j = n; j > i; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }

        keys[i] = key;
        vals[i] = val;
        n++;

        assert check();
    }

    /**
     * remove the specified key and associated value from this symbol table
     * (if the key is in the symbol table)
     * @param key tha key
     */
    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("argument to del() is null");
        if (isEmpty()) return;
        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n && keys[i].compareTo(key) == 0) return;

        for (int j = i; j < n-1; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        n--;
        keys[n] = null;
        vals[n] = null;

        if (n >= 0 && n == keys.length/4) resize(keys.length/2);
        assert check();
    }

    /**
     * Remove the smallest key and associated value from this Symbol table
     */
    public void deleteMin(){
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    /**
     * Remove the largest key and associated value from this symbol table.
     */
    public void deleteMax(){
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }

    /**
     * returns the smallest key in this symbol table.
     * @return the smallest key in this symbol table.
     */
    public Key min(){
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        return keys[0];
    }

    /**
     * returns the largest key in this symbol table.
     * @return the largest key in this symbol table.
     */
    public Key max(){
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        return keys[n-1];
    }

    /**
     * Return the kth smallest key in this symbol table.
     * @param k the order statistic
     * @return kth smallest key in this symbol table.
     */
    public Key select(int k){
        if (k < 0 || k >= size()) throw new IllegalArgumentException("called select() with invalid argument : " + k);
        return keys[k];
    }

    /**
     * Returns the largest key in this symbol table less than or equal to key
     *
     * @param  key the key
     * @return the largest key in this symbol table less than or equal to key
     */
    public Key floor(Key key){
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        int i = rank(key);
        if (i == 0) return null;
        else if (i > 0 && key.equals(keys[i])) return keys[i];
        else return keys[i-1];
    }

    /**
     * Returns the smallest key in this symbol table greater than or equal to key
     *
     * @param  key the key
     * @return the smallest key in this symbol table greater than or equal to key
     */
    public Key ceiling(Key key){
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        int i = rank(key);
        if (i == 0) return null;
        else if (i > 0 && key.equals(keys[i])) return keys[i];
        else return keys[i+1];
    }

    /**
     * Returns the number of keys in this symbol table in the specified range.
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return the number of keys in this symbol table in the specified range.
     */
    public int size(Key lo, Key hi){
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) ;
        else return rank(hi) - rank(lo);
    }

    /**
     * Returns all keys in this symbol table as an Iterable.
     * To iterate over all of the keys in the symbol table named st,
     * use the foreach notation: for (Key key : st.keys()).
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    /**
     * Returns all keys in this symbol table in the given range,
     * as an Iterable.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in this symbol table between lo
     *         (inclusive) and hi (inclusive)
     */
    public Iterable<Key> keys(Key lo, Key hi){
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }

    /***************************************************************************
     *  Check internal invariants.
     ***************************************************************************/

    private boolean check(){
        return isSorted() && rankCheck();
    }

    // are the items in the array in ascending order?
    private boolean isSorted(){
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i-1]) < 0) return false;
        return true;
    }

    //check that rank(select(i)) = i
    private boolean rankCheck() {
        // todo:这里两个循环是否重复了，检查的都是同一个?
        // 只要是有序的，那么返回的肯定是true?
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }


    public static void main(String[] args){
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
