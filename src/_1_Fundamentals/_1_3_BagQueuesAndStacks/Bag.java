package _1_Fundamentals._1_3_BagQueuesAndStacks;

import java.util.Iterator;

/**
 * 算法 1.4
 *      链表实现Bag,实现Iterable
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;
    private int n;

    private class Node{
        private Item item;
        private Node next;
    }

    public void add (Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return first == null;
    }

    //迭代
    @Override
    public Iterator<Item> iterator() {
        return new LinkIterator();
    }

    private class LinkIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.next == null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
