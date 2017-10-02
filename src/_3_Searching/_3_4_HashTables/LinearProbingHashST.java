package _3_Searching._3_4_HashTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;//键值对的个数
    private int m;//hash值的个数
    private Key[] keys;//keys
    private Value[] vals;//values

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity) {
        this.m = capacity;
        this.n = 0;
        this.keys = (Key[]) new Object[m];
        this.vals = (Value[]) new Object[m];
    }

    /**
     * 返回键值对个数
     *
     * @return 键值对个数
     */
    public int size() {
        return n;
    }

    /**
     * 返回给定键的散列值
     *
     * @return 给定健散列值
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * 是否为空
     *
     * @return 空: true; 非空: false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    public void resize(int capacity) {
        LinearProbingHashST temp = new LinearProbingHashST(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null)
                temp.put(keys[i], vals[i]);
        }
        this.m = temp.m;
        this.keys = (Key[]) temp.keys;
        this.vals = (Value[]) temp.vals;

    }

    /**
     * 给定key是都在散列表中
     *
     * @param key the key
     * @return 在: true; 不在: false
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * 获取key对应的value
     *
     * @param key the key
     * @return value
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (int i = hash(key); keys[i] != null && key.equals(keys[i]); i = (i + 1) % m)
            if (key.equals(keys[i]))
                return vals[i];
        return null;
    }

    /**
     * 向散列表中插入该键值对
     *
     * @param key the key
     * @param val the value
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("the first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        if (n >= m / 2) resize(2 * m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            // 不能使用 for (i = hash(key); keys[i] != null && key.equals(keys[i]); i = (i + 1) % m)
            // 因为i的值不会增加
            if (key.equals(keys[i])) {
                vals[i] = val;
                return;
            }
        }

        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * 删除键值对
     *
     * @param key the key
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        keys[i] = null;
        vals[i] = null;

        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToReHash = keys[i];
            Value valToReHash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToReHash, valToReHash);
        }
        n--;
        if (n > 0 && n <= m / 8) resize(m / 2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null)
                queue.enqueue(keys[i]);
        }
        return queue;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        for (int i = 0; ! StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("size: " + st.size()) ;
        StdOut.println("============================");

        st.delete("S");
        st.delete("E");
        st.delete("KK");

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("size: " +st.size());
        StdOut.println("===========================");

    }
}
