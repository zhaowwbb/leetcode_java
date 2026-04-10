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

    public void test(int x, boolean expectedResult) {
        boolean result = isPalindrome(x);
        System.out.printf("x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
        result = isPalindromeV2(x);
        System.out.printf("x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);

    }

    public static void main(String[] args) {
        PalindromeNumber util = new PalindromeNumber();
        util.test(121, true);
        util.test(-121, false);
        util.test(10, false);
    }
}
