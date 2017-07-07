package _1_Fundamentals._1_1_programmingModel;

/**
 * exercise 1.1.12
 */
public class Exe_1_1_12 {
    public static void main(String[] args){
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) a[i] = 9-i;
        for (int i = 0; i < 10; i++) a[i] = a[a[i]];
        for (int i = 0; i < 10; i++) System.out.println(i);
    }
}
