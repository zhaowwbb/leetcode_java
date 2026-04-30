
import java.util.*;

public class ValidParentheses {

    public boolean isValidV2(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                char top = stack.peek();
                if (top == '(' && c != ')')
                    return false;
                if (top == '{' && c != '}')
                    return false;
                if (top == '[' && c != ']')
                    return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid(String s) {
        if (null == s)
            return false;
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                // if (stack.isEmpty()) {
                // stack.push(c);
                // } else {
                // stack.push(c);
                // }
            }
            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    // invalid
                    return false;
                } else {
                    char top = stack.peek();
                    if ('(' == top && c == ')') {
                        stack.pop();
                    }
                    if ('{' == top && c == '}') {
                        stack.pop();
                    }
                    if ('[' == top && c == ']') {
                        stack.pop();
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValidV3(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (c == ')') {
                    if (!stack.empty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (c == '}') {
                    if (!stack.empty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (c == ']') {
                    if (!stack.empty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValidV4(String s) {
        if(null == s || s.length() % 2 != 0)return false;
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}','{'); 
        for(char c : s.toCharArray()){
            if(map.containsKey(c)){
                if(!stack.isEmpty()){
                    char top = stack.pop();
                    if(top != map.get(c)){
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public void test(String s, boolean expectedResult) {
        boolean result = isValid(s);
        System.out.printf("[V1] s=[%s], expected:[%b], actual:[%b] %n", s, expectedResult, result);
        result = isValidV2(s);
        System.out.printf("[V2] s=[%s], expected:[%b], actual:[%b] %n", s, expectedResult, result);
        result = isValidV3(s);
        System.out.printf("[V3] s=[%s], expected:[%b], actual:[%b] %n", s, expectedResult, result);
        result = isValidV4(s);
        System.out.printf("[V4] s=[%s], expected:[%b], actual:[%b] %n", s, expectedResult, result);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        ValidParentheses util = new ValidParentheses();
        util.test("()", true);
        util.test("()[]{}", true);
        util.test("(]", false);
        util.test("([])", true);
        util.test("([)]", false);
    }
}
