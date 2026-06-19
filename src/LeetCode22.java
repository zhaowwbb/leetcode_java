import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class LeetCode22 {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0)
            return result;
        if (n == 1) {
            result.add("()");
        } else {
            backtrack(result, "", 0, 0, n);
        }

        return result;
    }

    private void backtrack(List<String> result, String str, int open, int close, int max) {
        if (str.length() == 2 * max) {
            result.add(str);
            return;
        }
        if (open < max) {
            backtrack(result, str + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(result, str + ")", open, close + 1, max);
        }
    }

    public List<String> generateParenthesisV2(int n) {
        List<String> result = new ArrayList<>();
        backtraceV2(result, "", 0, 0, n);
        return result;
    }

    public void backtraceV2(List<String> result, String str, int open, int close, int max) {
        if (str.length() == 2 * max) {
            result.add(str);
            return;
        }
        if (open < max) {
            backtraceV2(result, str + "(", open + 1, close, max);
        }
        if (close < open) {
            backtraceV2(result, str + ")", open, close + 1, max);
        }
    }

    public List<String> generateParenthesisV3(int n) {
        List<String> result = new ArrayList<>();
        backtraceV3(result, "", 0, 0, n);
        return result;
    }

    public void backtraceV3(List<String> result, String str, int leftNum, int rightNum, int n) {
        if (str.length() == n * 2) {
            result.add(str);
            return;
        }
        if (leftNum < n) {
            // sb.append("(");
            backtraceV3(result, str + "(", leftNum + 1, rightNum, n);
        }
        if (leftNum > rightNum) {
            // sb.append(")");
            backtraceV3(result, str + ")", leftNum, rightNum + 1, n);
        }
    }

    public List<String> generateParenthesisV4(int n) {
        List<String> result = new ArrayList<>();
        backtraceV4(result, "", 0, 0, n);
        return result;
    }

    public void backtraceV4(List<String> result, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            result.add(str);
            return;
        }
        if (open < max) {
            backtraceV4(result, str + "(", open + 1, close, max);
        }
        if (close < open) {
            backtraceV4(result, str + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        LeetCode22 solver = new LeetCode22();

        // Multi-case datasets for 'n' pairs
        int[] testInputs = { 3, 1 };

        // Master list containing expected lists of combinations for each test case
        List<List<String>> expectedOutputs = new ArrayList<>();
        expectedOutputs.add(Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
        expectedOutputs.add(Arrays.asList("()"));

        System.out.println("--- Running Generate Parentheses Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            int n = testInputs[i];
            List<String> expected = expectedOutputs.get(i);

            // The single function call
            List<String> actual = solver.generateParenthesisV4(n);

            // Sort copies of both lists to make the validation check order-insensitive
            List<String> sortedActual = new ArrayList<>(actual);
            List<String> sortedExpected = new ArrayList<>(expected);
            Collections.sort(sortedActual);
            Collections.sort(sortedExpected);

            // Validation check
            if (sortedActual.equals(sortedExpected)) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (n = " + n + " -> " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! n = " + n +
                        "\n  Expected: " + expected + "\n  Got:      " + actual);
            }
        }
    }
}
