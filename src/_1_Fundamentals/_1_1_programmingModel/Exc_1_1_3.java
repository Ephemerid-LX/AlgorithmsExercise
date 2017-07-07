package _1_Fundamentals._1_1_programmingModel;

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
