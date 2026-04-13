import java.util.Map;
import java.util.HashMap;

public class LongestUniqueSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int maxLength = 0;
        java.util.Set<Character> charSet = new java.util.HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charSet.contains(c)) {
                maxLength = Math.max(charSet.size(), maxLength);
                charSet.clear();
                charSet.add(c);
            } else {
                charSet.add(c);
            }
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringV2(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int maxLength = 0;
        java.util.Map<Character, Integer> charIndexMap = new java.util.HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charIndexMap.containsKey(c)) {
                left = Math.max(charIndexMap.get(c) + 1, left);

            }
            charIndexMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringV3(String s) {
        int maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charIndexMap.containsKey(c)) {
                left = Math.max(charIndexMap.get(c) + 1, left);
            }
            charIndexMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);

        }
        return maxLength;
    }

    public int lengthOfLongestSubstringV4(String s) {
        int maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charIndexMap.containsKey(c)) {
                left = Math.max(left, charIndexMap.get(c) + 1);
            }
            charIndexMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public void test(String s, int expect) {
        System.out.println("Input: " + s);

        int result = lengthOfLongestSubstring(s);
        System.out.printf("Expected: [%d], actual: [%d]%n", expect, result);

        result = lengthOfLongestSubstringV2(s);
        System.out.printf("Expected: [%d], actual: [%d]%n", expect, result);

        result = lengthOfLongestSubstringV3(s);
        System.out.printf("Expected: [%d], actual: [%d]%n", expect, result);

        result = lengthOfLongestSubstringV4(s);
        System.out.printf("Expected: [%d], actual: [%d]%n", expect, result);
    }

    public static void main(String[] args) {
        LongestUniqueSubstring utils = new LongestUniqueSubstring();

        utils.test("abcabcbb", 3);
        utils.test("bbbbb", 1);
        utils.test("pwwkew", 3);
    }
}
