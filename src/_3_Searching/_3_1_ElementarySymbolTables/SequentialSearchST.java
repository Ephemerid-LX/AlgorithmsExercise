package _3_Searching._3_1_ElementarySymbolTables;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac SequentialSearchST.java
 *  Execution:    java SequentialSearchST
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/31elementary/tinyST.txt
 *
 *  Symbol table implementation with sequential search in an
 *  unordered linked list of key-value pairs.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *
 *  % java SequentialSearchST < tiny.txt
 *  L 11
 *  P 10
 *  M 9
 *  X 7
 *  H 5
 *  C 4
 *  R 3
 *  A 8
 *  E 12
 *  S 0
 *
 ******************************************************************************/
public class SequentialSearchST<Key extends Comparable<Key>, Value> {

    private int n;
    private Node first;

    public SequentialSearchST(){
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public void put(){

    }

    /**
     * 符号表中是否存在key
     * @param key key
     * @return 存在,则返回true;否则返回false.
     */
    public boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key in this symbol table
     * @param key the key
     * @return the value associated with the given key in this symbol table
     */
    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (Node x = first; x != null; x = x.next )
            if (key.equals(x.key))
                return x.value;
        return null;
    }

    /**
     * 添加key-value;
     * 如果key存在,则覆盖原值;
     * 如果key不存在,则添加该值;
     * 如果value为null,则删除该key
     * @param key key
     * @param value value
     */
    public void put(Key key, Value value){
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        // 如果value为null,则直接删除key
        if (value == null) {
            delete(key);
            return;
        }

        // 如果匹配到key,则修改value
        for (Node x = first; x != null; x = x.next){
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }

        // 如果匹配不到key,则创建新节点，并插入到链表的开头
        first = new Node(key, value, first);
    }

    /**
     * 从符号表中删除key以及相关的value
     * @param key 需要删除的key
     */
    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, key);
    }

    // 删除x结点后的key
    // 如果链表太长，调用方法栈会很大
    private Node delete(Node x, Key key){
        // 链表尾部
        if (x == null) return null;
        if (x.key.equals(key)){
            n--;
            return x.next;
        }
        // 如果有删除，则会将指针指向删除key的next
        x.next = delete(x.next, key);
        return x;
    }

    private class Node{
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    /**
     * returns all keys
     * @return all keys
     */
    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

    public static void main(String[] args){
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        for (int i = 0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
