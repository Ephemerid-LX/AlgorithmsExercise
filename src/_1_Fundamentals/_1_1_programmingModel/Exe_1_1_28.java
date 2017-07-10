package _1_Fundamentals._1_1_programmingModel;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/**
 * exercise 1.1.28
 * 没有使用List等对象
 */
public class Exe_1_1_28 {
    public static void main(String[] args) {

        // 读取文件
        In in = new In("src/_1_Fundamentals/_1_1_programmingModel/exe_1_1_28.txt");
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        //
        int[] unduplicated = new int[whitelist.length], tmp = new int[0];
        int index = 0;

        //下标数组,临时下标数组
        int[] allIndex = new int[0], indexTmp;

        for (int i = 0; i < whitelist.length; i++) {
            //如果在下标数组中存在，则跳过
            if (BinarySearch.indexOf(allIndex, i) != -1) continue;

            int[] indexes = indexOf(whitelist, whitelist[i]);
            if (indexes.length > 1) {
                //将查到的下标添加到下标数组中
                indexTmp = new int[allIndex.length + indexes.length];
                for (int j = 0; j < allIndex.length; j++) indexTmp[j] = allIndex[j];
                for (int p = indexes.length; p > 1; p--) indexTmp[indexTmp.length - p] = indexes[indexes.length - p + 1];
                allIndex = indexTmp;
                Arrays.sort(allIndex);
            }
            //将不重复的数组添加
            unduplicated[index++] = whitelist[i];
        }

        if (index < whitelist.length) {
            //缩小删除后数组容量
            for (int i = 0; i < index; i++) {
                tmp = new int[index];
                tmp[i] = unduplicated[i];
                StdOut.println(unduplicated[i]);
            }
            unduplicated = tmp;
        }
    }

    /**
     * 二分法无法查找如{1,1,1,1,1}这样就有多个重复元素的index
     *
     * 将不会返回第一次出现该值下下标
     * @param a 数组
     * @param key 需要查找的值
     * @return 具有想同值的下标
     */
    public static int[] indexOf(int[] a, int key){
        int[] indexes = new int[0], tmp;
        for(int i = 0; i < a.length; i++){
            if (key == a[i]) {
                tmp = new int[indexes.length+1];
                for (int j = 0; j < indexes.length; j++) {
                    tmp[j] = indexes[j];
                }
                tmp[indexes.length] = i;
                indexes = tmp;
                tmp = null;
            }
        }
        return indexes;
    }
}
