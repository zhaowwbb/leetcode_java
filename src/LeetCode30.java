import java.util.*;

public class LeetCode30 {

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

    public List<Integer> findSubstringV5(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (null == s || words == null || words.length == 0)
            return result;
        int wordLen = words[0].length();
        int subStrLen = wordLen * (words.length);
        if (s.length() < subStrLen)
            return result;
        Map<String, Integer> keyMap = new HashMap<>();
        Map<String, Integer> subStrMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            keyMap.put(words[i], keyMap.getOrDefault(words[i], 0) + 1);
        }
        // System.out.println("keyMap=" + keyMap);
        for (int i = 0; i <= s.length() - subStrLen; i++) {
            // String word = s.substring(i, i + wordLen);
            subStrMap.clear();
            boolean isValid = true;
            // if(!wordsSet.contains(word))continue;
            for (int j = i; j < i + subStrLen; j += wordLen) {
                String word = s.substring(j, j + wordLen);
                if (!keyMap.containsKey(word)) {
                    isValid = false;
                    break;
                }
                subStrMap.put(word, subStrMap.getOrDefault(word, 0) + 1);
                if (subStrMap.get(word) > keyMap.get(word)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                result.add(i);
            }
        }

        return result;
    }

    public List<Integer> findSubstringV6(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (null == s || null == words || words.length == 0)
            return result;
        int wordLen = words[0].length();
        int wordCount = words.length;

        Map<String, Integer> wordFrequency = new HashMap<>();

        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            int count = 0;
            Map<String, Integer> currentFrequency = new HashMap<>();
            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right = right + wordLen;
                if (wordFrequency.containsKey(word)) {
                    currentFrequency.put(word, currentFrequency.getOrDefault(word, 0) + 1);
                    count++;
                    while (currentFrequency.get(word) > wordFrequency.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);

                        currentFrequency.put(leftWord, currentFrequency.get(leftWord) - 1);
                        count--;
                        left = left + wordLen;
                    }
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    currentFrequency.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    public List<Integer> findSubstringV7(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        // If the string is shorter than the total length of all words combined
        if (s.length() < totalLen) {
            return result;
        }

        // Build the frequency map for the words array
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Run the sliding window wordLen times for different offsets
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> currentFreq = new HashMap<>();
            int count = 0; // Number of valid words currently in the window

            // Slide the window across the string
            while (right + wordLen <= s.length()) {
                // Extract the next word candidate
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordFreq.containsKey(word)) {
                    currentFreq.put(word, currentFreq.getOrDefault(word, 0) + 1);
                    count++;

                    // If we have more occurrences of 'word' than needed, shrink from the left
                    while (currentFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentFreq.put(leftWord, currentFreq.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // If the window size matches the total number of words, we found a match
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // The word is not part of the dictionary; reset the window
                    currentFreq.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    public List<Integer> findSubstringV8(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (null == s || s.length() == 0 || null == words || words.length == 0) {
            return result;
        }
        int wordLen = words[0].length();
        int wordCount = words.length;
        int subStrLen = wordLen * wordCount;
        if (s.length() < subStrLen) {
            return result;
        }
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> currentFreq = new HashMap<>();
            int count = 0;
            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;
                if (wordFreq.containsKey(word)) {
                    currentFreq.put(word, currentFreq.getOrDefault(word, 0) + 1);
                    count++;
                    while (currentFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentFreq.put(leftWord, currentFreq.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    currentFreq.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode30 solver = new LeetCode30();

        // Multi-case datasets
        String[] testStrings = {
                "barfoothefoobarman",
                "wordgoodgoodgoodwordofficial",
                "barfoofoobarthefoobarman"
        };
        String[][] testWords = {
                { "foo", "bar" },
                { "word", "good", "best", "word" },
                { "bar", "foo", "the" }
        };

        // Master list containing expected array structures for each test case
        List<List<Integer>> expectedOutputs = new ArrayList<>();
        expectedOutputs.add(Arrays.asList(0, 9));
        expectedOutputs.add(new ArrayList<>()); // Expected empty list
        expectedOutputs.add(Arrays.asList(6, 9, 12));

        System.out.println("--- Running Substring with Concatenation of All Words Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testStrings.length; i++) {
            String currentStr = testStrings[i];
            String[] currentWords = testWords[i];
            List<Integer> expected = expectedOutputs.get(i);

            // The single function call
            List<Integer> actual = solver.findSubstringV8(currentStr, currentWords);

            // Sort copies of both lists to make the validation check order-insensitive
            List<Integer> sortedActual = new ArrayList<>(actual);
            List<Integer> sortedExpected = new ArrayList<>(expected);
            Collections.sort(sortedActual);
            Collections.sort(sortedExpected);

            String wordsDisplay = Arrays.toString(currentWords);

            // Validation check
            if (sortedActual.equals(sortedExpected)) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (s: \"" + currentStr + "\", words: "
                        + wordsDisplay + " -> " + actual + ")");
            } else {
                System.err.println(
                        "Test Case " + (i + 1) + ": FAILED! s: \"" + currentStr + "\" | words: " + wordsDisplay +
                                "\n  Expected: " + expected + "\n  Got:      " + actual);
            }
        }
    }
}
