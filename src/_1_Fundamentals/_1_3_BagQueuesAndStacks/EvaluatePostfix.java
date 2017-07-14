package _1_Fundamentals._1_3_BagQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 计算后序表达式
 * 这里我只考虑和只有"+","-","*","\","(",")"的情况
 */
public class EvaluatePostfix {
    public static double evaluate(String postfix){
        String[] input = postfix.split("\\s+");
        Stack<Double> vals = new Stack<>();
        for (String s : input) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^") ) {
                if (vals.isEmpty()) throw new IllegalArgumentException("后序表达式错误！");
                double later = vals.pop();
                double  front = vals.pop();
                if (s.equals("+")) vals.push(front + later);
                else if (s.equals("-")) vals.push(front - later);
                else if (s.equals("*")) vals.push(front * later);
                else if (s.equals("/")) vals.push(front / later);
                else if (s.equals("^")){
                    double result = 1;
                    for (int i = 0; i < later; i++) result *= front;
                    vals.push(result);
                }
            } else vals.push(Double.parseDouble(s));// 数字压入栈中
        }
        return vals.pop();
    }

    public static void main(String[] args){
        String postfix = StdIn.readLine();
        //String postfix = "2 3 2 1 - / * 3 4 1 - * + ";
        StdOut.println(evaluate(postfix));
    }
}
