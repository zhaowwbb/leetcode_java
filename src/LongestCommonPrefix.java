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
        // int pos = 0;
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

    public String longestCommonPrefixV3(String[] strs) {
        String commonPrefix = "";
        if (null == strs || strs.length == 0)
            return commonPrefix;
        Arrays.sort(strs);
        int left = 0;
        int right = strs.length - 1;
        while (left < right) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            String s1 = strs[left];
            String s2 = strs[right];
            while (index < s1.length() && index < s2.length() && s1.charAt(index) == s2.charAt(index)) {
                sb.append(s1.charAt(index));
                index++;
            }
            if (sb.length() > commonPrefix.length()) {
                commonPrefix = sb.toString();
            }
            left++;
            right--;
        }

        return commonPrefix;
    }

    public String longestCommonPrefixV4(String[] strs) {
        if (null == strs || strs.length == 0)
            return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) < 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
            if (prefix.isEmpty())
                return "";
        }
        return prefix;

    }

    public void test(String[] strs, String expectedResult) {
        String result = longestCommonPrefix(strs);
        System.out.printf("[V1]s=[%s], expected:[%s], actual:[%s] %n", Arrays.toString(strs), expectedResult, result);
        result = longestCommonPrefixV2(strs);
        System.out.printf("[V2]s=[%s], expected:[%s], actual:[%s] %n", Arrays.toString(strs), expectedResult, result);
        result = longestCommonPrefixV3(strs);
        System.out.printf("[V3]s=[%s], expected:[%s], actual:[%s] %n", Arrays.toString(strs), expectedResult, result);
        result = longestCommonPrefixV4(strs);
        System.out.printf("[V4]s=[%s], expected:[%s], actual:[%s] %n", Arrays.toString(strs), expectedResult, result);
    }

    public static void main(String[] args) {
        LongestCommonPrefix util = new LongestCommonPrefix();

        String[] strs = { "flower", "flow", "flight" };
        util.test(strs, "fl");

        strs = new String[] { "dog", "racecar", "car" };
        util.test(strs, "");

        strs = new String[] {
                "technique", // Length 9
                "technician", // Length 10
                "tech", // Length 4 (The Bottleneck)
                "technology", // Length 10
                "technical", // Length 9
                "techie" // Length 6
        };
        util.test(strs, "tech");
    }
}
