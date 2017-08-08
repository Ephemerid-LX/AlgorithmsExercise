package _2_Sorting._2_5_SortingApplications;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/******************************************************************************
 *  Compilation:  javac Domain.java
 *  Execution:    java Domain < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *
 *  Sort by reverse domain name.
 *
 *  % java Domain < domains.txt
 *  com.apple
 *  com.cnn
 *  com.google
 *  edu.princeton
 *  edu.princeton.cs
 *  edu.princeton.cs.bolle
 *  edu.princeton.cs.www
 *  edu.princeton.ee
 *
 ******************************************************************************/
public class Domain implements Comparable<Domain>{
    private String[] fields;
    private int n;

    // store fields in reverse order
    public Domain(String name){
        fields = name.split("\\.");
        n = fields.length;
    }

    // return string representation - fields , delimited by .
    public String toString(){
        if(n == 0) return "";
        String s = fields[0];
        for (int i = 1; i < n; i++)
            s = fields[i] + "." + s;
        return s;
    }

    // compare by reverse domian name
    @Override
    public int compareTo(Domain that) {
        for (int i = 0; i < Math.min(this.n, that.n); i++){
            String v = this.fields[this.n - i - 1];
            String w = that.fields[that.n - i - 1];
            int r = v.compareTo(w);
            if (r < 0) return -1;
            else if (r > 0) return 1;
        }
        return this.n - that.n;
    }

    public static void main(String[] args){

        String[] names = StdIn.readAllStrings();
        Domain[] domains = new Domain[names.length];
        for (int i = 0; i < names.length; i++)
            domains[i] = new Domain(names[i]);

        Arrays.sort(domains);

        for (int i = 0; i < domains.length; i++){
            StdOut.println(domains[i]);
        }
    }

}
