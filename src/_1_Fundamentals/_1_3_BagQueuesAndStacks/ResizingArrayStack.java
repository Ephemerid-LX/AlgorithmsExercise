package _1_Fundamentals._1_3_BagQueuesAndStacks;

import java.util.Iterator;

/**
 * 大小可调
 * 算法 1.1
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];
    private int n = 0;

    // 调整数组大小
    private void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) temp[i] = a[i];
        a = temp;
    }

    /**
     * 添加元素到栈顶
     * @param item 元素
     */
    public void push(Item item){
        if (a.length == n) resize(2 * a.length);
        a[n++] = item;
    }

    /**
     * 从栈底取出元素
     * @return 元素
     */
    public Item pop(){
        if (n < a.length/4) resize(a.length/2);
        return a[--n];
    }

    /**
     * 是否为空
     */
    public boolean isEmpty(){
        return n == 0;
    }

    /**
     * 元素个数
     * @return 元素个数
     */
    public int size(){
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{

        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}
