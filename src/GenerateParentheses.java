import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class GenerateParentheses {

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

    public void test(int n) {
        System.out.println("n=" + n);
        List<String> list = null;
        list = generateParenthesis(n);
        System.out.println("[V1]list=" + list);
        list = generateParenthesisV2(n);
        System.out.println("[V2]list=" + list);
    }

    public static void main(String[] args) {
        GenerateParentheses util = new GenerateParentheses();
        util.test(3);
        util.test(1);
    }
}
