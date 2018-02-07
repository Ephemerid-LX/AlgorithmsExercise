package _5_Strings._5_2_Tries;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;


/******************************************************************************
 *  Compilation:  javac TrieST.java
 *  Execution:    java TrieST < words.txt
 *  Dependencies: StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/52trie/shellsST.txt
 *
 *  A string symbol table for extended ASCII strings, implemented
 *  using a 256-way trie.
 *
 *  % java TrieST < shellsST.txt
 *  by 4
 *  sea 6
 *  sells 1
 *  she 0
 *  shells 3
 *  shore 7
 *  the 5
 *
 * 单词查找树
 ******************************************************************************/
public class TrieST<Value> {
    private static final int R = 256;

    private Node root;
    private int n;

    public class Node<Value> {
        public Value val;
        public Node<Value>[] next = (Node<Value>[]) new Node[R];
    }


    /**
     * 查询
     *
     * @param key key
     * @return value
     */
    public Value get(String key) {
        if(key == null) throw new IllegalArgumentException("argument to get() is null.");
        Node x = get(root, key, 0);
        if(x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        //退出条件:没有下一个结点
        if(x == null) return null;
        //退出条件:查询了每一个字符
        if(d == key.length()) return x;

        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    /**
     * 是否有匹配的key
     *
     * @param key key
     * @return 有:true;否则:false
     */
    public boolean contains(String key) {
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * 插入
     *
     * @param key key
     * @param value value
     */
    public void put(String key, Value value) {
        if(key == null) throw new IllegalArgumentException("The first argument to put() is null.");
        if(value == null) delete(key);
        else root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        // 如果该结点为空，则创建该结点，继续
        if(x == null) x = new Node();
        // 检查到最后一个结点，val为空，则设置并返回
        if(d == key.length()) {
            if(x.val == null) n++;
            x.val = value;
            return x;
        }
        // 没有检查到最后|一个结点，继续
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, value, d + 1);
        return x;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回所有的键
     *
     * @return 所有的键
     */
    public Iterable<String> keys() {
        return keyWithPrefix("");
    }

    /**
     * 所有以指定字符为前缀的键
     *
     * @param prefix 指定字符
     * @return 以指定字符为前缀的键
     */
    public Iterable<String> keyWithPrefix(String prefix) {
        Queue<String> result = new Queue<>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), result);
        return result;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
                Node next;
                for(char i = 0; i < R; i++) {
                    next = x.next[i];
                    if(next != null) {
                        prefix.append(i);
                        if(next.val != null) results.enqueue(prefix.toString());
                        collect(next, prefix, results);
                        prefix.deleteCharAt(prefix.length() - 1);
                    }
                }

//        if(x == null) return;
//        if(x.val != null) results.enqueue(prefix.toString());
//        for(char c = 0; c < R; c++) {
//            prefix.append(c);
//            collect(x.next[c], prefix, results);
//            prefix.deleteCharAt(prefix.length() - 1);
//        }
    }

    /**
     * 返回能够匹配指定字符的键(只能使用"."代表任意字符)
     *
     * @param pattern 指定字符串
     * @return 能够匹配指定字符的键
     */
    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new Queue<>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if(x == null) return;
        int d = prefix.length();
        if(d == pattern.length() && x.val != null) results.enqueue(prefix.toString());
        if(d == pattern.length()) return;

        char c = pattern.charAt(d);
        // "." 匹配next的所有结点
        if(c == '.') {
            for(char i = 0; i < R; i++) {
                prefix.append(i);
                collect(x.next[i], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            // 匹配指定结点
            prefix.append(c);
            collect(x.next[c], prefix, pattern, results);
            //todo:为什么最后要删除最后一个字符
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * 返回指定字符串中最长的键
     *
     * @param query 指定字符串
     * @return 指定字符串中最长的键
     */
    public String longestPrefixOf(String query) {
        if(query == null) throw new IllegalArgumentException("argument to longestPrefixOf() is null");
        int length = longestPrefixOf(root, query, 0, -1);
        if(length == -1) return null;
        else return query.substring(0, length);
    }

    private int longestPrefixOf(Node x, String query, int d, int length) {
        if(x == null) return length;
        if(x.val != null) length = d;
        if(d == query.length()) return length;

        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d + 1, length);
    }

    /**
     * 删除指定key
     *
     * @param key key
     */
    public void delete(String key) {
        if(key == null) throw new IllegalArgumentException("argument to delete() is null");
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if(x == null) return null;
        if(d == key.length()) {
            if(x.val != null) n--;
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }

        if(x.val != null) return x;
        for(char i = 0; i < R; i++) {
            if(x.next[i] != null) return x;
        }
        return null;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        TrieST<Integer> st = new TrieST<>();
        for(int i = 0; !in.isEmpty(); i++)
            st.put(in.readString(), i);

        for(String key : st.keys())
            StdOut.println(key + " : " + st.get(key));
        StdOut.println();

        StdOut.println("size : " + st.size());
        StdOut.println();

        StdOut.println("longestPrefix(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("longestPrefix(\"quicksort\"):");
        StdOut.println(st.longestPrefixOf("quicksort"));
        StdOut.println();

        StdOut.println("keysWithPrefix(\"shor\"):");
        for(String key : st.keyWithPrefix("shor")) {
            StdOut.println(key);
        }
        StdOut.println();

        StdOut.println("keysThatMatch(\".he.l.\"):");
        for(String key : st.keysThatMatch(".he.l.")) {
            StdOut.println(key);
        }
        StdOut.println();

        st.delete("shells");
        st.delete("the");
        st.delete("by");
        for(String key : st.keys()) {
            StdOut.println(key + " : " + st.get(key));
        }
        StdOut.println();

        StdOut.println("size : " + st.size());
    }
}