package _3_Searching._3_1_ElementarySymbolTables;


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
            tempVals[i] = tempVals[i];
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
            if (cmp < 0) lo = mid + 1;
            else if (cmp > 0) hi = mid - 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val){
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        //
    }

    public void del(Key key){
        if (key == null) throw new IllegalArgumentException("argument to del() is null");

    }

    public void delMin(){

    }

    public void delMax(){

    }

}
