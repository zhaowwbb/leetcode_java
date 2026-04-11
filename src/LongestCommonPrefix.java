import java.util.Arrays;

public class LongestCommonPrefix {

    public String longestCommonPrefixV2(String[] strs) {
        if (null == strs || strs.length == 0)
            return "";
        if (strs.length == 1)
            return strs[0];
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int pos = 0;
        while (pos < first.length() && pos < last.length()) {
            if (first.charAt(pos) != last.charAt(pos)) {
                break;
            } else {
                pos++;
            }
        }
        return first.substring(0, pos);
    }

    public String longestCommonPrefix(String[] strs) {
        if (null == strs)
            return "";
        if (1 == strs.length)
            return strs[0];
        StringBuilder sb = new StringBuilder();
        String str0 = strs[0];
        int pos = 0;
        boolean isContinue = true;
        for (int i = 0; i < str0.length() && isContinue; i++) {
            char c = str0.charAt(i);
            for (int j = 1; j < strs.length && isContinue; j++) {
                String s = strs[j];
                if (i >= s.length()) {
                    return sb.toString();
                    // break;
                }
                if (s.charAt(i) != c) {
                    return sb.toString();
                    // break;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public void test(String[] strs, String expectedResult) {
        String result = longestCommonPrefix(strs);
        System.out.printf("s=[%s], expected:[%s], actual:[%s] %n", Arrays.toString(strs), expectedResult, result);
        result = longestCommonPrefixV2(strs);
        System.out.printf("s=[%s], expected:[%s], actual:[%s] %n", Arrays.toString(strs), expectedResult, result);

    }

    public static void main(String[] args) {
        LongestCommonPrefix util = new LongestCommonPrefix();

        String[] strs = { "flower", "flow", "flight" };
        util.test(strs, "fl");

        strs = new String[] { "dog", "racecar", "car" };
        util.test(strs, "");

    }
}
