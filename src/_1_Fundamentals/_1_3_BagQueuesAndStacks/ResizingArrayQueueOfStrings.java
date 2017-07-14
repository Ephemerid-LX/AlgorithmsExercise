package _1_Fundamentals._1_3_BagQueuesAndStacks;

/**
 * exercise 1.3.14
 *
 */
public class ResizingArrayQueueOfStrings {
    private String[] a;
    private int n;

    public ResizingArrayQueueOfStrings(){
        a = new String[100];
        n = 0;
    }

    public void enqueue(String item){
        a[n++] = item;
        if (n == a.length) resize(2 * a.length);
    }

    public String dequeue(){
        String s = a[0];

        String[] temp = new String[a.length];
        for (int i = 1; i < n; i++) temp[i-1] = a[i];
        a = temp;
        n--;

        if (n < a.length/4) resize(a.length/2);
        return s;

    }

    public boolean isEmpty(){
        return 0 == n;
    }

    public int size(){
        return n;
    }

    private void resize(int max){
        String[] temp = new String[max];
        for (int i = 0; i < n; i++) temp[i] = a[i];
        a = temp;
    }
}
