package _1_Fundamentals._1_2_DataAbstraction;


import edu.princeton.cs.algs4.StdOut;

/**
 * exercise 1.2.6
 */
public class CircularRotation {
    /**
     * 判断是否为回环变位
     * 明显答案更好:
     *  Solution: (s.length() == t.length()) && (s.concat(s).indexOf(t) >= 0)
     * @param s s
     * @param t t
     * @return true false
     */
    public static boolean is(String s, String t) {
        if (s == null || "".equals(s) || t == null || "".equals(t)) return false;
        if (s.length() != t.length()) return false;
        String key = s.substring(0, 1);
        int a = t.indexOf(key);
        if (a == -1 ) return false;
        String t1 = t.substring(a, t.length()) + t.substring(0, a);
        if (!t1.equals(s)) return false;
        return true;
    }

    public static void main(String[] args){
        String t = args[0];
        String s = args[1];
        StdOut.println(is(s, t));
        StdOut.println((s.length() == t.length()) && (s.concat(s).indexOf(t) >= 0));
    }
}
