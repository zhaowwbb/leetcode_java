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

    public void test(int x, int expected) {
        System.out.println("input=" + x);
        int result = 0;
        result = reverse(x);
        System.out.printf("[V1] Expected:[%d], actual:[%d]%n", expected, result);
        // result = reverseV2(x);
        // System.out.printf("[V2] Expected:[%d], actual:[%d]%n", expected, result);
        // result = reverseV3(x);
        // System.out.printf("[V3] Expected:[%d], actual:[%d]%n", expected, result);
        // result = reverseV4(x);
        // System.out.printf("[V4] Expected:[%d], actual:[%d]%n", expected, result);
        // result = reverseV5(x);
        // System.out.printf("[V5] Expected:[%d], actual:[%d]%n", expected, result);
        result = reverseV6(x);
        System.out.printf("[V6] Expected:[%d], actual:[%d]%n", expected, result);
        result = reverseV7(x);
        System.out.printf("[V7] Expected:[%d], actual:[%d]%n", expected, result);
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        System.out.println("Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        LeetCode7 util = new LeetCode7();

        int x = 0;
        x = 123;
        util.test(x, 321);
        x = -123;
        util.test(x, -321);
        x = 120;
        util.test(x, 21);
        x = 1534236469;
        util.test(x, 0);
    }
}
