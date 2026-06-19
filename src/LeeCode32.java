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
                    // unmatched, create new boundary
                    stack.push(i);
                } else {
                    int len = i - stack.peek();
                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LeeCode32 solver = new LeeCode32();

        // Multi-case datasets
        String[] testInputs = {
                "(()",
                ")()())",
                "",
                "()()",
                "(()())"
        };

        int[] expectedOutputs = { 2, 4, 0, 4, 6 };

        System.out.println("--- Running Longest Valid Parentheses Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            String currentInput = testInputs[i];
            int expected = expectedOutputs[i];

            // The single function call
            int actual = solver.longestValidParenthesesV2(currentInput);

            // Validation check
            if (actual == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (\"" + currentInput + "\" -> " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: \"" + currentInput +
                        "\" | Expected: " + expected + ", but got: " + actual);
            }
        }
    }
}
