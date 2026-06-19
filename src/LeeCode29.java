public class LeeCode29 {

    public int divide(int dividend, int divisor) {
        int sign = 0;
        if (dividend > 0 && divisor < 0) {
            sign = -1;
            // divisor = 0 - divisor;
        }
        if (dividend < 0 && divisor > 0) {
            sign = -1;
            // dividend = 0 - dividend;
        }
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int result = 0;
        while (dividend > divisor) {
            result++;
            dividend -= divisor;
        }
        if (sign < 0) {
            result = 0 - result;
        }
        return result;
    }

    public int divideV2(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int quotient = 0;
        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        long ldividend = (long) Math.abs(dividend);
        long ldivisor = (long) Math.abs(divisor);
        while (ldividend >= ldivisor) {
            long tempDivisor = ldivisor;
            long multiple = 1;
            while (ldividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }
            ldividend -= tempDivisor;
            quotient += multiple;
        }
        return isNegative ? -quotient : quotient;
    }

    public int divideV3(int dividend, int divisor) {
        // Handle overflow case: -2^31 / -1 = 2^31 (which overflows)
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result
        // True if signs are different
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert to long to prevent overflow during absolute value conversion
        // Math.abs(Integer.MIN_VALUE) is still negative in 32-bit int!
        long lDividend = Math.abs((long) dividend);
        long lDivisor = Math.abs((long) divisor);

        int quotient = 0;

        // Main logic: Subtract multiples of the divisor
        while (lDividend >= lDivisor) {
            long tempDivisor = lDivisor;
            long multiple = 1;

            // Exponentially increase the divisor (using left shift)
            // as long as it doesn't exceed the remaining dividend.
            while (lDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }

            // Subtract the largest found multiple and add to quotient
            lDividend -= tempDivisor;
            quotient += (int) multiple;
        }

        return negative ? -quotient : quotient;
    }

    public static void main(String[] args) {
        LeeCode29 solver = new LeeCode29();

        // Multi-case datasets including standard and edge-case boundaries
        int[] dividends = { 10, 7, 0, 1, -2147483648, -2147483648 };
        int[] divisors = { 3, -3, 1, 1, -1, 2 };
        int[] expectedOutputs = { 3, -2, 0, 1, 2147483647, -1073741824 };

        System.out.println("--- Running Divide Two Integers Tests ---");

        // Sequential validation using a single function call point
        for (int i = 0; i < dividends.length; i++) {
            int currentDividend = dividends[i];
            int currentDivisor = divisors[i];
            int expected = expectedOutputs[i];

            // The single function call
            int actual = solver.divideV3(currentDividend, currentDivisor);

            // Validation check
            if (actual == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (" +
                        currentDividend + " / " + currentDivisor + " -> " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! " + currentDividend + " / " + currentDivisor +
                        " | Expected: " + expected + ", but got: " + actual);
            }
        }
    }
}
