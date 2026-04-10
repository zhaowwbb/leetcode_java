public class RegularExpressionMatching {


    public boolean isMatchV2(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for(int j = 2; j <= n; j++ ){
            if(p.charAt(j - 1) == '*'){
                dp[0][j] = dp[0][j - 2];
            }   
        }

        for(int i = 1; i <= m; i++ ){
            for(int j = 1; j <= n; j++){
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if(sc == pc || pc == '.'){
                    dp[i][j] = dp[i - 1][j - 1];
                }else if(pc == '*'){
                    if(dp[i][j - 2]){
                        dp[i][j] = true;
                    }else{
                        char prePc = p.charAt(j - 2);
                        if(prePc == '.' || prePc == sc){
                            dp[i][j] = dp[i - 1][j];
                        }
                    }
                }
            }
        }

        return dp[m][n];
    }

    //failed
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
        if(i < s.length())return false;
        // if(i == s.length() - 1 && )

        return i < s.length() || j < p.length();


        // return i >= (s.length() - 1) && j >= (p.length() - 1);
    }

    public void test(String s, String p, boolean expectedResult) {
        boolean result = isMatch(s, p);
        System.out.printf("s=[%s], p=[%s], expected:[%b], actual:[%b]%n", s, p, expectedResult, result);

        result = isMatchV2(s, p);
        System.out.printf("s=[%s], p=[%s], expected:[%b], actual:[%b]%n", s, p, expectedResult, result);
    }

    public static void main(String[] args) {
        RegularExpressionMatching util = new RegularExpressionMatching();
        util.test("aa", "a", false);
        util.test("aa", "a*", true);
        util.test("ab", ".*", true);
    }
}
