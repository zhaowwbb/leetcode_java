import java.util.ArrayList;
import java.util.List;

public class LeetCode10 {

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

    public boolean isMatchV7(String s, String p) {
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
                char curC = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curP == '.' || curC == curP) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (curP == '*') {
                    dp[i][j] = dp[i][j - 2];

                    char preChar = p.charAt(j - 2);
                    if (preChar == '.' || preChar == curC) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public boolean isMatchV8(String s, String p) {
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
                char currentC = s.charAt(i - 1);
                char currentP = p.charAt(j - 1);
                if (currentC == currentP || currentP == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (currentP == '*') {
                    // 0 occurrence
                    dp[i][j] = dp[i][j - 2];

                    char preP = p.charAt(j - 2);
                    if (preP == currentC || preP == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public boolean isMatchV9(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int j = 2; j <= pLen; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                char curC = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curP == '.' || curC == curP) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (curP == '*') {
                    dp[i][j] = dp[i][j - 2];
                    char preP = p.charAt(j - 2);
                    if (preP == curC || preP == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }

    static class RegexTestCase {
        String description;
        String s;
        String p;
        boolean expected;

        public RegexTestCase(String description, String s, String p, boolean expected) {
            this.description = description;
            this.s = s;
            this.p = p;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        LeetCode10 solver = new LeetCode10();
        System.out.println(
                "Running Regular Expression Matching Test Suite...\n------------------------------------------");

        // 1. Collate test layouts tracking specific wildcard constraints
        List<RegexTestCase> testCases = new ArrayList<>();

        // Example 1: Missing match case
        testCases.add(new RegexTestCase(
                "Basic character mismatch without wildcards",
                "aa",
                "a",
                false));

        // Example 2: Star wildcard match
        testCases.add(new RegexTestCase(
                "Star match multiplying preceding element",
                "aa",
                "a*",
                true));

        // Example 3: Dot and Star combinations
        testCases.add(new RegexTestCase(
                "Dot-Star sequence matching everything",
                "ab",
                ".*",
                true));

        // Complex Case: Zero occurrences handling
        testCases.add(new RegexTestCase(
                "Star sequences acting as zero occurrences in middle",
                "aab",
                "c*a*b",
                true));

        // Complex Case: Partial match failure
        testCases.add(new RegexTestCase(
                "Incomplete sequence consumption mismatch",
                "abcd",
                "d*",
                false));

        // 2. Iterate and process assertions exactly once per defined setup block
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            RegexTestCase tc = testCases.get(i);

            // Core execution point
            boolean actual = solver.isMatchV9(tc.s, tc.p);

            if (actual == tc.expected) {
                System.out.println(String.format("[PASS] Test %d: %s", i + 1, tc.description));
                passed++;
            } else {
                System.err.println(String.format("[FAIL] Test %d: %s", i + 1, tc.description));
                System.err.println("       String:  \"" + tc.s + "\" | Pattern: \"" + tc.p + "\"");
                System.err.println("       Expected: " + tc.expected);
                System.err.println("       Actual:   " + actual);
            }
        }

        System.out.println("------------------------------------------");
        System.out.println(String.format("Execution Result: %d/%d Tests Passed.", passed, testCases.size()));
    }
}
