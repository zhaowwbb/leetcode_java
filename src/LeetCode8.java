public class LeetCode8 {

    public int myAtoiV2(String s) {
        if (null == s || s.length() == 0)
            return 0;
        int i = 0;
        int n = s.length();
        // skip space
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        // all space
        if (i == n)
            return 0;

        int sign = 1;

        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            if (s.charAt(i) == '-')
                sign = -1;
            i++;
        }
        // if (s.charAt(i) == '+') {
        // sign = 1;
        // } else if (s.charAt(i) == '-') {
        // sign = -1;
        // }

        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            // if (Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            // System.out.print("[" + digit + "]");
            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            i++;
            // } else {
            // break;
            // }
        }
        // System.out.println("");
        return result * sign;
    }

    public int myAtoi(String s) {
        if (null == s)
            return 0;
        boolean isNegative = false;
        boolean isSignPresent = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c.equals('+')) {
                if (isSignPresent) {
                    break;
                } else {
                    isSignPresent = true;
                    isNegative = false;
                }
            } else if (c.equals('-')) {
                if (isSignPresent) {
                    break;
                } else {
                    isSignPresent = true;
                    isNegative = true;
                }
            } else if (c.equals(' ')) {
                // invalid number with space
                if (sb.length() > 0) {
                    break;
                } else {
                    // skip
                }
            } else if (Character.isDigit(c)) {
                if (!isSignPresent) {
                    isSignPresent = true;
                }
                if (c.equals('0')) {
                    if (sb.length() > 0) {
                        sb.append(c);
                    } else {
                        // skip 0 in the beginning
                    }
                } else {
                    sb.append(c);
                }
            } else {
                // no digit
                break;
            }
        }

        int maxLen = String.valueOf(Integer.MAX_VALUE).length();
        // System.out.println("number=" + sb.toString() + ",maxLen=" + maxLen);
        if (sb.length() < maxLen) {
            if (sb.length() == 0)
                return 0;
            if (isNegative) {
                return 0 - Integer.valueOf(sb.toString());
            } else {
                return Integer.valueOf(sb.toString());
            }
        } else if (sb.length() > maxLen) {
            if (isNegative) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        } else {
            String maxIntStr = String.valueOf(Integer.MAX_VALUE);
            String resultStr = sb.toString();
            boolean isExceed = false;
            int lastPos = maxIntStr.length() - 1;
            for (int j = 0; j < maxIntStr.length(); j++) {
                if (j != lastPos) {
                    if (resultStr.charAt(j) > maxIntStr.charAt(j)) {
                        isExceed = true;
                        break;
                    }
                } else {
                    if (isNegative) {
                        if (resultStr.charAt(j) == '9') {
                            isExceed = true;
                            break;
                        }
                    } else {
                        if (resultStr.charAt(j) == '8' || resultStr.charAt(j) == '9') {
                            isExceed = true;
                            break;
                        }
                    }
                }
            }
            if (isExceed) {
                if (isNegative) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            } else {

                if (isNegative) {
                    return 0 - Integer.valueOf(resultStr).intValue();

                } else {
                    return Integer.valueOf(resultStr).intValue();
                }
            }
        }

        // return 0;
    }

    public int myAtoiV3(String s) {
        int i = 0;
        int n = s.length();
        int number = 0;
        // skip space
        while (i < n && s.charAt(i) == ' ')
            i++;
        if (i == n - 1)
            return 0;
        // check '+','-'

        int signed = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            if (s.charAt(i) == '-')
                signed = -1;
            i++;
        }
        while (i < n) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int digit = c - '0';
                number = number * 10 + digit;
            } else {
                break;
            }
            i++;
        }
        if (signed < 0) {
            return number * -1;
        } else {
            return number;
        }
    }

    public int myAtoiV4(String s) {
        int n = s.length();
        int i = 0;
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        if (i == n - 1)
            return 0;
        int sign = 1;
        int number = 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            if (s.charAt(i) == '-')
                sign = -1;
            i++;
        }
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            if (number > Integer.MAX_VALUE / 10 || (number == Integer.MAX_VALUE / 10 && digit > number % 10)) {
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            number = number * 10 + digit;
            i++;
        }
        return number * sign;
    }

    public int myAtoiV5(String s) {
        if (null == s)
            return 0;
        int pos = 0;
        // skip white space
        while (pos < s.length() && s.charAt(pos) == ' ') {
            pos++;
        }
        // check sign
        int sign = 1;
        if (s.charAt(pos) == '+' || s.charAt(pos) == '-') {
            if (s.charAt(pos) == '-') {
                sign = -1;
            }
            pos++;
        }
        int result = 0;
        while (pos < s.length() && s.charAt(pos) >= '0' && s.charAt(pos) <= '9') {
            int digit = s.charAt(pos) - '0';
            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 &&
                            digit > Integer.MAX_VALUE % 10)) {

                return sign == 1
                        ? Integer.MAX_VALUE
                        : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            pos++;
        }
        return result * sign;
    }

    public int myAtoiV6(String s) {
        if(null == s || s.length() == 0)return 0;
        int pos = 0;
        int sign = 1;
        int len = s.length();
        int result = 0;
        while(pos < len && s.charAt(pos) == ' '){
            pos++;
        }
        if(pos < len && (s.charAt(pos) == '+' || s.charAt(pos) == '-')){
            if(s.charAt(pos) == '-'){
                sign = -1;
            }
            pos++;
        }
        while(pos < len && Character.isDigit(s.charAt(pos))){
            int digit = s.charAt(pos) - '0';
            if(result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && digit > Integer.MAX_VALUE % 10)){
                if(sign > 0){
                    return Integer.MAX_VALUE;
                }else{
                    return Integer.MIN_VALUE;
                }
            }

            result = result * 10 + digit;
            pos++;
        }
        return result * sign;
    }

    public void test(String s, int expectedResult) {
        // System.out.println("####################################");
        int result = 0;
        // result = myAtoi(s);
        // System.out.printf("[V1] s=[%s],expected:[%d], actual:[%d]%n", s,
        // expectedResult, result);

        // result = myAtoiV2(s);
        // System.out.printf("[V2] s=[%s],expected:[%d], actual:[%d]%n", s,
        // expectedResult, result);
        // result = myAtoiV3(s);
        // System.out.printf("[V3] s=[%s],expected:[%d], actual:[%d]%n", s,
        // expectedResult, result);
        result = myAtoiV4(s);
        System.out.printf("[V4] s=[%s],expected:[%d], actual:[%d]%n", s, expectedResult, result);
        result = myAtoiV5(s);
        System.out.printf("[V5] s=[%s],expected:[%d], actual:[%d]%n", s, expectedResult, result);
        result = myAtoiV6(s);
        System.out.printf("[V6] s=[%s],expected:[%d], actual:[%d]%n", s, expectedResult, result);
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        LeetCode8 util = new LeetCode8();
        String s = "";
        int expectedResult = 0;

        // case 1
        s = "42";
        expectedResult = 42;
        util.test(s, expectedResult);
        // case 2
        s = " -042";
        expectedResult = -42;
        util.test(s, expectedResult);
        // case 3
        s = "1337c0d3";
        expectedResult = 1337;
        util.test(s, expectedResult);
        // case 4
        s = "0-1";
        expectedResult = 0;
        util.test(s, expectedResult);
        // case 5
        s = "words and 987";
        expectedResult = 0;
        util.test(s, expectedResult);
        s = "-2147483648";
        expectedResult = -2147483648;
        util.test(s, expectedResult);

        s = "-21474836482";
        expectedResult = -2147483648;
        util.test(s, expectedResult);

        s = "21474836472";
        expectedResult = 2147483647;
        util.test(s, expectedResult);
    }
}
