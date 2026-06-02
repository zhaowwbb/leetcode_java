public class LeetCode9 {

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
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int rev = 0;
        while (x > rev) {
            int digit = x % 10;
            rev = rev * 10 + digit;
            x /= 10;
        }
        if (rev == x || x == rev / 10)
            return true;
        return false;
    }

    public boolean isPalindromeV4(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        int rev = 0;
        while (rev < x) {
            int digit = x % 10;
            rev = rev * 10 + digit;
            x /= 10;
        }
        return (x == rev || x == rev / 10);
    }

    public boolean isPalindromeV5(int x) {
        if(x <0)return false;
        String s = String.valueOf(x);
        int left = 0; 
        int right = s.length() - 1;
        while(left <= right && s.charAt(left) == s.charAt(right)){
            left ++;
            right --;
        }
        if(left > right){
            return true;
        }else{
            return false;
        }
    }

    public boolean isPalindromeV6(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0))return false;
        int reverseHalf = 0;
        while(x > reverseHalf){
            int pop = x % 10;
            reverseHalf = reverseHalf * 10 + pop;
            x /= 10;
        }
        return x == reverseHalf || reverseHalf / 10 == x;
    }

    public void test(int x, boolean expectedResult) {
        boolean result = isPalindrome(x);
        System.out.printf("[V1] x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
        result = isPalindromeV2(x);
        System.out.printf("[V2] x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
        result = isPalindromeV3(x);
        System.out.printf("[V3] x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
        result = isPalindromeV4(x);
        System.out.printf("[V4] x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
        result = isPalindromeV5(x);
        System.out.printf("[V5] x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);
        result = isPalindromeV6(x);
        System.out.printf("[V6] x=[%d],expected:[%b], actual:[%b]%n", x, expectedResult, result);        
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        LeetCode9 util = new LeetCode9();
        util.test(121, true);
        util.test(-121, false);
        util.test(10, false);
    }
}
