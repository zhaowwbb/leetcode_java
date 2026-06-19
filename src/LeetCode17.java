import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Map;
// import java.util.HashMap;
import java.util.Collections;

public class LeetCode17 {

    char[][] letterMap = {
            {},
            {},
            { 'a', 'b', 'c' },
            { 'd', 'e', 'f' },
            { 'g', 'h', 'i' },
            { 'j', 'k', 'l' },
            { 'm', 'n', 'o' },
            { 'p', 'q', 'r', 's' },
            { 't', 'u', 'v' },
            { 'w', 'x', 'y', 'z' }
    };

    public List<String> letterCombinationsV2(String digits) {
        List<String> list = new ArrayList<>();
        if (null == digits || digits.length() == 0)
            return list;

        backtrace(list, new StringBuilder(), digits, 0);
        return list;
    }

    public void backtrace(List<String> list, StringBuilder sb, String digits, int index) {
        if (digits.length() == index) {
            list.add(sb.toString());
            return;
        }
        int pos = digits.charAt(index) - '0';

        char[] charArray = letterMap[pos];

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            sb.append(c);
            backtrace(list, sb, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);

        }

        // backtrace(list, sb, digits, index + 1);

    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if (null == digits || digits.length() == 0 || digits.length() > 4)
            return list;

        for (int i = 0; i < digits.length(); i++) {
            char c = digits.charAt(i);
            int pos = c - '0';
            // System.out.println("pos=" + pos);
            // Integer key = Integer.valueOf(digits.charAt(i));
            char[] charArray = letterMap[pos];
            // System.out.println("charArray=" + Arrays.toString(charArray));
            addCombinations(list, charArray);
        }
        List<String> resultList = new ArrayList<String>();
        for (String s : list) {
            if (s.length() == digits.length()) {
                resultList.add(s);
            }
        }
        return resultList;
    }

    public void addCombinations(List<String> list, char[] digitChars) {
        // System.out.println("addCombinations, list.size()=" + list.size());
        List<String> tmpList = new ArrayList<>();
        for (int i = 0; i < digitChars.length; i++) {
            tmpList.add("" + digitChars[i]);
            for (int j = 0; j < list.size(); j++) {
                String s = list.get(j);
                tmpList.add(s + digitChars[i]);
            }
        }
        list.addAll(tmpList);
    }

    public List<String> letterCombinationsV3(String digits) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtraceV3(result, sb, digits, 0);
        return result;
    }

    public void backtraceV3(List<String> result, StringBuilder sb, String digits, int index) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }
        int pos = digits.charAt(index) - '0';
        char[] chars = letterMap[pos];
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            backtraceV3(result, sb, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> letterCombinationsV4(String digits) {
        List<String> result = new ArrayList<>();
        backtrackV4(result, new StringBuilder(), digits, 0);
        return result;
    }

    public void backtrackV4(List<String> result, StringBuilder sb, String digits, int index) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }
        int pos = digits.charAt(index) - '0';
        char[] letters = letterMap[pos];
        for (int i = 0; i < letters.length; i++) {
            sb.append(letters[i]);
            backtrackV4(result, sb, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> letterCombinationsV5(String digits) {
        List<String> result = new ArrayList<>();
        if (null == digits || digits.length() == 0) {
            return result;
        }
        backtraceV5(result, new StringBuilder(), 0, digits);
        return result;
    }

    public void backtraceV5(List<String> result, StringBuilder sb, int pos, String digits) {
        int len = digits.length();
        if (pos == len) {
            result.add(sb.toString());
            return;
        }
        int index = digits.charAt(pos) - '0';
        char[] chars = letterMap[index];
        for (char c : chars) {
            sb.append(c);
            backtraceV5(result, sb, pos + 1, digits);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> letterCombinationsV6(String digits) {
        List<String> list = new ArrayList<>();
        backtraceV6(new StringBuilder(), list, digits, 0);
        return list;
    }

    public void backtraceV6(StringBuilder sb, List<String> list, String digits, int pos) {
        if (pos == digits.length()) {
            list.add(sb.toString());
            return;
        }
        char c = digits.charAt(pos);
        int index = c - '0';
        char[] charList = letterMap[index];
        for (int i = 0; i < charList.length; i++) {
            sb.append(charList[i]);
            backtraceV6(sb, list, digits, pos + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode17 solver = new LeetCode17();

        // Multi-case datasets
        String[] testInputs = { "23", "2" };

        List<List<String>> expectedOutputs = new ArrayList<>();
        expectedOutputs.add(Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
        // expectedOutputs.add(new ArrayList<>());
        expectedOutputs.add(Arrays.asList("a", "b", "c"));

        System.out.println("--- Running Letter Combinations Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            String currentInput = testInputs[i];
            List<String> expected = expectedOutputs.get(i);

            // The single function call
            List<String> actual = solver.letterCombinationsV6(currentInput);

            // Sort copies of both lists to make the test order-insensitive
            List<String> sortedActual = new ArrayList<>(actual);
            List<String> sortedExpected = new ArrayList<>(expected);
            Collections.sort(sortedActual);
            Collections.sort(sortedExpected);

            // Validation check
            if (sortedActual.equals(sortedExpected)) {
                System.out.println(
                        "Test Case " + (i + 1) + ": PASSED (Input: \"" + currentInput + "\" -> " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: \"" + currentInput + "\"\n" +
                        "  Expected: " + expected + "\n  Got:      " + actual);
            }
        }
    }
}
