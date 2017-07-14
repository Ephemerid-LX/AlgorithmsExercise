package _1_Fundamentals._1_3_BagQueuesAndStacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 后序转中序
 *
 *
 */
public class InfixToPostfix {

    /**
     * 这里我只考虑和只有"+","-","*","\","(",")"的情况
     * 用到一个输出队列和一个运算符栈
     * 1.遇到数字，加入到输出队列中
     * 2.遇到运算符：
     *      a.如果运算符栈为空，将其压入栈
     *      b.如果运算符的优先级小于栈顶运算符优先级，则
     *          从栈中弹出运算符，并将弹出的运算符加入到输出队列中，
     *          循环以上步骤，直至输入运算符优先级大于或等于栈顶优先级
     *          最后将输入运算符压入栈中
     *      c.如果运算符优先级大于或等于，则将其压入栈中
     * 3.遇到"(",将其压入栈中
     * 4.遇到")",将其丢弃,并从栈中弹出运算符进入到输出队列中,直至遇到"(",
     *      并将其是丢弃
     * 5.没有输入后,将栈中所有元素弹出,进入到输出队列中
     * @param infix 中序表达式
     */
    public static String transfer(String infix){
        String[] input = infix.split("\\s+");
        Stack<String> ops = new Stack<>();
        Queue<String> output = new Queue<>();
        for (String s : input) {
            String top = null;

            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
                if (ops.isEmpty()) { // 第一个与
                    ops.push(s);
                    continue;
                }

                top = ops.peek();
                  // 此处注释的代码为优先级相同的时候的出栈操作
                  // 但我绝对优先级相同是可以不做出栈，只要保证整个栈中
                  // 没有低优先级的在上面，高优先级的在下面即可。
//                while ((s.equals("+") || s.equals("-")) && (top.equals("+") || top.equals("-"))){
//                    output.enqueue(ops.pop());
//                    if (ops.isEmpty()) break;
//                    top = ops.peek();
//                }
//                while ((s.equals("*") || s.equals("/")) && (top.equals("*") || top.equals("/"))){
//                    output.enqueue(ops.pop());
//                    if (ops.isEmpty()) break;
//                    top = ops.peek();
//                }
//                while (s.equals("^") && (top.equals("^"))){
//                    output.enqueue(ops.pop());
//                    if (ops.isEmpty()) break;
//                    top = ops.peek();
//                }

                while ((s.equals("+") || s.equals("-")) && (top.equals("*") || top.equals("/") || top.equals("^"))) {
                    output.enqueue(ops.pop());
                    if (ops.isEmpty()) break;
                    top = ops.peek();
                }

                while ((s.equals("*") || s.equals("/")) && top.equals("^")) {
                    output.enqueue(ops.pop());
                    if (ops.isEmpty()) break;
                    top = ops.peek();
                }

                ops.push(s);
            }
            else if (s.equals("(")) ops.push(s);
            else if (s.equals(")")) {
                top = ops.pop();
                while (!top.equals("(")) {
                    output.enqueue(top);
                    top = ops.pop();
                }
            }
            else output.enqueue(s);
        }

        while (!ops.isEmpty()) {
            output.enqueue(ops.pop());
        }

        String postfix = "";
        for (String s : output) {
            postfix = postfix + s+ " ";
        }
        return postfix;
    }

    public static void main(String[] args){
        String infix = StdIn.readLine();
        //String infix = "2 * 3 / ( 2 - 1 ) + 3 * ( 4 - 1 )";
        //String infix = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3";
        //String infix = "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )";

        StdOut.println(transfer(infix));
    }
}
