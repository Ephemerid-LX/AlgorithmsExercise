package _1_Fundamentals._1_1_programmingModel;

/**
 *
 * 直接遍历查找
 *
 */
public class BruteForceSearch {
    public static int indexOf(int[] a, int key){
        for (int i = 0; i < a.length; i++)
            if (a[i] == key) return i;
        return -1;
    }
}
