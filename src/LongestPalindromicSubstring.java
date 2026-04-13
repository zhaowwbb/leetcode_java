public class LongestPalindromicSubstring {

    public String longestPalindromeV3(String s) {
        if (null == s || s.length() == 0)
            return "";

        int left = 0, right = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int leftLen = expandFromMiddle(s, i, i);
            int rightLen = expandFromMiddle(s, i, i + 1);
            maxLen = Math.max(leftLen, rightLen);

            if (maxLen > right - left) {
                left = i - (maxLen - 1) / 2;
                right = i + (maxLen / 2);
            }

        }
        return s.substring(left, right + 1);

    }

    public int expandFromMiddle(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeV2(String s) {
        if (null == s)
            return null;
        if (s.length() == 1)
            return null;
        int i = 0, j = 0;
        String palindromeStr = "";
        for (i = 0; i < s.length(); i++) {
            for (j = i + 1; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                int left = i;
                int right = j;
                boolean isPalinDome = true;
                while (left < right) {
                    if (s.charAt(left) == s.charAt(right)) {
                        left++;
                        right--;
                    } else {
                        // not palindome
                        isPalinDome = false;
                        break;
                    }

                }
                if (isPalinDome) {
                    if (sub.length() > palindromeStr.length()) {
                        palindromeStr = sub;
                    }
                }

            }
        }
        return palindromeStr;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return "";

        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public int expandFromCenterV4(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeV4(String s) {
        int start = 0, end = 0;
        // int len = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenterV4(s, i, i);
            int len2 = expandFromCenterV4(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandFromCenterV5(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        return right - left - 1;
    }

    public String longestPalindromeV5(String s) {
        int start = 0, end = 0;
        for(int i = 0; i < s.length(); i++){
            int len1 = expandFromCenterV5(s, i, i);
            int len2 = expandFromCenterV5(s, i, i+ 1);
            int len = Math.max(len1,   len2);

            if(len > end - start){
                start = i - (len - 1) /2 ;
                end = i + len /2;
            }
        }
        return s.substring(start, end + 1);
    }

    public void test(String s, String expected) {
        System.out.println("s=" + s);
        String result = "";

        result = longestPalindrome(s);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);

        result = longestPalindromeV2(s);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);

        result = longestPalindromeV3(s);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);

        result = longestPalindromeV4(s);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);

        result = longestPalindromeV5(s);
        System.out.printf("Expected:[%s], actual:[%s]%n", expected, result);
    }

    public static void main(String[] args) {
        String ss = "ABCD";
        // System.out.println("ss=" + ss.substring(0, 4));

        LongestPalindromicSubstring util = new LongestPalindromicSubstring();
        String s = "";
        s = "babad";
        util.test(s, "bab");
        s = "cbbd";
        util.test(s, "bb");
        s = "babad";
        util.test(s, "bab");
        s = "abcdefghij**racecarace**klmnopqrst";
        util.test(s, "racecarace");
    }
}
