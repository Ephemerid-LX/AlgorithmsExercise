package _5_Strings._5_2_Tries;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac TST.java
 *  Execution:    java TST < words.txt
 *  Dependencies: StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/52trie/shellsST.txt
 *
 *  Symbol table with string keys, implemented using a ternary search
 *  trie (TST).
 *
 *
 *  % java TST < shellsST.txt
 *  keys(""):
 *  by 4
 *  sea 6
 *  sells 1
 *  she 0
 *  shells 3
 *  shore 7
 *  the 5
 *
 *  longestPrefixOf("shellsort"):
 *  shells
 *
 *  keysWithPrefix("shor"):
 *  shore
 *
 *  keysThatMatch(".he.l."):
 *  shells
 *
 *  % java TST
 *  theory the now is the time for all good men
 *
 *  Remarks
 *  --------
 *    - can't use a key that is the empty string ""
 *
 ******************************************************************************/
public class TST<Value> {

    private Node<Value> root;
    private int n;

    private static class Node<Value> {
        private char c;
        private Node<Value> left, mid, right;
        private Value val;
    }

    public TST() {
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(String key) {
        if(key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * 查询
     *
     * @param key key
     * @return 值
     */
    public Value get(String key) {
        if(key == null)
            throw new IllegalArgumentException("calls get() with null argument.");
        if(key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node<Value> x = get(root, key, 0);
        if(x == null) return null;
        else return (Value) x.val;
    }

    private Node<Value> get(Node<Value> x, String key, int d) {
        if(x == null) return null;
        if(key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");

        char c = key.charAt(d);
        if(c < x.c) return get(x.left, key, d);
        else if(c > x.c) return get(x.right, key, d);
        else if(d < key.length() - 1) return get(x.mid, key, d + 1);
            // 这里的 d == key.length() - 1 的情况放在了最后，在key.length() == 0 的时候，key.charAt(d)处会报错
            // 所以和 TrieST中的Keys()方法的处理会不同
        else return x;
    }

    /**
     * 插入
     *
     * @param key key
     * @param val value
     */
    public void put(String key, Value val) {
        if(key == null) throw new IllegalArgumentException("calls put() with null key");
        if(val == null) delete(key);
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if(x == null) {
            x = new Node<Value>();
            x.c = c;
        }

        if(c < x.c) x.left = put(x.left, key, val, d);
        else if(c > x.c) x.right = put(x.right, key, val, d);
        else if(d < key.length() - 1) x.mid = put(x.mid, key, val, d + 1);
        else {
            if(x.val == null) n++;
            x.val = val;
        }
        return x;
    }

    /**
     * 删除 key
     *
     * @param key key
     */
    public void delete(String key) {
        if(key == null) throw new IllegalArgumentException("calls delete() with null key");
        // 如果不存在改key，将不执行递归
        if(!contains(key)) return;
        root = delete(root, key, 0);
    }

    private Node<Value> delete(Node<Value> x, String key, int d) {
        if(x == null) return null;

        char c = key.charAt(d);

        if(c < x.c) x.left = delete(x.left, key, d);
        else if(c > x.c) x.right = delete(x.right, key, d);
        else if(d < key.length() - 1) x.mid = delete(x.mid, key, d + 1);
        else {
            x.val = null;
            n--;
        }

        if(x.val == null && x.left == null && x.right == null && x.mid == null)
            // 删除结点之后，在进行插入操作，可能会对树的结构产生影响
            x = null;
        return x;
    }

    /**
     * 返回指定字符中的最长键
     *
     * @param query 指定字符
     * @return 指定字符中的最长键
     */
    public String longestPrefixOf(String query) {
        if(query == null) throw new IllegalArgumentException("calls longestPrefixOf() with null argument.");
        if(query.length() == 0) return null;
        int length = longestPrefixOf(root, query, 0, -1);
        if(length == -1) return null;
        return query.substring(0, length);
    }

    private int longestPrefixOf(Node<Value> x, String query, int d, int length) {
        if(x == null) return length;
        char c = query.charAt(d);
        if(c < x.c) length = longestPrefixOf(x.left, query, d, length);
        else if(c > x.c) length = longestPrefixOf(x.right, query, d, length);
        else if(d < query.length() - 1) {
            if(x.val != null) length = d + 1;
            length = longestPrefixOf(x.mid, query, d + 1, length);
        } else if(x.val != null) length = d + 1;

        return length;

    }

    /**
     * 返回所有的键
     *
     * @return 所有键
     */
    public Iterable<String> keys() {
        Queue<String> results = new Queue<>();
        collect(root, new StringBuilder(), results);
        return results;
    }

    /**
     * 返回以指定字符为前缀的所有键
     *
     * @param prefix 指定字符
     * @return 以指定字符为前缀的所有键
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        if(prefix == null) throw new IllegalArgumentException("calls keysWithPrefix() with null argument.");
        Queue<String> results = new Queue<>();
        Node<Value> x = get(root, prefix, 0);
        if(x == null) return results;
        if(x.val != null) results.enqueue(prefix);
        collect(x.mid, new StringBuilder(prefix), results);
        return results;
    }

    private Iterable<String> collect(Node<Value> x, StringBuilder prefix, Queue<String> results) {
        if(x == null) return results;
        collect(x.left, prefix, results);

        prefix.append(x.c);
        if(x.val != null) results.enqueue(prefix.toString());
        collect(x.mid, prefix, results);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, results);
        return results;
    }

    /**
     * 返回能够匹配指定字符的键(只能使用"."代表任意字符)
     *
     * @param pattern 指定字符串
     * @return 能够匹配指定字符的键
     */
    public Iterable<String> keysThatMatch(String pattern) {
        if(pattern == null) throw new IllegalArgumentException("calls keysThatMatch() with null argument.");
        Queue<String> results = new Queue<>();
        collect(root, new StringBuilder(), 0, pattern, results);

//        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node<Value> x, StringBuilder prefix, int i, String pattern, Queue<String> results) {
        if(x == null) return;
        char c = pattern.charAt(i);
        if(c == '.' || c < x.c) collect(x.left, prefix, i, pattern, results);
        if(c == '.' || c == x.c) {
            if(i == pattern.length() - 1 && x.val != null) results.enqueue(prefix.toString() + x.c);
            if(i < pattern.length() - 1) {
                collect(x.mid, prefix.append(x.c), i + 1, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if(c == '.' || c > x.c) collect(x.right, prefix, i, pattern, results);
    }

//    private void collect(Node<Value> x, StringBuilder prefix, String pattern, Queue<String> results) {
//        if(x == null) return;
//        int d = prefix.length();
//        if(d == pattern.length()) return;
//
//        char c = pattern.charAt(d);
//        if(c == '.') {
//            collect(x.left, prefix, pattern, results);
//
//            prefix.append(x.c);
//            if(x.val != null && (d + 1) == pattern.length()) results.enqueue(prefix.toString());
//            collect(x.mid, prefix, pattern, results);
//            prefix.deleteCharAt(d);
//
//            collect(x.right, prefix, pattern, results);
//        } else {
//            if(c < x.c) collect(x.left, prefix, pattern, results);
//            else if(c == x.c) {
//                prefix.append(c);
//                if(x.val != null && (d + 1) == pattern.length()) results.enqueue(prefix.toString());
//                collect(x.mid, prefix, pattern, results);
//                prefix.deleteCharAt(d);
//            } else if(c > x.c) {
//                collect(x.right, prefix, pattern, results);
//            }
//        }
//    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        TST<Integer> st = new TST<>();
        for(int i = 0; !in.isEmpty(); i++)
            st.put(in.readString(), i);

        for(String key : st.keys())
            StdOut.println(key + " : " + st.get(key));
        StdOut.println();

        StdOut.println("size : " + st.size());
        StdOut.println();

        StdOut.println("longestPrefixOf(\"shellsort\"):");
        StdOut.println(st.longestPrefixOf("shellsort"));
        StdOut.println();

        StdOut.println("longestPrefixOf(\"quicksort\"):");
        StdOut.println(st.longestPrefixOf("quicksort"));
        StdOut.println();

        StdOut.println("keysWithPrefix(\"shor\"):");
        for(String key : st.keysWithPrefix("shor")) {
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
