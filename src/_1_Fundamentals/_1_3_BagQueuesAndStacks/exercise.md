### 1.3.1
```java
public boolean isFull(){
    return n == s.length;
}
```

### 1.3.2
was best times of the was the it (1 left on stack)


### 1.3.3
+ b    
  此时0不能在1前面输出
+ f  
  1不能在7前面输出
+ g  
  0不能在2前面输出

### 1.3.4
可以参考`Dijikstra 的双栈算数表达式求值算法`来实现。
``` java
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
```
> Answer: (b), (f), and (g).

### 1.3.5
110010  
将十进制转换为二进制

> Answer: Prints the binary representation of N (110010 when n is 50).

### 1.3.6
倒序

### 1.3.7
```java
public Item peek (){
    return first.item;
}
```

### 1.3.10
```java
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;
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
```

### 1.3.11
```java
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
```

### 1.3.13
b,c,d


### 1.3.14
```java
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * exercise 1.3.14
 * 用数组实现Queue
 * 需要理解当数组在length-1的位置存值后，
 * 数组不一样被占满，因为数组可能从开始处被置为null
 */
public class ResizingArrayQueue<Item> implements Iterable<Item>{
    private Item[] q;
    private int n;
    private int first;
    private int last;

    //初始化
    public ResizingArrayQueue(){
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    // 入列
    public void enqueue(Item item){
        if (n == q.length) resize(2*q.length);
        q[last++] = item;
        n++;
        // 当last == q.length，且数组并没有满时，重头开始[0,first-1]
        if (last == q.length) last = 0;
    }

    //出列
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;
        n--;
        first++;
        // 当first == q.length，且数组并没有满时，重头开始[0,last]
        if (first == q.length) first = 0;
        if (n > 0 && n < q.length/4) resize(q.length/2);
        return item;
    }

    /**
     * 重置数组大小
     * @param capacity 数组大小
     */
    public void resize(int capacity){
        //assert capacity >= n;这里不知道为什么需要这个断言
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            /*
            first + i < q.length ----> [first,q.length-1]
            first + i >= q.length ---->[0,first)
             */
            temp[i] = q[(first+i)%q.length];
        q = temp;
        first = 0;
        last = n;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class ArrayIterator implements Iterator<Item>{
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (isEmpty()) throw new NoSuchElementException();
            return q[(first + i++) % q.length];
        }
    }

    public static void main(String[] args){
        ResizingArrayQueue<String> q = new ResizingArrayQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (!s.equals("-")) q.enqueue(s);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
```

## Creative Problems
### 1.3.38
```java
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Creative Problems
 * exercise 1.3.37
 * 人编号队列,依次存入临时队列,遇到 M 的倍数,进入输入队列;循环;
 * 最后剩余次数带进下次循环中
 * 直到人编号队列size为1
 */
public class Josephus {
    public static Queue<Integer> survive(int n, int m){
        Queue<Integer> queue = new Queue<>();
        Queue<Integer> temp = new Queue<>();
        Queue<Integer> result = new Queue<>();
        int remainder = 0;
        int length;
        for (int i = 0; i < n; i++){
            queue.enqueue(i);
        }

        while (queue.size() > 1){
            length = queue.size();
            for (int i = 0; i < length; i++) {
                if ((remainder + i + 1 ) % m == 0)
                    result.enqueue(queue.dequeue());
                else
                    temp.enqueue(queue.dequeue());
            }
            remainder = m - (remainder + length) % m;
            queue = temp;
        }
        result.enqueue(queue.dequeue());
        return result;
    }

    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StdOut.println(survive(n,m));
    }
}
```

### 1.3.42
`todo`
