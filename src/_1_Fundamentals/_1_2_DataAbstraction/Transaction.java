package _1_Fundamentals._1_2_DataAbstraction;

import edu.princeton.cs.algs4.Date;

/**
 * exercise 1.2.13/14/19
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
        String[] info = transaction.split(",");// \\s+
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
