package _3_Searching._3_2_BinarySearchTrees;

import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/******************************************************************************
 *  Compilation:  javac BST.java
 *  Execution:    java BST
 *  Dependencies: StdIn.java StdOut.java Queue.java
 *  Data files:   http://algs4.cs.princeton.edu/32bst/tinyST.txt
 *
 *  A symbol table implemented with a binary search tree.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *
 *  % java BST < tinyST.txt
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
public class BST<Key extends Comparable<Key>,  Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size){
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null) return 0;
        else return x.size;
    }

    public boolean isEmpty(){
        return root.size == 0;
    }

    // 查找
    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node x, Key key){
        // 如果该结点为null,返回null
        if (x == null) return null;

        int cmn = key.compareTo(x.key);
        // 如果key不相等
        // 1.小于val,在左树中递归
        if (cmn < 0) return get(x.left, key);
        // 2.大于val,在右树中递归
        else if (cmn > 0) return get(x.right, key);
        // 如果该key相等,则返回value
        else return x.val;
    }

    // 插入
    public void put(Key key, Value val){
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val){
        // 如果 x == null，就直接创建一个结点
        if (x == null) x = new Node(key, val, 1);

        int cmn = key.compareTo(x.key);
        // 如果大于，则在右树中put
        if (cmn < 0) x.left = put(x.left, key, val);
        // 如果小于，则在左树中put
        else if (cmn > 0) x.right = put(x.right, key, val);
        // 等于，更新值
        else x.val = val;
        // 更更新父节点的计数
        x.size = size(x.left) + size(x.right) + 1;
        // return
        return x;
    }

    /**
     * 查找最小key
     * @return 最小key
     */
    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        // 如果没有左结点为null，则返回该结点
        if (x.left == null) return x;
        // 否则在左子树中查找最小key
        return min(x.left);
    }

    /**
     * 查找最大key
     * @return 最大key
     */
    public Key max(){
        return max(root);
    }

    private Key max(Node x){
        // 如果没有右结点，则返回该结点
        if (x.right == null) return x.key;
        // 否则在右子树中查最大key
        return max(x.right);
    }


    /**
     * 查找小于等于给定key的最大key
     * @param key 给定key
     * @return 小于等于给定key的最大key
     */
    public Key floor(Key key){
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }


    private Node floor(Node x, Key key){
        // todo:理清思路
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        // 如果key相等，则返回该根节点
        if (cmp == 0) return x;
        // 如果key小，则返回在左子树中查找的结果
        else if (cmp < 0) return floor(x.left, key);
        // 如果key大，则在右子树中查找
        Node t = floor(x.right, key);
        // 如果有小于的，则返回该结点
        if (t != null) return t;
        // 如果没有小于的，则返回有子树的跟结点
        else return x;
    }

    public Key ceiling(Key key){
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key){
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * Return the kth smallest key in the symbol table
     * @param k the order statistic
     * @return the kth smallest key in the symbol table
     */
    public Key select(int k){
        if (k < 0 || k >=size()) throw new IllegalArgumentException("called select() with invalid argument: " + k);
        Node x = select(root, k);
        if (x == null) return null;
        else return x.key;
    }

    private Node select(Node x, int k){
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }

    /**
     * return the number of keys in the symbol table strictly less than key
     * @param key the key
     * @return the number of keys in the symbol table strictly less than key
     */
    public int rank(Key key){
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    private int rank(Key key, Node x){
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return rank(key, x.right)+size(x.left)+1;
        else return size(x.left);
    }

    /**
     * removes the smallest key and associated value from the symbol table
     */
    public void deleteMin(){
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        deleteMin(root);
    }

    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * removes the largest key and associated value from the symbol table
     */
    public void deleteMax(){
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        deleteMax(root);
    }

    private Node deleteMax(Node x){
        if (x.right == null) return x.left;
        x.right = deleteMax(x);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table)
     *
     * @param key the key
     */
    public void delete(Key key){
        if (key == null) throw new IllegalArgumentException("called delete() with a null key");
        delete(key, root);
    }

    private Node delete(Key key, Node x){
        if (key == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) delete(key, x.left);
        else if (cmp > 0) delete(key, x.right);
        else {
            if (x.left == null) return x.right;
            else if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.right) + size(x.left) + 1;
        return x;
    }

    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue queue, Key lo, Key hi){
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

}
