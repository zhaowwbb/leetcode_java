public class RomanToInteger {

    private int getVal(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public int romanToInt(String s) {
         System.out.println("s=" + s);
        if (null == s || s.length() > 15)
            return 0;

        int preVal = getVal(s.charAt(0));
        int total = 0;
        for(int i = 1; i < s.length(); i++ ){
            int curVal = getVal(s.charAt(i));
            if(preVal < curVal){
                total -= preVal;
            }else{
                total += preVal; 
            }
            preVal = curVal;
            // i++;
        }
        total += preVal;

        return total;



        // int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        // String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        // int num = 0;
        // int left = 0;
        // int i = 0;
        // char preC = 'M';
        // char c = 'M';
        // while (left < s.length() && i < symbols.length) {
        //     c = s.charAt(left);
        //     String romanSymbol = String.valueOf(c);
        //     if (romanSymbol.equals(symbols[i])) {
        //         num += values[i];
        //         System.out.println("num=" + num);
        //         left++;
        //     } else {
        //         if(i % 2 == 0){

        //         }
        //         i++;
        //         while(i < symbols.length )
        //         if(symbols[i].length() > 1 && symbols[i].charAt(0) == c){
        //             num += values[i];
        //             i++;
        //             left++;

        //         }else{
        //             i++;
        //         }
        //     }
        // }
        // return val;
    }

    public void test(String s, int expectedResult) {
        int result = romanToInt(s);

        System.out.printf("s=[%s], expected:[%d], actual:[%d] %n", s, expectedResult, result);
    }

    public static void main(String[] args) {
        RomanToInteger util = new RomanToInteger();
        util.test("III", 3);
        util.test("LVIII", 58);
        util.test("MCMXCIV", 1994);

    }
}
