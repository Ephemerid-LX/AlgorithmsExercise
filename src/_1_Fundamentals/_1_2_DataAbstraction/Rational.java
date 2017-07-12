package _1_Fundamentals._1_2_DataAbstraction;

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
