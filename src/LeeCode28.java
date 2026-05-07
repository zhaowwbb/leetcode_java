public class LeeCode28 {

    public int strStr(String haystack, String needle) {
        int index = -1;
        if(null ==haystack || null == needle)return index;
        int left = 0;
        while(left < haystack.length() - needle.length()){
            int i = 0;
            int tmpIndex = left;
            while(i < needle.length() && haystack.charAt(left) == needle.charAt(i)){
                left++;
                i++;
            }
            if(i == needle.length()){
                //found match
                return tmpIndex;
            }else{
                left += i;
            }
        }
        return index;
    }

    public int strStrV2(String haystack, String needle) {
        int index = -1;
        if(null == haystack || null == needle)return index;
        int hLen = haystack.length();
        int nLen = needle.length();

        if(hLen < nLen)return index;
        for(int i = 0; i < hLen - nLen; i++){
            int j = 0;
            while(j < nLen && haystack.charAt(i + j) == needle.charAt(j)){
                j++;
            }
            if(j == nLen){
                //found
                return i;
            }
        }
        return index;
    }

    public void test(String haystack, String needle, int expected) {
        System.out.println("haystack=[" + haystack + "[], needle=[" + needle + "]   ");
        int result = strStr(haystack, needle);
        System.out.printf("[V1] expected:[%d], actual:[%d] %n", expected, result);

        result = strStrV2(haystack, needle);
        System.out.printf("[V2] expected:[%d], actual:[%d] %n", expected, result);
        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        LeeCode28 lc = new LeeCode28();
        String haystack = "sadbutsad";
        String needle = "sad";
        lc.test(haystack, needle, 0);
        haystack = "leetcode";
        needle = "leeto";
        lc.test(haystack, needle, -1);
    }
}
