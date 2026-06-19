import java.util.Arrays;

public class LeetCode14 {

    public String longestCommonPrefixV2(String[] strs) {
        if (null == strs || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int pos = 0;
        while (pos < first.length() && pos < last.length()) {
            if (first.charAt(pos) != last.charAt(pos)) {
                break;
            } else {
                pos++;
            }
        }
        return first.substring(0, pos);
    }

    public String longestCommonPrefix(String[] strs) {
        if (null == strs)
            return "";
        if (1 == strs.length)
            return strs[0];
        StringBuilder sb = new StringBuilder();
        String str0 = strs[0];
        // int pos = 0;
        boolean isContinue = true;
        for (int i = 0; i < str0.length() && isContinue; i++) {
            char c = str0.charAt(i);
            for (int j = 1; j < strs.length && isContinue; j++) {
                String s = strs[j];
                if (i >= s.length()) {
                    return sb.toString();
                    // break;
                }
                if (s.charAt(i) != c) {
                    return sb.toString();
                    // break;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public String longestCommonPrefixV3(String[] strs) {
        String commonPrefix = "";
        if (null == strs || strs.length == 0)
            return commonPrefix;
        Arrays.sort(strs);
        int left = 0;
        int right = strs.length - 1;
        while (left < right) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            String s1 = strs[left];
            String s2 = strs[right];
            while (index < s1.length() && index < s2.length() && s1.charAt(index) == s2.charAt(index)) {
                sb.append(s1.charAt(index));
                index++;
            }
            if (sb.length() > commonPrefix.length()) {
                commonPrefix = sb.toString();
            }
            left++;
            right--;
        }

        return commonPrefix;
    }

    public String longestCommonPrefixV4(String[] strs) {
        if (null == strs || strs.length == 0)
            return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) < 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            if (prefix.isEmpty())
                return "";
        }
        return prefix;

    }

    public String longestCommonPrefixV5(String[] strs) {
        int commonPos = Integer.MAX_VALUE;
        // String preStr = "";

        String prefix = strs[0];

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != c) {
                    return prefix.substring(0, i);
                }
            }
        }

        return prefix;
    }

    public String longestCommonPrefixV6(String[] strs) {
        String result = "";
        if (null == strs)
            return result;
        if (strs.length == 1)
            return strs[0];
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                String s = strs[j];
                if (i == s.length() || c != s.charAt(i)) {
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }

    public static void main(String[] args) {
        LeetCode14 solver = new LeetCode14();

        // 2D Array to hold multiple test cases (each sub-array is a case)
        String[][] testInputs = {
                { "flower", "flow", "flight" },
                { "dog", "racecar", "car" },
                { "interspecies", "interstellar", "interstate" },
                { "throne", "throne" },
                { "" }
        };

        String[] expectedOutputs = { "fl", "", "inters", "throne", "" };

        System.out.println("--- Running Longest Common Prefix Tests ---");

        // Execute and evaluate every case using a single function call point
        for (int i = 0; i < testInputs.length; i++) {
            String[] currentInput = testInputs[i];
            String expected = expectedOutputs[i];

            // The single function call
            String actual = solver.longestCommonPrefixV6(currentInput);

            // Print format wrapper for array visualization
            String inputDisplay = "[" + String.join(", ", currentInput) + "]";

            // Validation check
            if (actual.equals(expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (" + inputDisplay + " -> \"" + actual + "\")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: " + inputDisplay + " | Expected: \""
                        + expected + "\", but got: \"" + actual + "\"");
            }
        }
    }
}
