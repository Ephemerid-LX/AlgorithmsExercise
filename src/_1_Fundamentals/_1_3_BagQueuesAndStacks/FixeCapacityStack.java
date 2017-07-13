package _1_Fundamentals._1_3_BagQueuesAndStacks;

import edu.princeton.cs.algs4.In;

/**
 * 改进
 * 使用泛型
 */
public class FixeCapacityStack<Item> {
    private Item[] a;
    private int n;
    public FixeCapacityStack(int cap){
        a = (Item[])new Object[cap];
    }

    public void push(Item item){
        a[n++] = item;
    }

    public Item pop(){
        return a[--n];
    }

    public boolean isEmpty(){
        return 0 == n;
    }

    public int size(){
        return n;
    }
}
