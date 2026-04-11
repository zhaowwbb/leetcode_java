public class RomanToInteger {

    private int getVal(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public int romanToIntV2(String s) {
        if (null == s || s.length() == 0)
            return 0;

        int preValue = getVal(s.charAt(0));
        int total = 0;
        for (int i = 1; i < s.length(); i++) {
            int curValue = getVal(s.charAt(i));
            if (preValue < curValue) {
                total -= preValue;
            } else {
                total += preValue;
            }
            preValue = curValue;
        }
        total += preValue;

        return total;
    }

    public int romanToInt(String s) {
        System.out.println("s=" + s);
        if (null == s || s.length() > 15)
            return 0;

        int preVal = getVal(s.charAt(0));
        int total = 0;
        for (int i = 1; i < s.length(); i++) {
            int curVal = getVal(s.charAt(i));
            if (preVal < curVal) {
                total -= preVal;
            } else {
                total += preVal;
            }
            preVal = curVal;
            // i++;
        }
        total += preVal;
        return total;
    }

    public void test(String s, int expectedResult) {
        int result = 0;
        result = romanToInt(s);
        System.out.printf("s=[%s], expected:[%d], actual:[%d] %n", s, expectedResult, result);
        result = romanToIntV2(s);
        System.out.printf("s=[%s], expected:[%d], actual:[%d] %n", s, expectedResult, result);
    }

    public static void main(String[] args) {
        RomanToInteger util = new RomanToInteger();
        util.test("III", 3);
        util.test("LVIII", 58);
        util.test("MCMXCIV", 1994);

    }
}
