import java.util.ArrayList;
import java.util.List;

public class LeetCode9 {

    public boolean isPalindromeV2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int reverse = 0;
        while (reverse < x) {
            reverse = reverse * 10 + (x % 10);
            x /= 10;
        }
        if (x == reverse || x == (reverse / 10))
            return true;

        return false;
    }

    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x < 10)
            return true;
        String str = String.valueOf(x);
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public boolean isPalindromeV3(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int rev = 0;
        while (x > rev) {
            int digit = x % 10;
            rev = rev * 10 + digit;
            x /= 10;
        }
        if (rev == x || x == rev / 10)
            return true;
        return false;
    }

    public boolean isPalindromeV4(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int rev = 0;
        while (rev < x) {
            int digit = x % 10;
            rev = rev * 10 + digit;
            x /= 10;
        }
        return (x == rev || x == rev / 10);
    }

    public boolean isPalindromeV5(int x) {
        if (x < 0)
            return false;
        String s = String.valueOf(x);
        int left = 0;
        int right = s.length() - 1;
        while (left <= right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        if (left > right) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPalindromeV6(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int reverseHalf = 0;
        while (x > reverseHalf) {
            int pop = x % 10;
            reverseHalf = reverseHalf * 10 + pop;
            x /= 10;
        }
        return x == reverseHalf || reverseHalf / 10 == x;
    }

    static class PalindromeTestCase {
        String description;
        int input;
        boolean expected;

        public PalindromeTestCase(String description, int input, boolean expected) {
            this.description = description;
            this.input = input;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        LeetCode9 solver = new LeetCode9();
        System.out.println("Running Palindrome Number Test Suite...\n------------------------------------------");

        // 1. Collate test layouts tracking specific mathematical constraints
        List<PalindromeTestCase> testCases = new ArrayList<>();

        // Example 1: Standard odd-length palindrome
        testCases.add(new PalindromeTestCase(
                "Standard odd-length symmetric number",
                121,
                true));

        // Example 2: Negative numbers
        testCases.add(new PalindromeTestCase(
                "Negative number with leading minus sign symbol boundary",
                -121,
                false));

        // Example 3: Non-palindrome with trailing zero reflection conflict
        testCases.add(new PalindromeTestCase(
                "Positive number with trailing zero sequence",
                10,
                false));

        // Edge Case: Single-digit number
        testCases.add(new PalindromeTestCase(
                "Single-digit integer baseline asset",
                7,
                true));

        // Edge Case: Standard even-length palindrome
        testCases.add(new PalindromeTestCase(
                "Standard even-length symmetric number",
                1221,
                true));

        // Edge Case: Absolute zero
        testCases.add(new PalindromeTestCase(
                "Zero value baseline",
                0,
                true));

        // 2. Iterate and process assertions exactly once per defined setup block
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            PalindromeTestCase tc = testCases.get(i);

            // Core execution point
            // boolean actual = solver.isPalindrome(tc.input);
            boolean actual = solver.isPalindromeV6(tc.input);

            if (actual == tc.expected) {
                System.out.println(String.format("[PASS] Test %d: %s", i + 1, tc.description));
                passed++;
            } else {
                System.err.println(String.format("[FAIL] Test %d: %s", i + 1, tc.description));
                System.err.println("       Input:    " + tc.input);
                System.err.println("       Expected: " + tc.expected);
                System.err.println("       Actual:   " + actual);
            }
        }

        System.out.println("------------------------------------------");
        System.out.println(String.format("Execution Result: %d/%d Tests Passed.", passed, testCases.size()));
    }
}
