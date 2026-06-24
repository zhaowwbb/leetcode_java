import java.util.*;

public class LeetCode40 {

    // Implementation Method
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sorting is strictly required here to group duplicates together and prune
        // branches
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain,
            int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Early pruning: if the candidate is larger than the remainder, stop searching
            // this branch
            if (candidates[i] > remain) {
                break;
            }

            // Skip duplicates to prevent generating identical combinations
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            tempList.add(candidates[i]);
            // Move to 'i + 1' so each element index is used at most once per branch
            backtrack(result, tempList, candidates, remain - candidates[i], i + 1);
            // Backtrack
            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2V2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtraceV2(result, new ArrayList<Integer>(), candidates, target, 0);

        // remove duplicate
        Set<String> set = new HashSet<>();
        List<List<Integer>> filterResult = new ArrayList<>();
        for (List<Integer> list : result) {
            StringBuilder sb = new StringBuilder();
            list.forEach(i -> sb.append('#').append(i));
            String key = sb.toString();
            if (set.contains(key)) {
                continue;
            } else {
                filterResult.add(list);
                set.add(key);
            }
        }

        return filterResult;
    }

    public void backtraceV2(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain,
            int start) {
        if (remain == 0) {
            result.add(new ArrayList<Integer>(tempList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remain) {
                break;
            }
            tempList.add(candidates[i]);
            backtraceV2(result, tempList, candidates, remain - candidates[i], i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2V3(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtraceV3(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }

    public void backtraceV3(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain,
            int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remain) {
                break;
            }
            // skip duplicate
            if (i > start && candidates[i] == candidates[i - 1])
                continue;
            tempList.add(candidates[i]);
            backtraceV3(result, tempList, candidates, remain - candidates[i], i+ 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // --- Automated Test Framework ---

    static class TestCase {
        int[] candidates;
        int target;
        List<List<Integer>> expected;

        TestCase(int[] candidates, int target, Integer[][] expectedArray) {
            this.candidates = candidates;
            this.target = target;
            this.expected = new ArrayList<>();
            for (Integer[] row : expectedArray) {
                List<Integer> list = Arrays.asList(row);
                Collections.sort(list); // Ensure internal rows are sorted
                this.expected.add(list);
            }
            sortResults(this.expected); // Sort overall matrix
        }
    }

    private static void sortResults(List<List<Integer>> list) {
        list.sort((a, b) -> {
            int minLen = Math.min(a.size(), b.size());
            for (int i = 0; i < minLen; i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
            }
            return a.size() - b.size();
        });
    }

    public static void main(String[] args) {
        LeetCode40 solver = new LeetCode40();
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1
        testCases.add(new TestCase(
                new int[] { 10, 1, 2, 7, 6, 1, 5 },
                8,
                new Integer[][] { { 1, 1, 6 }, { 1, 2, 5 }, { 1, 7 }, { 2, 6 } }));

        // Test Case 2
        testCases.add(new TestCase(
                new int[] { 2, 5, 2, 1, 2 },
                5,
                new Integer[][] { { 1, 2, 2 }, { 5 } }));

        // Test Case 3 (Edge case: no valid combinations)
        testCases.add(new TestCase(
                new int[] { 2, 4, 6 },
                5,
                new Integer[][] {}));

        System.out.println("Executing Combination Sum II Automation Suite...\n");
        int passedCount = 0;

        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);

            // Call the implementation method ONLY ONE time per test case
            // List<List<Integer>> actualResult = solver.combinationSum2(tc.candidates,
            // tc.target);
            // List<List<Integer>> actualResult = solver.combinationSum2V2(tc.candidates,
            // tc.target);
            List<List<Integer>> actualResult = solver.combinationSum2V3(tc.candidates, tc.target);

            sortResults(actualResult);

            boolean isPassed = actualResult.equals(tc.expected);
            if (isPassed) {
                passedCount++;
            }

            System.out.println("Test Case " + (i + 1) + ": " + (isPassed ? "✅ PASS" : "❌ FAIL"));
            System.out.println("  Candidates: " + Arrays.toString(tc.candidates) + " | Target: " + tc.target);
            System.out.println("  Expected  : " + tc.expected);
            System.out.println("  Actual    : " + actualResult);
            System.out.println("-------------------------------------------------------------------");
        }

        System.out.println("Test Run Complete: " + passedCount + "/" + testCases.size() + " Passed.");
    }
}