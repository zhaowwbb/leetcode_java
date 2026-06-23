import java.util.*;

public class LeetCode38 {

    public String countAndSayV0(int n) {
        String ret = process("1", n, 1);
        return ret;
    }

    public String process(String s, int n, int index) {
        if (index == n)
            return s;
        StringBuilder sb = new StringBuilder();
        if (s.length() == 1) {
            sb.append("1");
            sb.append(s);
            return process(sb.toString(), n, index + 1);
        }
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != stack.peek()) {
                sb.append(stack.size());
                sb.append(stack.peek());
                stack.clear();
                stack.push(c);
            } else {
                stack.push(c);
            }
        }
        sb.append(stack.size());
        sb.append(stack.peek());

        return process(sb.toString(), n, index + 1);
    }

// Cache to avoid re-computing sequences for multiple test cases.
    // Constraints state 1 <= n <= 30.
    private static final String[] memo = new String[31];

    public String countAndSay(int n) {
        // Base case initialization
        if (memo[1] == null) {
            memo[1] = "1";
        }

        // Fill the cache up to n if not already computed
        for (int i = 2; i <= n; i++) {
            if (memo[i] == null) {
                memo[i] = getNextSequence(memo[i - 1]);
            }
        }

        return memo[n];
    }

    // Helper method to perform Run-Length Encoding (RLE) on a string
    private String getNextSequence(String s) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        
        int i = 0;
        while (i < length) {
            char current_char = s.charAt(i);
            int count = 0;
            
            // Count consecutive identical characters
            while (i < length && s.charAt(i) == current_char) {
                count++;
                i++;
            }
            
            // Append frequency followed by the character itself
            sb.append(count).append(current_char);
        }
        
        return sb.toString();
    }

    public String countAndSayV2(int n) {
        // StringBuilder sb = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        map.put("1", "11");
        // map.put("2", "11");
        String str = "1";
        if(n == 1)return str;
        
        String outputStr = str;
        // int len 
        for(int index = 2; index <= n; index++){
            // System.out.println("index=" + index + ", input=" + str);
            if(map.containsKey(str)){
                //already processed
                str = map.get(str);
                // System.out.println("Found in cache, index=" + index + ", result=" + str);
                outputStr = str;
                continue;
            }
            StringBuilder output = new StringBuilder();
            int i = 0;
            while(i < str.length()){
                char currentChar = str.charAt(i);
                int count = 0;
                while(i < str.length() && str.charAt(i) == currentChar){
                    count++;
                    i++;
                }
                output.append(count);
                output.append(currentChar);
            }
            outputStr = output.toString();
            // System.out.println("process " + index + ", output=" + outputStr);
            map.put(str, outputStr);
            str = outputStr;
        }

        return outputStr;
    }

    public String countAndSayV3(int n) {
        if(n == 1)return "1";
        String input = "1";
        String output = "";
        for(int index = 2; index <=n ;index++){
            int i = 0;
            StringBuilder sb = new StringBuilder();
            while(i < input.length()){
                char cur = input.charAt(i);
                int count = 0;
                while(i < input.length() && input.charAt(i) == cur){
                    count++;
                    i++;
                }
                sb.append(count).append(cur);
            }
            output = sb.toString();
            input = output;
        }

        return output;
    }

    public static void main(String[] args) {
        LeetCode38 solver = new LeetCode38();

        // Multi-case datasets
        int[] testInputs = { 1, 2, 3, 4, 5 };
        String[] expectedOutputs = { "1", "11", "21", "1211", "111221" };

        System.out.println("--- Running Count and Say Tests ---");

        // Single function call execution within the loop
        for (int i = 0; i < testInputs.length; i++) {
            int currentInput = testInputs[i];
            String expected = expectedOutputs[i];

            // Single location where the function is executed
            // String actual = solver.countAndSay(currentInput);
            // String actual = solver.countAndSayV2(currentInput);
            String actual = solver.countAndSayV3(currentInput);

            // Validation check
            if (actual.equals(expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (" + currentInput + " -> \"" + actual + "\")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: " + currentInput + " | Expected: \""
                        + expected + "\", but got: \"" + actual + "\"");
            }
        }
    }
}
