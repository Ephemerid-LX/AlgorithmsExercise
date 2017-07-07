package _1_Fundamentals._1_1_programmingModel;

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
