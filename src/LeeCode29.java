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

    public void test(int dividend, int divisor, int expected) {
        System.out.printf("Input dividend:[%d]  divisor:[%d] %n", dividend, divisor);
        int result = divide(dividend, divisor);
        System.out.printf("[V1] expected:[%d], actual:[%d] %n", expected, result);

        result = divideV2(dividend, divisor);
        System.out.printf("[V2] expected:[%d], actual:[%d] %n", expected, result);

        result = divideV3(dividend, divisor);
        System.out.printf("[V3] expected:[%d], actual:[%d] %n", expected, result);
        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        LeeCode29 lc = new LeeCode29();
        lc.test(10, 3, 3);
        lc.test(7, -3, -2);
        lc.test(-2147483648, 1, -2147483648);
        lc.test(-2147483648, -1, 2147483647);
        lc.test(2147483647, 1, 2147483647);
        lc.test(2147483647, -1, -2147483647);
    }
}
