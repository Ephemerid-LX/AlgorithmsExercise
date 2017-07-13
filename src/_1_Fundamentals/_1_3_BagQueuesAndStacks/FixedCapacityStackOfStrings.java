package _1_Fundamentals._1_3_BagQueuesAndStacks;

/**
 * 只用于String类型的栈
 */
public class FixedCapacityStackOfStrings {
    private String[] s;
    private int n;

    public FixedCapacityStackOfStrings(int cap){
        s = new String[cap];
    }

    // 进栈
    public void push(String item){
        s[n++] = item;
    }

    // 出栈
    public String pop(){
        return s[--n];
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    // exercise 1.3.1
    public boolean isFull(){
        return n == s.length;
    }
}
