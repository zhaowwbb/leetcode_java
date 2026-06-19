
import java.util.*;

public class LeetCode20 {

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
        if (null == s || s.length() % 2 != 0)
            return false;
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (!stack.isEmpty()) {
                    char top = stack.pop();
                    if (top != map.get(c)) {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public boolean isValidV5(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.size() == 0)
                    return false;
                if ((c == ')' && stack.peek() == '(') || (c == ']' && stack.peek() == '[')
                        || (c == '}' && stack.peek() == '{')) {
                    stack.pop();
                    continue;
                } else {
                    return false;
                }
            }
        }
        // System.out.println("stack.size()=" + stack.size());
        return stack.size() == 0;
    }

    public boolean isValidV6(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                if (!stack.isEmpty()) {
                    if (stack.peek() == map.get(c)) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        LeetCode20 solver = new LeetCode20();

        // Multi-case datasets
        String[] testInputs = {
                "()",
                "()[]{}",
                "(]",
                "([)]",
                "{[]}",
                "["
        };

        boolean[] expectedOutputs = { true, true, false, false, true, false };

        System.out.println("--- Running Valid Parentheses Tests ---");

        // Sequential validation using a single function call point
        for (int i = 0; i < testInputs.length; i++) {
            String currentInput = testInputs[i];
            boolean expected = expectedOutputs[i];

            // The single function call
            boolean actual = solver.isValidV6(currentInput);

            // Validation check
            if (actual == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (\"" + currentInput + "\" -> " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: \"" + currentInput + "\" | Expected: "
                        + expected + ", but got: " + actual);
            }
        }
    }
}
