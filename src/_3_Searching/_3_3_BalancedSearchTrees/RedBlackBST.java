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
    }

    public RedBlackBST(){

    }

    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/
    private boolean isRed(Node h){
        return h.color;
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

        // 2.如果key大于h.key，则在h的右链接中插入

        // 3.如果key小于h.key，则在h的左链接中插入

        // 4.如果key等于h.key，则更新值

        // 5.在插入或者更新后，
        // 5.1 如果父结点是红色结点
        // 5.1.1 如果新结点是左结点，且父节点是红色结点，则右旋，再反转颜色

        // 5.1.2 如果新结点是右结点，且父结点是红色结点，则左旋，再右旋，最后转换颜色

        // 5.2 如果父结点不是红色结点
        // 5.2.1 如果新结点的兄弟结点（父节点的另外一个结点）为红色结点，则翻转颜色

        // 5.2.2 如果新结点在右侧，则左旋

        // 5.2.3 如果新结点在左侧，则右旋

        // 6 返回结点，并认为其为新结点
        return null;
    }


    public void delete(Key key){

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
