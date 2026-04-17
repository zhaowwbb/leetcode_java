public class RegularExpressionMatching {

    public boolean isMatchV4(String s, String p) {
        if (null == s || null == p) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (pc == sc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    char prev = p.charAt(j - 2);
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else if (prev == sc || prev == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public boolean isMatchV3(String s, String p) {
        if (null == s || null == p)
            return false;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                // .
                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    char pre = p.charAt(j - 2);
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else if (pre == sc || pre == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                }

            }
        }
        return dp[m][n];
    }

    public boolean isMatchV2(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    if (dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else {
                        char prePc = p.charAt(j - 2);
                        if (prePc == '.' || prePc == sc) {
                            dp[i][j] = dp[i - 1][j];
                        }
                    }
                }
            }
        }

        return dp[m][n];
    }

    // failed
    public boolean isMatch(String s, String p) {
        System.out.println("s=" + s + ", p=" + p);
        if (null == s || null == p)
            return false;
        int i = 0, j = 0;
        char preChar = ' ';
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(i)) {
                preChar = s.charAt(i);
                i++;
                j++;
            } else {
                if (p.charAt(i) == '.') {
                    preChar = s.charAt(i);
                    i++;
                    j++;
                } else if (p.charAt(i) == '*') {
                    if (s.charAt(i) != preChar) {
                        // 0 occurence of *
                        preChar = s.charAt(i);
                        i++;
                        j++;
                    } else {
                        // 1 to many occurent of *
                        while (i < s.length() && s.charAt(i) == preChar) {
                            i++;
                        }
                        if (i < s.length()) {
                            preChar = s.charAt(i);
                        }
                        j++;
                    }
                } else {
                    return false;
                }
            }
        }

        System.out.println("i=" + i + ",j=" + j);
        if (i < s.length())
            return false;
        // if(i == s.length() - 1 && )

        return i < s.length() || j < p.length();

        // return i >= (s.length() - 1) && j >= (p.length() - 1);
    }

    public boolean isMatchV5(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j <= n; j++) {
            char c = p.charAt(j - 1);
            if (c == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char currentS = s.charAt(i - 1);
                char currentP = p.charAt(j - 1);
                if (currentP == '.' || currentP == currentS) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (currentP == '*') {
                    dp[i][j] = dp[i][j - 2];
                    char preChar = p.charAt(j - 2);
                    if (preChar == '.' || preChar == currentS) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public boolean isMatchV6(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char currentS = s.charAt(i - 1);
                char currentP = p.charAt(j - 1);
                if (currentP == '.' || currentP == currentS) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (currentP == '*') {
                    dp[i][j] = dp[i][j - 2];
                    char preChar = p.charAt(j - 2);
                    if (preChar == '.' || preChar == currentS) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public void test(String s, String p, boolean expectedResult) {
        boolean result = false;
        result = isMatchV3(s, p);
        System.out.printf("V3 s=[%s], p=[%s], expected:[%b], actual:[%b]%n", s, p, expectedResult, result);
        result = isMatchV4(s, p);
        System.out.printf("V4 s=[%s], p=[%s], expected:[%b], actual:[%b]%n", s, p, expectedResult, result);
        result = isMatchV5(s, p);
        System.out.printf("V5 s=[%s], p=[%s], expected:[%b], actual:[%b]%n", s, p, expectedResult, result);
        result = isMatchV6(s, p);
        System.out.printf("V6 s=[%s], p=[%s], expected:[%b], actual:[%b]%n", s, p, expectedResult, result);
    }

    public static void main(String[] args) {
        RegularExpressionMatching util = new RegularExpressionMatching();
        util.test("aab", "c*a*b", true);
        util.test("mississippi", "mis*is*p*.", false);
        util.test("", "a*b*c*", true);
    }
}
