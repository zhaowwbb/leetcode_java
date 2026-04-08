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

    // public String longestPalindromeV2(String s) {
    // if (null == s)
    // return null;
    // if (s.length() == 1) {
    // return s;
    // }
    // int startPos = 0, endPos = 0;
    // Character preChar = s.charAt(0);
    // int palinStart = 0, palinEnd = 0, pos = 1, prePos = 0;
    // int len = 0;
    // boolean isForward = true;

    // while (pos < s.length()) {
    // Character c = s.charAt(pos);
    // preChar = s.charAt(prePos);
    // if (isForward) {
    // if (c.equals(preChar)) {
    // // found middle, check backward
    // isForward = false;
    // pos++;
    // if (prePos == 0) {
    // len = 2;
    // palinStart = 0;
    // palinEnd = 1;
    // } else {
    // prePos--;
    // }
    // } else {

    // }
    // } else {
    // // not palindome in the beginning
    // if (prePos < 0) {
    // isForward = true;
    // pos++;

    // prePos = pos;
    // pos++;
    // }

    // // backward from middle
    // if (c.equals(s.charAt(prePos))) {
    // if (palinStart == prePos) {
    // // found palindome
    // if (pos - prePos > len) {
    // // find longer
    // palinStart = prePos;
    // palinEnd = pos;
    // len = palinEnd - palinStart;
    // } else {
    // // find shorter
    // // skip
    // }
    // prePos = pos++;
    // if (prePos < s.length()) {
    // preChar = s.charAt(prePos);
    // pos++;
    // } else {
    // // to the end
    // break;
    // }
    // } else {
    // // check next one
    // prePos--;
    // pos++;
    // }

    // }
    // }
    // }

    // return "";
    // }

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

    public static void main(String[] args) {
        String ss = "ABCD";
        System.out.println("ss=" + ss.substring(0, 4));

        LongestPalindromicSubstring util = new LongestPalindromicSubstring();
        String s = "";
        String result = "";
        s = "babad";
        result = util.longestPalindrome(s);
        System.out.println("expected: bab, actual: " + result);
        result = util.longestPalindromeV2(s);
        System.out.println("expected: bab, actual: " + result);
        result = util.longestPalindromeV3(s);
        System.out.println("expected: bab, actual: " + result);

        s = "cbbd";
        result = util.longestPalindrome(s);
        System.out.println("expected: bb, actual: " + result);
        result = util.longestPalindromeV2(s);
        System.out.println("expected: bb, actual: " + result);
        result = util.longestPalindromeV3(s);
        System.out.println("expected: bb, actual: " + result);

        s = "abcdefghij**racecarace**klmnopqrst";
        result = util.longestPalindrome(s);
        System.out.println("expected: racecarace, actual: " + result);
        result = util.longestPalindromeV2(s);
        System.out.println("expected: racecarace, actual: " + result);
        result = util.longestPalindromeV3(s);
        System.out.println("expected: racecarace, actual: " + result);
    }
}
