import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Map;
// import java.util.HashMap;

public class LetterCombinationsOfPhoneNumber {

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

    private void printResult(List<String> result, String version) {
        System.out.println("[" + version + "] result.size()=" + result.size());
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i != result.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
        System.out.println("");
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

    public void test(String digits) {
        System.out.println("digits=" + digits);
        List<String> result = letterCombinations(digits);
        printResult(result, "V1");

        result = letterCombinationsV2(digits);
        printResult(result, "V2");

        result = letterCombinationsV3(digits);
        printResult(result, "V3");

        result = letterCombinationsV4(digits);
        printResult(result, "V4");
        System.out.println("#########################");
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber util = new LetterCombinationsOfPhoneNumber();
        String digits = "23";
        util.test(digits);
        digits = "2";
        util.test(digits);
    }
}
