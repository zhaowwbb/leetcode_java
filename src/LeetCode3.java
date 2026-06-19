import java.util.*;

public class LeetCode3 {

    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int maxLength = 0;
        java.util.Set<Character> charSet = new java.util.HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charSet.contains(c)) {
                maxLength = Math.max(charSet.size(), maxLength);
                charSet.clear();
                charSet.add(c);
            } else {
                charSet.add(c);
            }
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringV2(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int maxLength = 0;
        java.util.Map<Character, Integer> charIndexMap = new java.util.HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charIndexMap.containsKey(c)) {
                left = Math.max(charIndexMap.get(c) + 1, left);

            }
            charIndexMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringV3(String s) {
        int maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charIndexMap.containsKey(c)) {
                left = Math.max(charIndexMap.get(c) + 1, left);
            }
            charIndexMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);

        }
        return maxLength;
    }

    public int lengthOfLongestSubstringV4(String s) {
        int maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charIndexMap.containsKey(c)) {
                left = Math.max(left, charIndexMap.get(c) + 1);
            }
            charIndexMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public int lengthOfLongestSubstringV5(String s) {
        if (null == s)
            return 0;
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {

            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (!set.add(c)) {
                    maxLen = Math.max(maxLen, set.size());
                    set.clear();
                    // System.out.print("")
                    break;
                }
            }
        }
        maxLen = Math.max(maxLen, set.size());

        return maxLen;
    }

    public int lengthOfLongestSubstringV6(String s) {
        if (null == s)
            return 0;
        Map<Character, Integer> charToIndex = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charToIndex.containsKey(c)) {
                left = Math.max(left, charToIndex.get(c) + 1);
            }
            charToIndex.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public int lengthOfLongestSubstringV7(String s) {
        int maxLen = 0;
        if (null == s)
            return maxLen;
        int left = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charToIndex.containsKey(c)) {
                left = Math.max(left, charToIndex.get(c) + 1);
            }
            charToIndex.put(c, right);
            int len = right - left + 1;
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }

    public int lengthOfLongestSubstringV8(String s) {
        int maxLen = 0;
        if (null == s || s.length() == 0)
            return maxLen;
        int left = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char curChar = s.charAt(right);
            if (charToIndex.containsKey(curChar)) {
                left = Math.max(left, charToIndex.get(curChar) + 1);
            }
            charToIndex.put(curChar, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    static class SubstringTestCase {
        String description;
        String input;
        int expected;

        public SubstringTestCase(String description, String input, int expected) {
            this.description = description;
            this.input = input;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        LeetCode3 solver = new LeetCode3();
        System.out.println("Running Longest Substring Test Suite...\n------------------------------------------");

        // 1. Define the collection of test inputs and expected outputs
        List<SubstringTestCase> testCases = new ArrayList<>();

        // Example 1: "abcabcbb" -> "abc" (3)
        testCases.add(new SubstringTestCase(
                "Standard mixing string with recurring characters",
                "abcabcbb",
                3));

        // Example 2: "bbbbb" -> "b" (1)
        testCases.add(new SubstringTestCase(
                "All identical characters",
                "bbbbb",
                1));

        // Example 3: "pwwkew" -> "wke" (3)
        testCases.add(new SubstringTestCase(
                "Substring in the middle with an overlapping repetition",
                "pwwkew",
                3));

        // Edge Case: Empty string
        testCases.add(new SubstringTestCase(
                "Empty string input boundary",
                "",
                0));

        // Edge Case: Absolute unique string
        testCases.add(new SubstringTestCase(
                "Completely unique characters",
                "abcdefg",
                7));

        // Edge Case: Structural trap like "tmmzuxt" where left shouldn't move backward
        testCases.add(new SubstringTestCase(
                "String requiring left pointer stability control",
                "tmmzuxt",
                5));

        // 2. Run validations, invoking the method exactly once per scenario layout
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            SubstringTestCase tc = testCases.get(i);

            // Core invocation point
            int actual = solver.lengthOfLongestSubstringV8(tc.input);

            if (actual == tc.expected) {
                System.out.println(String.format("[PASS] Test %d: %s", i + 1, tc.description));
                passed++;
            } else {
                System.err.println(String.format("[FAIL] Test %d: %s", i + 1, tc.description));
                System.err.println("       Input:    \"" + tc.input + "\"");
                System.err.println("       Expected: " + tc.expected);
                System.err.println("       Actual:   " + actual);
            }
        }

        System.out.println("------------------------------------------");
        System.out.println(String.format("Execution Result: %d/%d Tests Passed.", passed, testCases.size()));
    }
}
