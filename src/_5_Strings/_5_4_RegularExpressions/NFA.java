package _5_Strings._5_4_RegularExpressions;

import _4_Graphs._4_2_DirectedGraphs.Digraph;
import _4_Graphs._4_2_DirectedGraphs.DirectedDFS;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac NFA.java
 *  Execution:    java NFA regexp text
 *  Dependencies: Stack.java Bag.java Digraph.java DirectedDFS.java
 *
 *  % java NFA "(A*B|AC)D" AAAABD
 *  true
 *
 *  % java NFA "(A*B|AC)D" AAAAC
 *  false
 *
 *  % java NFA "(a|(bc)*d)*" abcbcd
 *  true
 *
 *  % java NFA "(a|(bc)*d)*" abcbcbcdaaaabcbcdaaaddd
 *  true
 *
 *  Remarks
 *  -----------
 *  The following features are not supported:
 *    - The + operator
 *    - Multiway or
 *    - Metacharacters in the text
 *    - Character classes.
 *
 ******************************************************************************/
public class NFA {

    private Digraph graph;
    private String regexp;
    private final int m;

    public NFA(String regexp){
        this.regexp = regexp;
        this.m = this.regexp.length();

        Stack<Integer> ops = new Stack<>();
        graph = new Digraph(m + 1);
        for(int i = 0; i < m; i++) {
            int lp = i;

            int c = regexp.charAt(i);
            if(c == '(' || c == '|')
                ops.push(i);
            else if (c == ')') {
                int or = ops.pop();

                if(regexp.charAt(or) == '|') {
                    lp = ops.pop();
                    graph.addEdge(or, i);
                    graph.addEdge(lp, or+1);
                } else if (regexp.charAt(or) == '(') {
                    lp = or;
                } else {
                    throw new IllegalArgumentException("Invalid regular expression");
                }
            }

            if(i < m-1 && regexp.charAt(i + 1) == '*') {
                graph.addEdge(lp, i + 1);
                graph.addEdge(i + 1, lp);
            }

            if(c == '(' || c == '*' || c == ')')
                graph.addEdge(i, i+1);
        }

        if(ops.size() != 0)
            throw new IllegalArgumentException("Invalid regular expression");

        // todo: 普通字符的边为什么没有设置?例: ab的路径 a->b(a,b需要替换成下标)
    }

    // todo:还是明白该方法的流程
    public boolean recognizes(String txt){
        DirectedDFS dfs  = new DirectedDFS(graph, 0);
        Bag<Integer> pc = new Bag<>();
        for(int v = 0; v < graph.V(); v++)
            if(dfs.marked(0)) pc.add(v);

        for(int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '*' || txt.charAt(i) == '|' || txt.charAt(i) == '(' || txt.charAt(i) == ')')
                throw new IllegalArgumentException("text contains the metacharacter '" + txt.charAt(i) + "'");

            Bag<Integer> match = new Bag<>();
            for(int v : pc) {
                if(v == m) continue;
                if((regexp.charAt(v) == txt.charAt(i)) || regexp.charAt(v) == '.')
                    match.add(v + 1);
            }

            dfs = new DirectedDFS(graph, match);
            pc = new Bag<>();
            for(int v = 0; v < graph.V(); v++)
                if(dfs.marked(v)) pc.add(v);

            if(pc.size() == 0) return false;
        }

        for(int v : pc)
            if(v == m) return true;
        return false;
    }

    public static void main(String[] args) {
        String regexp = "(" + args[0] + ")";
        String txt = args[1];
        NFA nfa = new NFA(regexp);
        StdOut.println(nfa.recognizes(txt));
    }
}
