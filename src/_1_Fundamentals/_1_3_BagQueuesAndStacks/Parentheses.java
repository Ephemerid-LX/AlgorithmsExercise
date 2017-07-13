package _1_Fundamentals._1_3_BagQueuesAndStacks;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 判断括号是否匹配完整
 */
public class Parentheses {
    public static void main(String[] args){
        In in = new In("src/_1_Fundamentals/_1_3_BagQueuesAndStacks/parentheses.txt");
        Stack<String> left = new Stack<>();
        boolean is = false;

        while (!in.isEmpty()) {
            String s = in.readString();
            if (s.equals(")")) is = !left.isEmpty() && "(".equals(left.pop());
            else if (s.equals("]")) is = !left.isEmpty() && "[".equals(left.pop());
            else if (s.equals("}")) is = !left.isEmpty() && "{".equals(left.pop());
            else {
                left.push(s);
                continue;
            }
            if (!is) break;
        }
        // 如果最后还有元素，说明并没有匹配
        if (!left.isEmpty()) is = false;

        StdOut.println(is);
        in.close();
    }
}
