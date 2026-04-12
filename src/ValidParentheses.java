
import java.util.Stack;

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

    public void test(String s, boolean expectedResult) {
        boolean result = isValid(s);
        System.out.printf("s=[%s], expected:[%b], actual:[%b] %n", s, expectedResult, result);
        result = isValidV2(s);
        System.out.printf("s=[%s], expected:[%b], actual:[%b] %n", s, expectedResult, result);

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
