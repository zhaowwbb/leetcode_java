import java.util.*;

public class LeeCode32 {

    char LEFT = '(';
    char RIGHT = ')';

    public int longestValidParentheses(String s) {
        int maxLen = 0;
        if (null == s)
            return maxLen;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    // invalid, use current char as boundary
                    stack.push(i);
                } else {
                    int len = i - stack.peek();
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        return maxLen;
    }

    public int longestValidParenthesesV2(String s) {
        int maxLen = 0;
        if(null == s)return maxLen;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    //unmatched, create new boundary
                    stack.push(i);
                }else{
                    int len = i - stack.peek();
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        return maxLen;
    }

    public void test(String s, int expected) {
        int actual = longestValidParentheses(s);
        System.out.printf("[V1] s=%s, expect=%d, actual=%d%n", s, expected, actual);

        actual = longestValidParenthesesV2(s);
        System.out.printf("[V2] s=%s, expect=%d, actual=%d%n", s, expected, actual);
    }

    public static void main(String[] args) {
        LeeCode32 lc = new LeeCode32();
        lc.test("(()", 2);
        lc.test(")()())", 4);
        lc.test("", 0);
    }
}
