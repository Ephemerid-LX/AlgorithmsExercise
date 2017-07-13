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
