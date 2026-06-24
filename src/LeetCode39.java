import java.util.*;

public class LeetCode39 {
    // Implementation Method
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sorting helps us prune the search tree early (optimization)
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain,
            int start) {
        if (remain == 0) {
            // Found a valid combination, make a deep copy and add to results
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // If the current candidate exceeds the remaining target, no need to check
            // further
            if (candidates[i] > remain) {
                break;
            }

            tempList.add(candidates[i]);
            // Notice we pass 'i' and not 'i + 1' because we can reuse the same element
            backtrack(result, tempList, candidates, remain - candidates[i], i);
            // Backtrack: remove the last element before trying the next candidate
            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> combinationSumV2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtraceV2(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }

    public void backtraceV2(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain,
            int start) {
        if (remain == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            if (num > remain) {
                break;
            }
            tempList.add(num);
            backtraceV2(result, tempList, candidates, remain - num, i);
            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> combinationSumV3(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtraceV3(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }

    public void backtraceV3(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain,
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
            backtraceV3(result, tempList, candidates, remain - candidates[i], i);
            tempList.remove(tempList.size() - 1);
        }
    }

    // --- Test Logic Framework ---

    // Helper class to encapsulate a single test case
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
                Collections.sort(list); // Ensure internal lists are sorted for comparison
                this.expected.add(list);
            }
            // Sort the outer list so order of combinations won't break the equality check
            sortResults(this.expected);
        }
    }

    // Helper method to sort the list of combinations consistently
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
        LeetCode39 solver = new LeetCode39();

        // Defining multiple test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1
        testCases.add(new TestCase(
                new int[] { 2, 3, 6, 7 },
                7,
                new Integer[][] { { 2, 2, 3 }, { 7 } }));

        // Test Case 2
        testCases.add(new TestCase(
                new int[] { 2, 3, 5 },
                8,
                new Integer[][] { { 2, 2, 2, 2 }, { 2, 3, 3 }, { 3, 5 } }));

        // Test Case 3
        testCases.add(new TestCase(
                new int[] { 2 },
                1,
                new Integer[][] {}));

        // Test Case 4 (Additional edge case: target matches a single candidate)
        testCases.add(new TestCase(
                new int[] { 5, 10, 15 },
                5,
                new Integer[][] { { 5 } }));

        // Run tests
        System.out.println("Executing Automation Test Suite...\n");
        int passedCount = 0;

        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);

            // SINGLE execution call per test case requirement
            // List<List<Integer>> actualResult = solver.combinationSum(tc.candidates,
            // tc.target);
            List<List<Integer>> actualResult = solver.combinationSumV3(tc.candidates, tc.target);

            // Sort actual result to ensure alignment with expected result configuration
            sortResults(actualResult);

            boolean isPassed = actualResult.equals(tc.expected);
            if (isPassed) {
                passedCount++;
            }

            // Print Test Execution Summary
            System.out.println("Test Case " + (i + 1) + ": " + (isPassed ? "✅ PASS" : "❌ FAIL"));
            System.out.println("  Candidates: " + Arrays.toString(tc.candidates) + " | Target: " + tc.target);
            System.out.println("  Expected  : " + tc.expected);
            System.out.println("  Actual    : " + actualResult);
            System.out.println("-------------------------------------------------------------------");
        }

        System.out.println("Test Run Complete: " + passedCount + "/" + testCases.size() + " Passed.");
    }
}
