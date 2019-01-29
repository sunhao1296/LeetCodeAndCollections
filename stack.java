import java.util.Stack;

public class stack {
    /*
    leetcode 739
     */
    //最邻近的大值/小值考虑使用栈
    public int[] dailyTemperatures(int[] T) {
        int[] stack = new int[T.length+1];
        int[] ret = new int[T.length];
        int top = -1;
        for(int i=0;i<T.length;i++){
            while(top>-1 && T[i] > T[stack[top]]){
                int idx = stack[top--];
                ret[idx] = i-idx;
            }
            stack[++top] = i;
        }
        return ret;
    }
    /*
    leetcode 394
    与实现计算器相似，这种问题必用栈
     */
    public String decodeString(String s){
        String res = "";
        int curNum = 0;
        Stack<Integer> countStack = new Stack<Integer>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while(idx<s.length()){
            Character ch = s.charAt(idx);
            if(Character.isDigit(ch)){
                while(Character.isDigit(s.charAt(idx))){
                    curNum = 10*curNum + (s.charAt(idx++) - '0');
                }
            }else if(s.charAt(idx) == '['){
                countStack.push(curNum);
                curNum = 0;
                resStack.push(res);
                res = "";
                idx++;
            }else if(s.charAt(idx) == ']'){
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeat = countStack.pop();
                for(int i=0;i<repeat;i++){
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }else{
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}

