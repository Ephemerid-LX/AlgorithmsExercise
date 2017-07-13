package _1_Fundamentals._1_3_BagQueuesAndStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 算法1.2
 *      链表实现Stack
 */
public class Stack<Item> {
    private Node first;//栈顶
    private int n;//结点个数

    //结点
    private class Node{
        private Item item;
        private Node next;
    }

    // 添加一个结点
    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    // 取栈顶结点
    public Item pop(){
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return n;
    }

    // exercise 1.3.7
    public Item peek (){
        return first.item;
    }


//    // 测试代码
//    public static void main(String[] args){
//        Stack<String> stack = new Stack<>();
//        In in = new In("src/_1_Fundamentals/_1_3_BagQueuesAndStacks/tobe.txt");
//        // 使用tobe.txt
//        while (!in.isEmpty()) {
//            String s = in.readString();
//            if (!s.equals("-"))
//                stack.push(s);
//            else if (!stack.isEmpty())
//                StdOut.print(stack.pop()+" ");
//        }
//        StdOut.println("(" + stack.size() + " left on stack)");
//    }
}

