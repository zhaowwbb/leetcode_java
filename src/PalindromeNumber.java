public class PalindromeNumber {

    public boolean isPalindromeV2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int reverse = 0;
        while (reverse < x) {
            reverse = reverse * 10 + (x % 10);
            x /= 10;
        }
        if (x == reverse || x == (reverse / 10))
            return true;

        return false;
    }

    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x < 10)
            return true;
        String str = String.valueOf(x);
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public boolean isPalindromeV3(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0))return false;
        int rev = 0;
        while(x > rev){
            int digit = x % 10;
            rev = rev * 10 + digit;
            x /= 10;
        }
        if(rev == x || x == rev / 10)return true;
        return false;
    }

    public boolean isPalindromeV4(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0))return false;
        int rev = 0;
        while(rev < x)
        {
            int digit = x % 10;
            rev = rev * 10 + digit;
            x /= 10;
        }            
        return (x == rev || x == rev / 10);
    }

    public void test(int x, boolean expectedResult) {
        System.out.println("###############################");
        boolean result = isPalindrome(x);
        System.out.printf("x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
        result = isPalindromeV2(x);
        System.out.printf("x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
        result = isPalindromeV3(x);
        System.out.printf("x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
        result = isPalindromeV4(x);
        System.out.printf("x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
    }

    public static void main(String[] args) {
        PalindromeNumber util = new PalindromeNumber();
        util.test(121, true);
        util.test(-121, false);
        util.test(10, false);
    }
}
