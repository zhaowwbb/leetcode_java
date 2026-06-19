public class LeeCode28 {

    public int strStr(String haystack, String needle) {
        int index = -1;
        if (null == haystack || null == needle)
            return index;
        int left = 0;
        while (left < haystack.length() - needle.length()) {
            int i = 0;
            int tmpIndex = left;
            while (i < needle.length() && haystack.charAt(left) == needle.charAt(i)) {
                left++;
                i++;
            }
            if (i == needle.length()) {
                // found match
                return tmpIndex;
            } else {
                left += i;
            }
        }
        return index;
    }

    public int strStrV2(String haystack, String needle) {
        int index = -1;
        if (null == haystack || null == needle)
            return index;
        int hLen = haystack.length();
        int nLen = needle.length();

        if (hLen < nLen)
            return index;
        for (int i = 0; i < hLen - nLen; i++) {
            int j = 0;
            while (j < nLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == nLen) {
                // found
                return i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        LeeCode28 solver = new LeeCode28();

        // Multi-case datasets
        String[] haystacks = { "sadbutsad", "leetcode", "hello", "abc" };
        String[] needles = { "sad", "leeto", "ll", "a" };
        int[] expectedOutputs = { 0, -1, 2, 0 };

        System.out.println("--- Running Find First Occurrence Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < haystacks.length; i++) {
            String currentHaystack = haystacks[i];
            String currentNeedle = needles[i];
            int expected = expectedOutputs[i];

            // The single function call
            int actual = solver.strStrV2(currentHaystack, currentNeedle);

            // Validation check
            if (actual == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (\"" +
                        currentHaystack + "\", \"" + currentNeedle + "\" -> " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Haystack: \"" + currentHaystack +
                        "\" | Needle: \"" + currentNeedle + "\" | Expected: " + expected + ", but got: " + actual);
            }
        }
    }
}
