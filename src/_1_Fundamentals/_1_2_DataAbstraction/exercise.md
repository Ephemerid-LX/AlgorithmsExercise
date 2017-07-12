### 1.2.1
```java
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

/**
 * exercise 1.2.1
 */
public class Point2DTest {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);

        //画点
        Point2D[] point2DS = new Point2D[n];
        for (int i = 0; i < n; i++) {
            double x = Math.random();
            double y = Math.random();
            Point2D p = new Point2D(x, y);
            point2DS[i] = p;
            StdDraw.setPenRadius(.005);
            p.draw();
        }
        //计算所有的距离
        double[] distance = new double[sum(n-1)];
        int index = 0;
        for (int i = 0; i < n-1; i++)
            for (int j = i+1; j < n; j++)
                distance[index++] = point2DS[i].distanceTo(point2DS[j]);

        double minDis = StdStats.min(distance);
        StdOut.println(minDis);
    }

    /**
     * n+(n-1)+(n-2)+..+1
     * @param n 数
     * @return 结果
     */
    public static int sum(int n) {
        if (n < 0) throw new IllegalArgumentException();
        if (n == 0) return 0;
        int result = 0;
        for (int i = 1; i <= n; i++)
            result += i;
        return result;
    }
}
```

### 1.2.4
> hello  
> world

### 1.2.6
```java
import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.2.6
 */
public class CircularRotation {
    /**
     * 判断是否为回环变位
     * 明显答案更好:
     *  Solution: (s.length() == t.length()) && (s.concat(s).indexOf(t) >= 0)
     * @param s
     * @param t
     * @return
     */
    public static boolean is(String s, String t) {
        if (s == null || "".equals(s) || t == null || "".equals(t)) return false;
        if (s.length() != t.length()) return false;
        String key = s.substring(0, 1);
        int a = t.indexOf(key);
        if (a == -1 ) return false;
        String t1 = t.substring(a, t.length()) + t.substring(0, a);
        if (!t1.equals(s)) return false;
        return true;
    }

    public static void main(String[] args){
        String t = args[0];
        String s = args[1];
        StdOut.println(is(s, t));
        StdOut.println((s.length() == t.length()) && (s.concat(s).indexOf(t) >= 0));
    }
}

```
### 1.2.7
倒序

### 1.2.13&1.2.14
```java
import edu.princeton.cs.algs4.Date;

/**
 * exercise 1.2.13/14
 */
public class Transaction {
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount){
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Transaction(String transaction){
        String[] info = transaction.split(",");
        if (info.length != 3) throw new IllegalArgumentException("交易信息错误！");
        this.who = info[0];
        this.when = new Date(info[1]);
        this.amount = Double.parseDouble(info[2]);
    }

    public String who(){
        return who;
    }

    public Date when(){
        return when;
    }

    public double amount(){
        return amount;
    }

    public String toString(){
        return who + "," + when + "," +amount;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        if (this.who != that.who) return false;
        if (!this.when.equals(that.when)) return false;
        if (this.amount != that.amount) return false;
        return true;
    }
}
```
### 1.2.16
```java
/**
 * exercise 1.1.17
 * 没有考虑到一些特殊情况。
 */
public class Rational {
    private final int nu;
    private final int de;

    public int getNu(){
        return nu;
    }

    public int getDe(){
        return de;
    }

    public Rational(int numerator, int denominator){
        int divisor = euclid(numerator, denominator);
        this.nu = numerator/divisor;
        this.de = denominator/divisor;
    }

    //有理数相加
    public Rational plus(Rational b){
        int bnu = b.getNu(), bde = b.getDe();
        if (this.de == bde)
            return new Rational(this.nu + bnu, this.de + bde);
        int numerator = this.nu * bde + this.de * bnu;
        int denominator = this.de * bde;
        return new Rational(numerator, denominator);
    }

    public Rational minus(Rational b){
        int bnu = b.getNu(), bde = b.getDe();
        if (this.de == bde)
            return new Rational(this.nu - bnu, this.de - bde);
        int numerator = this.nu * bde - this.de * bnu;
        int denominator = this.de * bde;
        return new Rational(numerator, denominator);
    }

    //有理数相乘
    public Rational times(Rational b){
        int bnu = b.getNu(), bde = b.getDe();
        int numerator = this.nu * bnu;
        int denominator = this.de * bde;
        return new Rational(numerator, denominator);
    }

    //有理数相除
    public Rational divides(Rational b){
        int bnu = b.getNu(), bde = b.getDe();
        int numerator = this.nu * bde;
        int denominator = this.de * bnu;
        return new Rational(numerator, denominator);
    }

    public boolean equals(Rational that){
        if (this == that) return true;
        if (that == null) return false;
        return (this.nu == that.getNu()) &&
                (this.de == that.getDe());
    }

    public String toString(){
        return nu +"/"+ de;
    }


    //欧几里得算法
    private static int euclid(int a, int b){
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0) return a;
        return euclid(b, a%b);
    }
}
```
### 1.2.18
`todo`
方差、期望等相关概念公式复习。  
该类是用来存储方差和标准差
