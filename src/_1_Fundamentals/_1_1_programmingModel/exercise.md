### 1.1.1
a|b|c
-|-|-
7|-|true

### 1.1.2
a|b|c|d
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

### 1.1.24
```java
import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.1.24
 * 欧几里得算法（Euclidean algorithm，辗转相除法）
        */
public class Exe_1_1_24 {
    public static void main(String[] args){
        StdOut.println(euclid(1111111,1234567));
    }
    // 欧几里得算法
    public static int euclid(int a, int b){
        StdOut.println("a="+a+"; b="+b);
        if (b == 0) return a;
        return euclid(b, a%b);
    }
}
```
1111111与1234567互素

### 1.1.25


## 提高题
### 1.1.26
每次都交换两个元素

### 1.1.27

### 1.1.28
```java
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
```

### 1.1.29
```java
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * exercise 1.1.29
 */
public class Exe_1_1_29 {
    public static void main(String[] args){
        // 读取文件
        In in = new In("src/_1_Fundamentals/_1_1_programmingModel/exe_1_1_29.txt");
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);
        int i = rank(4, whitelist);
        int j = count(4, whitelist);

        for (int n = i; n <= i + j - 1; n++) {
            StdOut.println(whitelist[n]);
        }
    }

    /**
     * 返回等于该键的元素的数量
     * @param key 键
     * @param a 数组
     * @return 等于该键的元素的数量
     */
    public static int count(int key, int[] a){
        return Exe_1_1_28.indexOf(a,key).length;
    }

    /**
     * 返回数组中小于该键的元素数量
     * @param key 键
     * @param a 数组
     * @return 小于该键的元素数量
     */
    public static int rank(int key, int[] a){
        int[] count = Exe_1_1_28.indexOf(a, key);
        if (count.length == 0) return 0;
        return count[0];
    }
}
```

### 1.1.30
```java
import edu.princeton.cs.algs4.StdOut;

/**
 * exercise1.1.30
 */
public class Exe_1_1_30 {
    public static void main(String[] args){
        boolean[][] array = new boolean[5][5];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = Exe_1_1_24.euclid(i ,j) == 1 ? true : false;
                StdOut.printf("%-8s", array[i][j]);
            }
            StdOut.println();
        }
    }
}
```

### 1.1.31
+ 圆上任意一点的坐标:  
  x = x0 + r*cos(θ);
  y = y0 + r*sin(θ);
+ θ计算:  
  θ = 2π*(i/N) = π*(a°)/(180°)

```java
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * exercise 1.1.31
 */
public class Exe_1_1_31 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StdOut.println("请输入需要点的个数：");
        int N = in.nextInt();
        StdOut.println("请输入概率(0~1):");
        double p = in.nextDouble();

        draw(N,p);
        in.close();
    }

    public static void draw(int N, double p){
        StdDraw.setCanvasSize(1024,1024);
        StdDraw.setScale(-1.1,1.1);
        StdDraw.setPenRadius(0.05);
        //画点
        double[][] coordinate = new double[N][2];
        for (int i = 0; i < N; i++) {
            coordinate[i][0] = Math.cos(2*Math.PI*i/N);
            coordinate[i][1] = Math.sin(2*Math.PI*i/N);
            StdDraw.point(coordinate[i][0], coordinate[i][1]);
        }
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.GRAY);
        //画线
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (StdRandom.bernoulli(p)) {
                    StdDraw.line(
                            coordinate[i][0],coordinate[i][1],
                            coordinate[j][0],coordinate[j][1]
                    );
                }
            }
        }

    }
}
```
### 1.1.32
+ 统计分布
+ 计算每个矩形中心坐标

```java
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

/**
 * exercise 1.1.32
 */
public class Exe_1_1_32 {
    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        double l = Double.parseDouble(args[1]);
        double r = Double.parseDouble(args[2]);
        double[] a = StdIn.readAllDoubles();

        draw(N, l, r, a);
    }

    public static void draw (int N, double l, double r, double[] a){
        double withd = (r-l)/N;
        int[] statistics =new int[N];

        // 统计区分分布
        // 用需要统计的值 除以宽 取商(商是整数)
        for (int i = 0; i < a.length; i++) {
            if (a[i] <= l || a[i] >= r ) continue;
            int index = (int)((a[i] - l)/withd);
            statistics[index] += 1;
        }
        // setCanvasSize必须在之前设置，不然图像显示不出来
        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setXscale(l, r);
        StdDraw.setYscale(0, StdStats.max(statistics));

        // 计算每个矩形中心坐标点
        for (int i = 0; i < N; i++) {
            double x = l + (i+0.5)* withd;
            double y = statistics[i]/2.0;
            StdOut.println(x+","+y);
            StdDraw.filledRectangle(x, y, withd/2.0, statistics[i]/2.0);
        }
    }
}
```

### 1.1.35
```java
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * exercise 1.1.35
 */
public class Exe_1_1_35 {
    private static final int SIDES = 6;
    private static final double[] standard = standardProbabilityDistribution();

    public static void main(String[] args){
        StdOut.println(minN());
    }

    /**
     * 获得标准分布概率
     * @return 标准分布概率
     */
    public static double[] standardProbabilityDistribution(){
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            for (int j = 1; j <= SIDES; j++) {
                dist[i+j] += 1.0;
            }
        }

        for (int k = 2; k <= 2*SIDES; k++) {
            dist[k] /= 36.0;
        }
        return dist;
    }

    /**
     * 获得实验分布概率
     * @param N 实验次数
     * @return 实验分布概率
     */
    public static double[] experimentalData(int N){
        double[] dist = new double[2*SIDES+1];
        for (int l = 0; l < N; l++) {
            int i = StdRandom.uniform(1,7);
            int j = StdRandom.uniform(1,7);
            dist[i+j] += 1.0;
        }
        for (int k = 2; k <= 2*SIDES; k++) {
            dist[k] /= N;
        }
        return dist;
    }

    /**
     * 求最小N,保证实验数据与准确数据吻合程度到达小数点后三位
     * 需要注意的是,
     *  实验具是随机性的,所以每次实验的结果可能不一样
     * @return 最小N
     */
    public static int minN(){
        int N = 0;
        int equalsTimes = 2;
        while (equalsTimes != 13) {
            N++;
            equalsTimes = 2;
            double[] experimental = experimentalData(N);

            for (int k = 2; k <= 2 * SIDES; k++) {
                int point = -1;
                //这部分的值是固定的，可以提出来
                String s = String.format("%.5f", standard[k]);
                point = s.indexOf(".");
                String subS = s.substring(0, point + 4);

                String e = String.format("%.5f", experimental[k]);
                point = e.indexOf(".");
                String subE = e.substring(0, point + 4);
                if (subS.equals(subE)) equalsTimes++;

                StdOut.printf("%-10s", subS);
                StdOut.printf("%-10s", subE);
                StdOut.println();
            }
            StdOut.println("===================================");
        }
        return N;
    }
}
```

### 1.1.36 && 1.1.37
```java
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * exercise 1.1.36 && 1.1.37
 */
public class ShuffleTest {
    public static void main(String[] args){
        StdOut.println("请输入整数M:");
        int M = StdIn.readInt();
        StdOut.println("请输入整数N:");
        int N = StdIn.readInt();
        printShuffle(M,N);
        printShuffle2(M,N);
    }

    /**
     * 1.1.36
     * @param M 数组大小
     * @param N 乱序次数
     */
    public static void printShuffle(int M, int N) {
        int[] a = new int[M];
        int[][] b = new int[M][M];
        int[][] c = new int[M][M];
        for (int k = 0; k < N; k++) {

            for (int l = 0; l < M; l++) {
                a[l] = l;
            }

            StdRandom.shuffle(a);

            // todo
            //此处的两种形式如何理解
            //两种结果只是产生互为转置矩阵的结果
            for (int i = 0; i < M; i++) {
                //
                b[i][indexOf(a, i)]++;
                //
                c[i][a[i]]++;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++)
                StdOut.printf("%-10d", b[i][j]);
            StdOut.println();
        }
        StdOut.println();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++)
                StdOut.printf("%-10d", c[i][j]);
            StdOut.println();
        }
        StdOut.println();
    }

    /**
     * 1.1.37
     * @param M 数组大小
     * @param N 乱序次数
     */
    public static void printShuffle2(int M, int N) {
        int[] a = new int[M];
        int[][] b = new int[M][M];
        for (int k = 0; k < N; k++) {

            for (int l = 0; l < M; l++) {
                a[l] = l;
            }

            for (int j = 0; j < M; j++) {
                int r = StdRandom.uniform(M);     // [0,M-1)
                int temp = a[j];
                a[j] = a[r];
                a[r] = temp;
            }
            for (int i = 0; i < M; i++) {
                b[i][indexOf(a, i)]++;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++)
                StdOut.printf("%-10d", b[i][j]);
            StdOut.println();
        }
    }

    public static int indexOf(int[]a ,int key){
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i;
        }
        return -1;
    }
}
```
