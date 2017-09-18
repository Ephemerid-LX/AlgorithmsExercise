package _3_Searching._3_3_BalancedSearchTrees;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/******************************************************************************
 *  Compilation:  javac RedBlackBST.java
 *  Execution:    java RedBlackBST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/33balanced/tinyST.txt
 *
 *  A symbol table implemented using a left-leaning red-black BST.
 *  This is the 2-3 version.
 *
 *  Note: commented out assertions because DrJava now enables assertions
 *        by default.
 *
 *  % more tinyST.txt
 *  S E A R C H E X A M P L E
 *
 *  % java RedBlackBST < tinyST.txt
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
public class RedBlackBST<Key extends Comparable, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key key, Value val, int size, boolean color) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.color = color;

        }
    }

    public RedBlackBST() {

    }

    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return size(root) == 0;
    }

    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        /* 递归：
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        return x;
        */
        // 循环
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/
    public void put(Key key, Value val) {
        //1.如果key为null,则出异常
        //2.如果val为null，则删除key
        //3.else put
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        // 1.如果h为Null，则新建结点，为红色结点
        if (h == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        // 2.如果key大于h.key，则在h的右链接中插入
        if (cmp > 0) h.right = put(h.right, key, val);
        // 3.如果key小于h.key，则在h的左链接中插入
        else if (cmp < 0) h.left = put(h.left, key, val);
        // 4.如果key等于h.key，则更新值
        else h.val = val;

        /*
        考虑复杂，并不需要靠考虑父结点，收到了 flipColors() 中assert的影响
        // 5.在插入或者更新后，
        // 5.1 如果父结点是红色结点
        // 5.1.1 如果新结点是左结点，且父节点是红色结点，则右旋，再反转颜色
        // 5.1.2 如果新结点是右结点，且父结点是红色结点，则左旋，再右旋，最后转换颜色
        // 5.2 如果父结点不是红色结点
        // 5.2.1 如果新结点的兄弟结点（父节点的另外一个结点）为红色结点，则翻转颜色
        // 5.2.2 如果新结点在右侧，则左旋
        // 5.2.3 如果新结点在左侧，则右旋
        */
        // 5.在插入或者更新后
        // 5.1 如果左子结点是黑色结点，右子结点是红色结点，则左旋
        if (! isRed(h.left) && isRed(h.right)) h = rotateLeft(h);
        // 5.2 如果左子结点是红色结点，左子结点的左子节点也是红色结点，则右旋
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        // 5.3 如果左子结点和右子结点都是红色结点，则颜色转换
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        // 这句是否不需要，因为在左旋右旋的时候就重新计算过
        h.size = 1 + size(h.left) + size(h.right);
        // 6. 返回该结点
        return h;
    }

    /***************************************************************************
     *  Red-black tree deletion.
     ***************************************************************************/
    /**
     * Removes the smallest key and associated value from the symbol table.
     */
    public void deleteMin() {
        // 向下查找
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;
        if (!isRed(x.left) && !isRed(x.right)) x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);
    }

    /**
     *  Removes the largest key and associated value from the symbol table.
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node x){
        if (isRed(x.left)) x = rotateRight(x);
        //todo: x.left != null 这种情况为什么不讨论？
        //return x.left
        if (x.right == null) return null;
        if (!isRed(x.right) && !isRed(x.right.left)) x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return balance(x);
    }

    /**
     * Removes hte specified key  and its associated value from this symbol table
     * (if the key is in this symbol table).
     * @param key the key
     */
    public void delete(Key key) {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!contains(key)) return;

        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }else {
            if (isRed(h.left)) h = rotateRight(h);
            //todo: h.left != null 这种情况为什么不讨论？
            //return h.left
            if (key.compareTo(h.key) == 0 && h.right == null) return null;
            if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedLeft(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }else{
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    /***************************************************************************
     *  Red-black tree helper functions.
     ***************************************************************************/
    private Node rotateRight(Node h) {
        // assert h != null && isRed(h.left)
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateLeft(Node h) {
        //h ！= null 并且其右链接为红色
        //assert h != null && isRed(h.right)
        // 1.红色右链接记为x
        Node x = h.right;
        // 2.h的右链接指向x的左链接
        h.right = x.left;
        // 3.x左链接指向h
        x.left = h;
        // 4.x的颜色变为h的颜色
        x.color = h.color;
        // 5.h的颜色变为红色
        h.color = RED;
        // 6.x的size变为h的size
        x.size = h.size;
        // 7.h的size重新计算
        h.size = 1 + size(h.left) + size(h.right);
        // 8.返回x(h的父结点会指向x)
        return x;
    }

    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null)
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = ! isRed(h);
        h.right.color = ! isRed(h.right);
        h.left.color = ! isRed(h.left);
    }


    private Node moveRedLeft(Node h){
        // h.left && h.right 都不是红色结点, 颜色转换
        flipColors(h);
        // 如果h.right && h.right.left都是红色结点,h.right右旋,h左旋,颜色转换
        if (isRed(h.right) && isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        //h.size = 1 + size(h.right) + size(h.left);
        return h;
    }

    private Node moveRedRight(Node h){
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        //h.size = 1 + size(h.right) + size(h.left);
        return h;
    }

    private Node balance(Node h){
        // 如果h.right是红色结点，则左旋
        if (isRed(h.right)) h = rotateLeft(h);
        // 如果h.left && h.left.left是红色结点，则右旋
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        // 如果h.left && h.right是红色结点，则颜色转换
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.size = 1 + size(h.right) + size(h.left);
        return h;
    }

    /***************************************************************************
     *  Utility functions.
     ***************************************************************************/
    /**
     * Returns the height of the BST
     *
     * @return the height of the BST
     */
    public int height() {
        return height(root);
    }

    public int height(Node x) {
        if (x == null) return - 1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /***************************************************************************
     *  Ordered symbol table methods.
     ***************************************************************************/
    /**
     * returns the smallest key in the symbol table
     *
     * @return the smallest key in the symbol table
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    /**
     * returns the largest key in the symbol table
     *
     * @return the largest key in the symbol table
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    /**
     * Returns the largest key in the symbol table less than or equal to key
     *
     * @param key the key
     * @return the largest key in the symbol table less than or equal to key
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x != null) return x.key;
        return null;
    }

    private Node floor(Node x, Key key) {
        // 1. x==null return null
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        // 2. x.key > key 在左子树中继续查找
        if (cmp < 0) return floor(x.left, key);
        // 3. x.key == key return x
        else if (cmp == 0) return x;
        // 4. x.key < key 在右子树中继续查找，若找到则返回该值，找不到则返回x
        Node t = floor(x.right, key);
        if (t != null) return t;
        return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to key
     *
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to key
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x != null) return x.key;
        return null;
    }

    private Node ceiling(Node x, Key key) {
        // 1. 如果x==null return x;
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        // 2. 如果x.key < key 则在右子树中找
        if (cmp > 0) return ceiling(x.right, key);
            // 3. 如果x.key == key 则返回x
        else if (cmp == 0) return x;
        // 4. 如果x.key > key 则在左子树中赵，如果找到t,则返回t,如果没找到,则返回x
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        return x;
    }

    /**
     * Returns the kth smallest key in the symbol table
     *
     * @param k the order statistic
     * @return the kth smallest key in the symbol table
     */
    public Key select(int k) {
        // k >= 0 && k < root.size;
        if (k < 0 || k >= size(root)) throw new IllegalArgumentException("called select with invalid argument: " + k);
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        int t = size(x.left);
        // 1.如果k < x.size,在左子树中找排名为k的结点
        if (k < t) return select(x.left, k);
            // 2.如果k > x.size-1, 在右子树中找排名为(k-x.size-1)的结点
        else if (k > t) return select(x.right, k - t - 1);
            // 3.如果k == x.size,则返回key
        else return x;
    }

    /**
     * Returns the number of keys in the symbol table strictly less than key
     *
     * @param key the key
     * @return the number of keys in the symbol table strictly less than key
     */
    public int rank(Key key) {
        // key ！= null
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        int cmp = key.compareTo(x.key);
        // 1. 如果key < x.key,则在左子树中找，返回rank(x.left,key)
        if (cmp < 0) return rank(x.left, key);
            // 2. 如果key > x.key,则在右子树中找，返回1+left.size+rank(x.right,key)
        else if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
            // 3. 如果key == x.key,则返回left.size
        else return size(x.left);
    }

    /***************************************************************************
     *  Range count and range search.
     ***************************************************************************/
    /**
     * Returns all keys in the symbol table as an Iterable.
     * To iterate over all of the keys in the symbol table named st,
     * use the foreach notation : for (Key key : st.keys()).
     *
     * @return all keys in the symbol table as an Iterable.
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<>();
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an Iterable.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in the symbol table between lo
     * (inclusive) and hi(inclusive) as an Iterable
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        // lo != null && hi != null
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        // 如果x==null，推出
        if (x == null) return;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        // 如果lo < x.key,则在进入左子树
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        // 开始入栈
        if (cmplo <= 0 || cmphi >= 0) queue.enqueue(x.key);
        // 如果hi > x.key,则进入右子树
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /**
     * Returns the number of keys in the symbol table in the given range
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return the number of keys in the symbol table between lo(inclusive)
     * and hi(inclusive)
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        // 如果lo > hi，0
        if (lo.compareTo(hi) > 0) return 0;
        // hi的排名-lo的排名
        if (contains(hi)) return 1 + rank(hi) - rank(lo);
        else return rank(hi) - rank(lo);
    }

    /***************************************************************************
     *  Check integrity of red-black tree data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST())            StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        if (!is23())             StdOut.println("Not a 2-3 tree");
        if (!isBalanced())       StdOut.println("Not balanced");
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

    //TODO: 为什么这样就可以判断是否是二叉树？
    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        // x.size与重新计算的值是否相等
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        // select 和 rank 的转置
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key != select(rank(key))) return false;
        return true;
    }

    // Does the tree have no right links, and at most on(left)
    // red links in a row on any path?
    private boolean is23() { return is23(root); }

    private boolean is23(Node x) {
        // 右子树没有红链接，没有两条相邻的红链接
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != null && isRed(x) && isRed(x.left)) return false;
        return is23(x.left) && is23(x.right);
    }

    // dose every path form the root to a leaf have the given number of black links?
    private boolean isBalanced() {
        // 所有子叶到根结点的路径上，黑链接个数相同
        // 1. 计算一条路径上黑结点的个数
        int black = 0;
        Node x = root;
        while (x != null) {
            if (! isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node x, int black) {
        // 2. 计算各个路径上的距离是否与最开始计算的相同
        if (x == null) return black == 0;
        if (! isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    public static void main(String[] args) throws InterruptedException {
        RedBlackBST<String, Integer> st = new RedBlackBST<>();
        for (int i = 0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        StdOut.println("check:" + st.check());
        StdOut.println();

        StdOut.println("height:" + st.height());
        StdOut.println();

        StdOut.println("G floor:" + st.floor("G"));
        StdOut.println("G ceiling:" + st.ceiling("G"));
        StdOut.println();

        StdOut.println("M floor:" + st.floor("M"));
        StdOut.println("M ceiling:" + st.ceiling("M"));
        StdOut.println();

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();

        for (int i = 0; i < 3; i++){
            StdOut.println("min:" + st.min() + "; max:" + st.max());
            st.deleteMin();
            st.deleteMax();
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println();

        st.delete(st.select(2));
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println();
    }
}
