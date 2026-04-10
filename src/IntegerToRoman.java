public class IntegerToRoman {

    public String intToRomanV2(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();
        for(int i = 0; i < values.length; i++){
            while(num >= values[i]){
                num -= values[i];
                roman.append(symbols[i]);
            }
        }

        return roman.toString();
    }

    public String intToRoman(int num) {
        System.out.println("num=" + num);
        if (num <= 0 || num >= 4000)
            return "";
        StringBuilder sb = new StringBuilder();
        int mCount = num / 1000;
        if (mCount > 0) {
            for (int i = 0; i < mCount; i++) {
                sb.append("M");
            }
            num = num % 1000;
        }
        // int dCount = 0;
        int digit = 0;
        // int cCount = 0;
        // System.out.println(" num=" + num + ", sb=" + sb);
        // 1000 to 100
        if (num >= 100) {
            // dCount = num / 500;
            digit = num / 100;
            // System.out.println("100 digit=" + digit);
            if (digit == 4) {
                sb.append("CD");
                // num = num - 400;
            } else if (digit == 9) {
                sb.append("CM");
                // num = num - 900;
            } else if (digit == 5) {
                sb.append("D");
                // num = num - 900;
            } else {
                if (digit > 5) {
                    sb.append("D");
                    digit = digit - 5;
                }
                for (int i = 0; i < digit; i++) {
                    sb.append("C");
                }
            }
            num = num % 100;
        }
        // System.out.println(" num=" + num + ", sb=" + sb);
        // 99 to 1
        if (num >= 10) {
            digit = num / 10;
            // System.out.println("10 digit=" + digit);
            if (digit == 4) {
                sb.append("XL");
            } else if (digit == 9) {
                sb.append("XC");
            } else if (digit == 5) {
                sb.append("L");
            } else {
                if (digit > 5) {
                    sb.append("L");
                    digit = digit - 5;
                }
                for (int i = 0; i < digit; i++) {
                    sb.append("X");
                }
            }
            num = num % 10;
        }

        // System.out.println(" num=" + num + ", sb=" + sb);
        // 1 - 9
        digit = num;
        // System.out.println("1 digit=" + digit);
        if (digit == 4) {
            sb.append("IV");
        } else if (digit == 9) {
            sb.append("IX");
        } else if (digit == 5) {
            sb.append("V");
        } else {
            if (digit > 5) {
                sb.append("V");
                digit = digit - 5;
            }
            for (int i = 0; i < digit; i++) {
                sb.append("I");
            }
        }

        return sb.toString();
    }

    public void test(int num, String expectedResult) {
        String result = intToRoman(num);
        System.out.printf("num=[%d], expected:[%s], actual:[%s]%n", num, expectedResult, result);

        result = intToRomanV2(num);
        System.out.printf("num=[%d], expected:[%s], actual:[%s]%n", num, expectedResult, result);
    }

    public static void main(String[] args) {
        IntegerToRoman util = new IntegerToRoman();
        util.test(3749, "MMMDCCXLIX");
        util.test(58, "LVIII");
        util.test(1994, "MCMXCIV");
    }
}
