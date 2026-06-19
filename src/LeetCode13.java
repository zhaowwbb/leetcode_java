import java.util.*;

public class LeetCode13 {

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

    char[] SYMBOL_ARRAY_V3 = { 'M', 'D', 'C', 'L', 'X', 'V', 'I' };
    int[] VALUE_ARRAY_V3 = { 1000, 500, 100, 50, 10, 5, 1 };

    public int romanToIntV3(String s) {
        if (null == s)
            return 0;
        int num = 0;
        int n = SYMBOL_ARRAY_V3.length;

        Map<Character, Integer> symbolValueMapV3 = new HashMap<>();
        for (int i = 0; i < SYMBOL_ARRAY_V3.length; i++) {
            symbolValueMapV3.put(SYMBOL_ARRAY_V3[i], VALUE_ARRAY_V3[i]);
        }

        char preSymbol = s.charAt(0);
        int preValue = symbolValueMapV3.get(preSymbol);
        int currentValue = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            currentValue = symbolValueMapV3.get(c);
            if (currentValue > preValue) {
                // num += currentValue;
                num -= preValue;
            } else {
                num += preValue;
            }
            // System.out.println("num=" + num + ", preValue=" + preValue + ",[i]=" + i);
            preValue = currentValue;
        }
        num += currentValue;
        return num;
    }

    public int romanToIntV4(String s) {
        if (null == s)
            return 0;
        Map<Character, Integer> romanValueMap = new HashMap<>();
        romanValueMap.put('I', 1);
        romanValueMap.put('V', 5);
        romanValueMap.put('X', 10);
        romanValueMap.put('L', 50);
        romanValueMap.put('C', 100);
        romanValueMap.put('D', 500);
        romanValueMap.put('M', 1000);
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int currentValue = romanValueMap.get(c);
            if (i < s.length() - 1 && currentValue < romanValueMap.get(s.charAt(i + 1))) {
                num -= currentValue;
            } else {
                num += currentValue;
            }
        }
        return num;
    }

    int[] VALUES = { 1000, 500, 100, 50, 10, 5, 1 };
    char[] SYMBOLS = { 'M', 'D', 'C', 'L', 'X', 'V', 'I' };

    public int romanToIntV5(String s) {
        int num = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < VALUES.length; i++) {
            map.put(SYMBOLS[i], VALUES[i]);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int currentValue = map.get(c);

            if (i < s.length() - 1 && currentValue < map.get(s.charAt(i + 1))) {
                num -= currentValue;
            } else {
                num += currentValue;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        LeetCode13 solver = new LeetCode13();

        // Multi-case datasets
        String[] testInputs = { "III", "LVIII", "MCMXCIV", "XL", "DCXXI" };
        int[] expectedOutputs = { 3, 58, 1994, 40, 621 };

        System.out.println("--- Running Roman to Integer Tests ---");

        // Sequential validation using a single function call point
        for (int i = 0; i < testInputs.length; i++) {
            String currentInput = testInputs[i];
            int expected = expectedOutputs[i];

            // The single function call
            // int actual = solver.romanToInt(currentInput);
            int actual = solver.romanToIntV5(currentInput);

            // Validation check
            if (actual == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (\"" + currentInput + "\" -> " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: \"" + currentInput + "\" | Expected: "
                        + expected + ", but got: " + actual);
            }
        }
    }
}
