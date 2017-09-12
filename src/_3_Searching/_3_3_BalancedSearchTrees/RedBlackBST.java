package _3_Searching._3_3_BalancedSearchTrees;

import com.sun.org.apache.regexp.internal.RE;

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

    private class Node{
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key key, Value val, int size, boolean color){
            this.key = key;
            this.val = val;
            this.size = size;
            this.color = color;

        }
    }

    public RedBlackBST(){

    }

    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/
    private boolean isRed(Node x){
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x){
        if (x == null) return 0;
        return x.size;
    }

    public int size(){
        return size(root);
    }

    public boolean isEmpty(){
        return size(root) == 0;
    }

    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/
    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    private Value get(Node x, Key key){
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

    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/
    public void put(Key key, Value val){
        //1.如果key为null,则出异常
        //2.如果val为null，则删除key
        //3.else put
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null){
            delete(key);
            return;
        }

        put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val){
        // 1.如果h为Null，则新建结点，为红色结点
        if (h == null) return new Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        // 2.如果key大于h.key，则在h的右链接中插入
        if (cmp > 0) put(h.right, key, val);
        // 3.如果key小于h.key，则在h的左链接中插入
        if (cmp < 0) put(h.left, key, val);
        // 4.如果key等于h.key，则更新值
        else h.val = val;

        /*
        考虑复杂，并不需要靠考虑父结点，收到了 flipColor() 中assert的影响
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
        if (!isRed(h.left) && isRed(h.right)) rotateLeft(h);
        // 5.2 如果左子结点是红色结点，左子结点的左子节点也是红色结点，则右旋
        if (isRed(h.left) && isRed(h.left.left)) rotateRight(h);
        // 5.3 如果左子结点和右子结点都是红色结点，则颜色转换
        if (isRed(h.left) && isRed(h.right)) flipColor(h);

        // 这句是否不需要，因为在左旋右旋的时候就重新计算过
        h.size = size(h.left) + size(h.right);
        // 6. 返回该结点
        return h;
    }

    public void delete(Key key){

    }

    private Node delete(Node h, Key key) {

        return null;
    }
    /***************************************************************************
     *  Red-black tree helper functions.
     ***************************************************************************/
    private Node rotateLeft(Node h){
        //h ！= null 并且其右链接为红色
        //assert h != null && isRed(h.right)
        // 1.红色右链接记为x
        Node x = h.right;
        // 2.h的右链接指向x的左链接
        h.right = x.left;
        // 3.x右链接指向h
        x.right = h;
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

    private Node rotateRight(Node h){
        // assert h != null && isRed(h.right)
        Node x = h.left;
        h.left = x.right;
        x.right = x;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColor(Node h){
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null)
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !isRed(h);
        h.right.color = !isRed(h.right);
        h.left.color = !isRed(h.left);
    }

}
