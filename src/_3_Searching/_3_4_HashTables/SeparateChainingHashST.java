package _3_Searching._3_4_HashTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac SeparateChainingHashST.java
 *  Execution:    java SeparateChainingHashST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/34hash/tinyST.txt
 *
 *  A symbol table implemented with a separate-chaining hash table.
 *
 ******************************************************************************/
public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;
    private int m;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }

    /**
     * 散列函数
     * @param key the key
     * @return 散列值
     */
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * 返回键值对个数
     * @return 键值对个数
     */
    public int size() {
        return n;
    }

    /**
     * 是否为空
     * @return 空:true; 非空:false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 是否有该key
     * @param key the key
     * @return 有: true; 没有: false
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * 根据键，取值
     * @param key the key
     * @return the value
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    }

    /**
     * 插入键值对
     * @param key the key
     * @param val the value
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // todo:为什么是10*m?
        if (n >= 10*m) resize(2*m);

        int i = hash(key);
        if (!contains(key)) n++;
        st[i].put(key, val);
    }

    /**
     * 删除键值对
     * @param key the key
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        int i = hash(key);
        if (contains(key)) n--;
        st[i].delete(key);

        // todo: 为什么是2*m
        if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
    }

    /**
     * 调整散列表大小
     * @param chains 大小
     */
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            st.put(StdIn.readString(), i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("size:" + st.size());
        StdOut.println("=================================================");
        st.delete("S");
        st.delete("E");
        st.delete("KK");

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("size:" + st.size());
        StdOut.println("=================================================");
    }
}
