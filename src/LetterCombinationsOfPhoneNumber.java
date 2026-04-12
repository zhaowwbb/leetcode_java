import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Map;
// import java.util.HashMap;

public class LetterCombinationsOfPhoneNumber {

    char[][] letterMap = {
            {},
            {},
            { 'a', 'b', 'c' },
            { 'd', 'e', 'f' },
            { 'g', 'h', 'i' },
            { 'j', 'k', 'l' },
            { 'm', 'n', 'o' },
            { 'p', 'q', 'r', 's' },
            { 't', 'u', 'v' },
            { 'w', 'x', 'y', 'z' }
    };

    public List<String> letterCombinationsV2(String digits) {
        List<String> list = new ArrayList<>();
        if(null == digits || digits.length() == 0)return list;

        backtrace(list, new StringBuilder(), digits, 0);
        return list;
    }

    public void backtrace(List<String> list, StringBuilder sb, String digits, int index){
        if(digits.length() == index){
            list.add(sb.toString());
            return;
        }
        int pos = digits.charAt(index) - '0';

        char[] charArray = letterMap[pos];

        for(int i = 0; i < charArray.length; i++){
            char c = charArray[i];
            sb.append(c);
            backtrace(list, sb, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);

        }

        // backtrace(list, sb, digits, index + 1);


    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        if (null == digits || digits.length() == 0 || digits.length() > 4)
            return list;

        for (int i = 0; i < digits.length(); i++) {
            char c = digits.charAt(i);
            int pos = c - '0';
             System.out.println("pos=" + pos);
            // Integer key = Integer.valueOf(digits.charAt(i));
            char[] charArray = letterMap[pos];
            System.out.println("charArray=" + Arrays.toString(charArray));
            addCombinations(list, charArray);
        }
        List<String> resultList = new ArrayList<String>();
        for(String s : list){
            if(s.length() == digits.length()){
                resultList.add(s);
            }
        }
        return resultList;
    }

    public void addCombinations(List<String> list, char[] digitChars) {
         System.out.println("addCombinations, list.size()=" + list.size());
        List<String> tmpList = new ArrayList<>();
        for (int i = 0; i < digitChars.length; i++) {
            tmpList.add("" + digitChars[i]);
            for (int j = 0; j < list.size(); j++) {
                String s = list.get(j);
                tmpList.add(s + digitChars[i]);
            }
        }
        list.addAll(tmpList);
    }

    public void test(String digits) {
        System.out.println("digits=" + digits);
        List<String> result = letterCombinations(digits);
        System.out.println("result.size()=" + result.size());
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i != result.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
        System.out.println("");
        
        result = letterCombinationsV2(digits);
        System.out.println("V2 result.size()=" + result.size());
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i != result.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber util = new LetterCombinationsOfPhoneNumber();
        String digits = "23";
        util.test(digits);
        digits = "2";
        util.test(digits);
    }
}
