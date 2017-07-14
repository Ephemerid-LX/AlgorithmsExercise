package _1_Fundamentals._1_3_BagQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

/**
 * Dijikstra 的双栈算数表达式求值算法
 * 官方用例在:
 *      lib\someSolutionCode\
 * 使用限制：
 *      每一步都必须用()显示的括起来
 */
public class Evaluate {
    public static void main(String[] args){
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        //直接使用hasNext会一直阻塞
        //StdIn中直接使用的hasNext,所以这里不使用StdIn.isEmpty()
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNext("=")) {
            String s = scanner.next();
            if (s.equals("("));
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")){
                String op = ops.pop();
                Double v = vals.pop();
                if (op.equals("+")) v = vals.pop() + v;
                else if (op.equals("-")) v = vals.pop() - v;
                else if (op.equals("*")) v = vals.pop() * v;
                else if (op.equals("/")) v = vals.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            }
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
