import java.util.*;

public class LeetCode3 {

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

    public int lengthOfLongestSubstringV5(String s) {
        if (null == s)
            return 0;
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {

            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (!set.add(c)) {
                    maxLen = Math.max(maxLen, set.size());
                    set.clear();
                    // System.out.print("")
                    break;
                }
            }
        }
        maxLen = Math.max(maxLen, set.size());

        return maxLen;
    }

    public int lengthOfLongestSubstringV6(String s) {
        if (null == s)
            return 0;
        Map<Character, Integer> charToIndex = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charToIndex.containsKey(c)) {
                left = Math.max(left, charToIndex.get(c) + 1);
            }
            charToIndex.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public int lengthOfLongestSubstringV7(String s) {
        int maxLen = 0;
        if (null == s)
            return maxLen;
        int left = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charToIndex.containsKey(c)) {
                left = Math.max(left, charToIndex.get(c) + 1);
            }
            charToIndex.put(c, right);
            int len = right - left + 1;
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }

    public int lengthOfLongestSubstringV8(String s) {
        int maxLen = 0;
        if(null == s || s.length() == 0)return maxLen;
        int left = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();
        for(int right = 0; right < s.length(); right++){
            char curChar = s.charAt(right);
            if(charToIndex.containsKey(curChar)){
                left = Math.max(left, charToIndex.get(curChar) + 1);
            }
            charToIndex.put(curChar, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public void test(String s, int expect) {
        System.out.println("Input: " + s);

        int result = 0;
        // result = lengthOfLongestSubstring(s);
        // System.out.printf("[V1] Expected: [%d], actual: [%d]%n", expect, result);

        // result = lengthOfLongestSubstringV2(s);
        // System.out.printf("[V2] Expected: [%d], actual: [%d]%n", expect, result);

        // result = lengthOfLongestSubstringV3(s);
        // System.out.printf("[V3] Expected: [%d], actual: [%d]%n", expect, result);

        // result = lengthOfLongestSubstringV4(s);
        // System.out.printf("[V4] Expected: [%d], actual: [%d]%n", expect, result);

        result = lengthOfLongestSubstringV5(s);
        System.out.printf("[V5] Expected: [%d], actual: [%d]%n", expect, result);

        result = lengthOfLongestSubstringV6(s);
        System.out.printf("[V6] Expected: [%d], actual: [%d]%n", expect, result);

        result = lengthOfLongestSubstringV7(s);
        System.out.printf("[V7] Expected: [%d], actual: [%d]%n", expect, result);

        result = lengthOfLongestSubstringV8(s);
        System.out.printf("[V8] Expected: [%d], actual: [%d]%n", expect, result);
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        LeetCode3 utils = new LeetCode3();
        utils.test("au", 2);
        utils.test("abcabcbb", 3);
        utils.test("bbbbb", 1);
        utils.test("pwwkew", 3);
    }
}
