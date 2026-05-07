import java.util.*;

public class LeeCode30 {

    public void backtrace(List<String> list, StringBuilder sb, String[] words, boolean[] used) {
        if (sb.length() == words.length * (words[0].length())) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < words.length; i++) {
            if (used[i])
                continue;
            String str = words[i];
            used[i] = true;
            int lengthBefore = sb.length();
            sb.append(str);
            backtrace(list, sb, words, used);
            sb.setLength(lengthBefore);
            used[i] = false;
        }
    }

    public List<String> getSubStrs(String[] words) {
        List<String> list = new ArrayList<>();
        boolean[] used = new boolean[words.length];
        backtrace(list, new StringBuilder(), words, used);
        return list;
    }

    public void backtraceV2(List<String> result, StringBuilder sb, String[] words, boolean[] visited) {
        if (sb.length() == words.length * (words[0].length())) {
            result.add(sb.toString());
            return;
        }
        for (int i = 0; i < words.length; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            int beforeLength = sb.length();
            sb.append(words[i]);
            backtraceV2(result, sb, words, visited);
            visited[i] = false;
            sb.setLength(beforeLength);
        }
    }

    public List<String> listAllStringV2(String[] words) {
        List<String> list = new ArrayList<>();
        if (null == words || words.length == 0)
            return list;
        boolean[] visited = new boolean[words.length];
        backtraceV2(list, new StringBuilder(), words, visited);
        return list;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        // List<String> subStrList = getSubStrs(words);
        List<String> subStrList = listAllStringV2(words);
        // subStrList.forEach(System.out::println);

        for (int i = 0; i < subStrList.size(); i++) {
            int index = s.indexOf(subStrList.get(i));
            if (index >= 0) {
                result.add(index);
            }
        }
        // result.stream().forEach(str -> System.out.print("[" + str + "]"));
        // System.out.println("");
        // subStrList.stream().allMatch(null)
        // System.out.println(subStrList);
        return result;
    }

    public List<Integer> findSubstringV2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        int sLen = s.length();

        // 1. Build the frequency map for the words array
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // 2. Iterate through the string with different offsets
        // We only need to check from 0 to wordLen - 1
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            int count = 0;
            Map<String, Integer> seen = new HashMap<>();

            // 3. Move the 'right' pointer in steps of wordLen
            while (right + wordLen <= sLen) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordMap.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;

                    // If we have more of 'word' than needed, slide 'left' to remove
                    while (seen.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // If count matches total words, we found a valid index
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Word not in list: reset the window
                    seen.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    public List<Integer> findSubstringV3(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (null == s || null == words || words.length == 0)
            return result;
        int wordLen = words[0].length();
        int subStrLen = words.length * wordLen;
        if (s.length() < subStrLen)
            return result;
        Map<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        int wordNums = words.length;

        for (int i = 0; i < wordLen; i++) {
            // String word = words[i];
            int left = i;
            int right = i;
            int count = 0;
            Map<String, Integer> seenMap = new HashMap<>();
            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;
                if (wordMap.containsKey(word)) {
                    seenMap.put(word, seenMap.getOrDefault(word, 0) + 1);
                    count++;
                    while (seenMap.get(word) > wordMap.get(word)) {
                        // slide to left
                        String leftWord = s.substring(left, left + wordLen);
                        left += wordLen;
                        seenMap.put(leftWord, seenMap.getOrDefault(leftWord, 0) - 1);
                        count--;
                    }
                    if (count == wordNums) {
                        // find match
                        result.add(left);
                    }

                } else {
                    seenMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    public List<Integer> findSubstringV4(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (null == s || s.length() == 0 || null == words || words.length == 0)
            return result;
        int wordLen = words[0].length();
        int wordNums = words.length;
        Map<String, Integer> wordMap = new HashMap<>();

        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int count = 0;
            int left = i;
            int right = i;
            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;
                if (wordMap.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;
                    while (seen.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    if (count == wordNums) {
                        result.add(left);
                    }
                } else {
                    // not match
                    seen.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        return result;
    }

    public void test(String s, String[] words, String expected) {
        System.out.printf("Input s:[%s]   %n", s);
        System.out.println("words:" + Arrays.toString(words));
        List<Integer> result = findSubstring(s, words);
        System.out.println("[V1]expected :" + expected + ", Result :" + result);

        result = findSubstringV2(s, words);
        System.out.println("[V2]expected :" + expected + ", Result :" + result);

        result = findSubstringV3(s, words);
        System.out.println("[V3]expected :" + expected + ", Result :" + result);

        result = findSubstringV4(s, words);
        System.out.println("[V4]expected :" + expected + ", Result :" + result);
        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        LeeCode30 lc = new LeeCode30();

        String s = "barfoothefoobarman";
        String[] words = new String[] { "foo", "bar" };
        lc.test(s, words, "[9, 0]");

        s = "wordgoodgoodgoodbestword";
        words = new String[] { "word", "good", "best", "word" };
        lc.test(s, words, "[]");

        s = "barfoofoobarthefoobarman";
        words = new String[] { "bar", "foo", "the" };
        lc.test(s, words, "[9, 6, 12]");

        s = "aaa";
        words = new String[] { "a", "a" };
        lc.test(s, words, "[0,1]");
    }
}
