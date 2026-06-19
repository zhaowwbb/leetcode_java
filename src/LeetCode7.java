import java.util.ArrayList;
import java.util.List;

public class LeetCode7 {

    public int reverseV2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }

        return rev;
    }

    public int reverse(int x) {
        StringBuilder sb = new StringBuilder();
        // int i = x;
        boolean isNegative = false;
        int tmp = x;
        if (x < 0) {
            isNegative = true;
            tmp = 0 - x;
        }
        while (true) {
            if (tmp < 10) {
                sb.append(tmp);
                break;
            } else {
                sb.append(tmp % 10);
                tmp = tmp / 10;
            }
        }
        // System.out.println("result =" + sb.toString());
        // String s = String.valueOf(x);

        try {
            Integer resulInteger = Integer.valueOf(sb.toString());
            if (isNegative) {
                return 0 - resulInteger.intValue();
            } else {
                return resulInteger.intValue();
            }
        } catch (Exception e) {
            return 0;
        }

    }

    public int reverseV3(int x) {
        int reverse = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (reverse > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (reverse < Integer.MIN_VALUE / 10 || (reverse == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            reverse = reverse * 10 + pop;

        }
        return reverse;
    }

    public int reverseV4(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // Check for overflow before multiplying by 10
            // Max value ends in 7, Min value ends in 8
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop;
        }
        return rev;
    }

    public int reverseV5(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public int reverseV6(int x) {
        int number = 0;
        while (x != 0) {
            int digit = x % 10;
            if (number > Integer.MAX_VALUE / 10 || (number == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (number < Integer.MIN_VALUE / 10 || (number == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }
            number = number * 10 + digit;
            x = x / 10;
        }
        return number;
    }

    public int reverseV7(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10 || ((result == Integer.MIN_VALUE / 10) && digit < -8)) {
                return 0;
            }

            result = result * 10 + digit;
            x /= 10;
        }
        return result;
    }

    static class ReverseTestCase {
        String description;
        int input;
        int expected;

        public ReverseTestCase(String description, int input, int expected) {
            this.description = description;
            this.input = input;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        LeetCode7 solver = new LeetCode7();
        System.out.println("Running Reverse Integer Test Suite...\n------------------------------------------");

        // 1. Accumulate test configurations mapping inputs to expected reversed values
        List<ReverseTestCase> testCases = new ArrayList<>();

        // Example 1: Standard positive integer
        testCases.add(new ReverseTestCase(
                "Standard positive integer conversion",
                123,
                321));

        // Example 2: Negative integer
        testCases.add(new ReverseTestCase(
                "Negative integer maintaining sign parity",
                -123,
                -321));

        // Example 3: Trailing zeros
        testCases.add(new ReverseTestCase(
                "Integer with trailing zeros dropping leading digits",
                120,
                21));

        // Edge Case: Absolute zero boundary
        testCases.add(new ReverseTestCase(
                "Zero value input alignment",
                0,
                0));

        // Edge Case: Overflow positive boundary (1,534,236,469 reversed overflows
        // 32-bit)
        testCases.add(new ReverseTestCase(
                "Positive reversal triggering overflow boundary protection",
                1534236469,
                0));

        // Edge Case: Overflow negative boundary (-2,147,483,648 or values reversing
        // near it)
        testCases.add(new ReverseTestCase(
                "Negative reversal triggering overflow boundary protection",
                -2147483648,
                0));

        // 2. Iterate and verify test structures, invoking core logic exactly once per
        // scenario
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            ReverseTestCase tc = testCases.get(i);

            // Core execution invocation point
            int actual = solver.reverseV7(tc.input);

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
