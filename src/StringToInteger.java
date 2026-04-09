public class StringToInteger {

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
        while (i < n) {
            if (Character.isDigit(s.charAt(i))) {
                int digit = s.charAt(i) - '0';
                System.out.print("[" + digit + "]");
                if (result > Integer.MAX_VALUE / 10
                        || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                    return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                result = result * 10 + digit;
                i++;
            } else {
                break;
            }
        }
        System.out.println("");
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
        System.out.println("number=" + sb.toString() + ",maxLen=" + maxLen);
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

    public void test(String s, int expectedResult) {
        int result = myAtoi(s);
        System.out.printf("s=[%s],expected:[%d], actual:[%d]%n", s, expectedResult, result);
        result = myAtoiV2(s);
        System.out.printf("s=[%s],expected:[%d], actual:[%d]%n", s, expectedResult, result);

    }

    public static void main(String[] args) {
        StringToInteger util = new StringToInteger();
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

    }
}
