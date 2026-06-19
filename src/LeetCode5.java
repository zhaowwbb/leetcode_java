import java.util.ArrayList;
import java.util.List;

public class LeetCode5 {

    public String longestPalindromeV3(String s) {
        if (null == s || s.length() == 0)
            return "";

        int left = 0, right = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int leftLen = expandFromMiddle(s, i, i);
            int rightLen = expandFromMiddle(s, i, i + 1);
            maxLen = Math.max(leftLen, rightLen);

            if (maxLen > right - left) {
                left = i - (maxLen - 1) / 2;
                right = i + (maxLen / 2);
            }

        }
        return s.substring(left, right + 1);

    }

    public int expandFromMiddle(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeV2(String s) {
        if (null == s)
            return null;
        if (s.length() == 1)
            return null;
        int i = 0, j = 0;
        String palindromeStr = "";
        for (i = 0; i < s.length(); i++) {
            for (j = i + 1; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                int left = i;
                int right = j;
                boolean isPalinDome = true;
                while (left < right) {
                    if (s.charAt(left) == s.charAt(right)) {
                        left++;
                        right--;
                    } else {
                        // not palindome
                        isPalinDome = false;
                        break;
                    }

                }
                if (isPalinDome) {
                    if (sub.length() > palindromeStr.length()) {
                        palindromeStr = sub;
                    }
                }

            }
        }
        return palindromeStr;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return "";

        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public int expandFromCenterV4(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeV4(String s) {
        int start = 0, end = 0;
        // int len = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenterV4(s, i, i);
            int len2 = expandFromCenterV4(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandFromCenterV5(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeV5(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenterV5(s, i, i);
            int len2 = expandFromCenterV5(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandFromCenterV6(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeV6(String s) {
        int left = 0;
        int right = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenterV6(s, i, i);
            int len2 = expandFromCenterV6(s, i, i + 1);
            maxLen = Math.max(len1, len2);
            if (maxLen > right - left + 1) {
                left = i - (maxLen - 1) / 2;
                right = i + maxLen / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    public int expandFromCenterV7(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeV7(String s) {
        int left = 0;
        int right = 0;
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenterV7(s, i, i);
            int len2 = expandFromCenterV7(s, i, i + 1);
            len = Math.max(len1, len2);
            if (len > right - left + 1) {
                left = i - (len - 1) / 2;
                right = i + len / 2;
            }

        }
        return s.substring(left, right + 1);
    }

    public int expandFromCenterV8(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public String longestPalindromeV8(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenterV8(s, i, i);
            int len2 = expandFromCenterV8(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > right - left + 1) {
                left = i - (len - 1) / 2;
                right = i + len / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    public int expandFromCenterV9(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeV9(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenterV9(s, i, i);
            int len2 = expandFromCenterV9(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > right - left + 1) {
                left = i - (len - 1) / 2;
                right = i + len / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    static class PalindromeTestCase {
        String description;
        String input;
        List<String> validExpectedOutputs; // Multiple options allowed if there are ties

        public PalindromeTestCase(String description, String input, List<String> validExpectedOutputs) {
            this.description = description;
            this.input = input;
            this.validExpectedOutputs = validExpectedOutputs;
        }
    }

    public static void main(String[] args) {
        LeetCode5 solver = new LeetCode5();
        System.out.println(
                "Running Longest Palindromic Substring Test Suite...\n------------------------------------------");

        // 1. Build the collection of test inputs and multiple valid expected strings
        List<PalindromeTestCase> testCases = new ArrayList<>();

        // Example 1: "babad" -> "bab" or "aba" are both completely valid answers
        testCases.add(new PalindromeTestCase(
                "Standard mixing string with alternative symmetric ties",
                "babad",
                List.of("bab", "aba")));

        // Example 2: "cbbd" -> "bb"
        testCases.add(new PalindromeTestCase(
                "Even-length palindrome substring",
                "cbbd",
                List.of("bb")));

        // Edge Case: Single character
        testCases.add(new PalindromeTestCase(
                "Single character boundary string",
                "a",
                List.of("a")));

        // Edge Case: Entire string is a palindrome
        testCases.add(new PalindromeTestCase(
                "Full string is already a valid palindrome",
                "racecar",
                List.of("racecar")));

        // Edge Case: All identical characters
        testCases.add(new PalindromeTestCase(
                "Continuous repeating sequence",
                "ccccc",
                List.of("ccccc")));

        // 2. Iterate and execute validation checks exactly once per test scenario
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            PalindromeTestCase tc = testCases.get(i);

            // Core algorithm invocation point
            // String actual = solver.longestPalindrome(tc.input);
            String actual = solver.longestPalindromeV9(tc.input);

            // Verify if the actual output matches any of our valid expected outputs
            if (tc.validExpectedOutputs.contains(actual)) {
                System.out.println(String.format("[PASS] Test %d: %s", i + 1, tc.description));
                passed++;
            } else {
                System.err.println(String.format("[FAIL] Test %d: %s", i + 1, tc.description));
                System.err.println("       Input:    \"" + tc.input + "\"");
                System.err.println("       Expected (one of): " + tc.validExpectedOutputs);
                System.err.println("       Actual:            \"" + actual + "\"");
            }
        }

        System.out.println("------------------------------------------");
        System.out.println(String.format("Execution Result: %d/%d Tests Passed.", passed, testCases.size()));
    }
}
