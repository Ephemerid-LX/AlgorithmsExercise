### 1.1.1
a|b|c
-|-|-
7|-|true

### 1.1.2
a           |b|c|d
-|-|-|-
1.61(double)|10.0(double)|true(boolean)|33(String)

### 1.1.3
```java
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * Exercise 1.1.3
 * Command:
 *  javac -cp .;{lib_path}\lib\algs4-master.jar {java_path}\Exc_1_1_3.java
 *  java -cp .;{lib_path}\lib\algs4-master.jar {package_path}.Exc_1_1_3 args0 args1 args2
 *
 */
public class Exc_1_1_3 {
    public static void main(String[] args){
        if (args.length < 3) {
            StdOut.println("please give third Int.");
            return;
        }
        if ( Integer.parseInt(args[0])== Integer.parseInt(args[1])
                && Integer.parseInt(args[1]) == Integer.parseInt(args[2])) {
            StdOut.println("equal");
            return;
        }
        StdOut.println("not equal");
    }
}

```

### 1.1.4
+ a.错误;语法错误  
```
  if (a > b) c = 0;
```
+ b.错误;缺少括号
```
  if (a > b) {c = 0;}
```
+ c.正确
+ d.错误；缺少分号;
```
  if (a > b ) c=0;else b =0;
```

### 1.1.5
```java
import edu.princeton.cs.algs4.StdOut;

/**
 * Exercise 1.1.5
 */
public class Exc_1_1_5 {
    public static void main(String[] args){
        if (args.length < 3) {
            StdOut.println("please give third Double.");
            return;
        }

        double d1 = Double.parseDouble(args[0]);
        double d2 = Double.parseDouble(args[1]);

        if (d1>0 && d1<1 && d2>0 && d1<0) {
            StdOut.println("true");
            return;
        }
        StdOut.println("false");
    }
}
```
### 1.1.6
0  
1  
1  
2  
3  
5  
8  
13  
21  
34  
55  
89  
144  
233  
377  
610  

### 1.1.7
+ a.3.0009  
  如何简化这个问题，使它看起来更清晰
+ b.999000
+ c.10000

### 1.1.8
+ a.b  
  字符型
+ b.197  
  计算时转换为int型
+ c.e  
  计算时先转换为int型，在被墙转为char型

### 1.1.9
```java
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.1.9
 * 十进制、二进制的相互转换
 */
public class Exc_1_1_9 {
    public static void main(String[] args){
        String s = "";
        int N = StdIn.readInt();
        for (int n = N; n > 0; n /= 2) {
            //商除2取余
            s = n%2 + s;
        }
        StdOut.println(s);
    }
}
```

### 1.1.10
int数组没有初始化

### 1.1.11
```java
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * exercise 1.1.10
 * 目前只有行和列都小于10时，是整齐的
 */
public class Exc_1_1_10 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int line = in.nextInt();
        boolean[][] array = getBooleanTwoDimensionalArray(row,line);
        String s = " ";
        for (int j = 0; j < array[0].length; j++) {
            s += " "+(j+1);
        }
        s += "\n";

        for (int i = 0; i < array.length; i++) {
            s = s+ (i+1);
            for (int j = 0; j < array[i].length; j++) {

                if (array[i][j] ) s = s + " *";
                else s = s + "  ";
            }
            s = s + "\n";
        }

        StdOut.println(s);
    }


    /**
     * 随机生成二维布尔数组
     * @param row 行数
     * @param line 列数
     * @return 随机的布尔数组
     */
    private static boolean[][] getBooleanTwoDimensionalArray(int row, int line){
        boolean[][] array = new boolean[row][line];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j< line; j++) {
                if (0.5 > StdRandom.uniform(2)) array[i][j] = false;
                else array[i][j] = true;
            }
        }
        return array;
    }
}
```
### 1.1.12
0  
1  
2  
3  
4  
5  
6  
7  
8  
9  

### 1.1.13
```java
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * exercise 1.1.13
 */
public class Exe_1_1_13 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int line = in.nextInt();
        int[][] array = getIntTwoDimensionalArray(row,line);
        //转置数组
        int[][] tranArray = new int[array[0].length][array.length];
        //打印原始二维数组，同时转置
        StdOut.println("原始数组：");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                //打印原始数组
                StdOut.printf("%4d", array[i][j]);
                //转置
                tranArray[j][i] = array[i][j];
            }
            StdOut.println();
        }
        StdOut.println();
        StdOut.println("转置数组：");
        for (int i = 0; i < tranArray.length; i++) {
            for (int j = 0; j < tranArray[i].length; j++) {
                //打印转置数组
                StdOut.printf("%4d", tranArray[i][j]);
            }
            StdOut.println();
        }
    }

    /**
     * 随机获取一个二维数组
     * @param row 行数
     * @param line 列数
     * @return 二维数组
     */
    private static int[][] getIntTwoDimensionalArray(int row, int line){
        int[][] array = new int[row][line];
        for (int i = 0; i < row; i++) {
            for (int j =0; j < line; j++) {
                array[i][j] =StdRandom.uniform(10);
            }
        }
        return array;
    }
}

```

### 1.1.14
`todo`
数学知识遗忘

### 1.1.15
```java
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * exercise 1.1.15
 */
public class Exe_1_1_15 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int l = in.nextInt();
        int[] a = randomArray(M,l);
        int[] b = histogram(a,M);

        int sumb = 0;
        for (int i = 0; i < b.length; i++){
            sumb += b[i];
        }
        StdOut.print("返回数组b[]的元素和:");
        StdOut.println(sumb);
        StdOut.print("数组a[]的长度:");
        StdOut.println(a.length);
    }

    /**
     * 题目描述内容：
     *  接受一个整型数组a[]和一个整数M，返回一个大小为M的数组，
     *  其中第i个元素的值为整数i在参数数组中出现的次数。如果a[]
     *  中的值均在0到M-1之间,返回数值中所有元素之和应该和a.length
     *  相等。
     * @param a 整型数组
     * @param M 整数
     * @return 数组
     */
    public static int[] histogram(int[] a, int M){
        int[] b = new int[M];
        for (int i = 0; i < M; i++) {
            b[i] = times(a,i);
        }
        return b;
    }


    /**
     * 计算整数x在,数组a中出现出的次数
     * @param a 数组
     * @param x 整数
     * @return 次数
     */
    private static int times(int[] a, int x){
        int times = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) times++;
        }
        return  times;
    }

    /**
     * 获取一个随机的整型数组,
     * 数组大小为0~l-1,
     * 元素大小为0~k-1
     * @param l 数组最大长度
     * @param k 整数
     * @return 整型数组
     */
    private static int[] randomArray(int k, int l){
        int[] array = new int[StdRandom.uniform(l)];
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(k);
        }
        return array;
    }
}

```
### 1.1.16
311361142246


### 1.1.17
没有能够跳出递归的情况；
收敛条件应该在调用递归之前，如`1.1.16`那样。

### 1.1.18
+ mystery(2,25):50,修改后:50
+ mystery(3,11):33,修改后:48

### 1.1.20
```java
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * exercise 1.1.20
 * 阶乘:
 *  n! = 1*2*3*...*n
 * 对数运算:
 *  ln是以e为底的对数
 *  logn(xy) = logn(x)+logn(y)\\公式打不出来
 *
 */
public class Exe_1_1_20 {
    public static void main(String[] args){
        StdOut.println(exe1_1_20(1));

    }

    /**
     * 计算阶乘的自然对数
     * @param n
     * @return
     */
    public static double exe1_1_20(int n){
        if (n == 1) return 1;
        return Math.log(n)+Math.log(n-1);
    }
}
```

### 1.1.21
```java
import edu.princeton.cs.algs4.In;

/**
 *  exercise 1.1.21
 */
public class Exe_1_1_21 {
    public static void main(String[] args){
        In in = new In("src/_1_Fundamentals/_1_1_programmingModel/exe_1_1_21.txt");
        String line;
        while (in.hasNextLine()) {
            line = in.readLine();
            String[] values = line.split(",");
            System.out.printf("%-15s%-10.3f%-10.3f%-10.3f",values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                    Double.parseDouble(values[1])+Double.parseDouble(values[2]));
            System.out.println();
        }
    }
}

```
