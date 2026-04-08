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
        for(int left = 0, right = 0; right < s.length(); right++){
            char c = s.charAt(right);
            if(charIndexMap.containsKey(c)){
                left = Math.max(charIndexMap.get(c) + 1, left);

            }
            charIndexMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }


    public static void main(String[] args) {
        String s = "abcabcbb";
        LongestUniqueSubstring utils = new LongestUniqueSubstring();
        int result = utils.lengthOfLongestSubstring(s);
         System.out.println("expected: 3, actual: " + result);   
result = utils.lengthOfLongestSubstringV2(s);
System.out.println("expected: 3, actual: " + result);  
        s = "bbbbb";
        result = utils.lengthOfLongestSubstring(s);
        System.out.println("expected: 1, actual: " + result);
        result = utils.lengthOfLongestSubstringV2(s);
        System.out.println("expected: 1, actual: " + result);

        s = "pwwkew";
        result = utils.lengthOfLongestSubstring(s);
        System.out.println("expected: 3, actual: " + result);
        result = utils.lengthOfLongestSubstringV2(s);
        System.out.println("expected: 3, actual: " + result);
    }
}
