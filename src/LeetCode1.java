import java.util.*;

public class LeetCode1 {

    /**
     * Determines the array index (0, 1, or 2) that holds the maximum value.
     * * @param p1 The integer at position 0
     * 
     * @param p2 The integer at position 1
     * @param p3 The integer at position 2
     * @return The index (0, 1, or 2) of the maximum value.
     *         If there is a tie, it returns the first occurrence.
     */
    public int findMaxPosition(int p1, int p2, int p3) {
        // We initialize the max value as p1 (position 0)
        int maxVal = p1;
        int maxPos = 0;

        // Compare with p2 (position 1)
        if (p2 > maxVal) {
            maxVal = p2;
            maxPos = 1;
        }

        // Compare with p3 (position 2)
        if (p3 > maxVal) {
            maxPos = 2;
        }

        return maxPos;
    }

    /**
     * Counts occurrences where a value is significantly different from its
     * neighbors.
     * 
     * @param data List of Double numbers
     * @return Number of occurrences meeting the criteria
     */
    public int countPatternOccurrences(List<Double> data) {
        if (data == null || data.size() < 3) {
            return 0; // Need at least 3 elements to have a middle value
        }

        int count = 0;
        double threshold = 5.0;

        // Start at 1, end before the last element
        for (int i = 1; i < data.size() - 1; i++) {
            double prev = data.get(i - 1);
            double curr = data.get(i);
            double next = data.get(i + 1);

            // Condition 1: Neighbors are all greater than current + 5
            boolean isLocalValley = (prev > curr + threshold) && (next > curr + threshold);

            // Condition 2: Neighbors are all less than current - 5
            boolean isLocalPeak = (prev < curr - threshold) && (next < curr - threshold);

            if (isLocalValley || isLocalPeak) {
                count++;
            }
        }

        return count;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {};

    }

    public int[] twoSumOptimized(int[] nums, int target) {
        java.util.Map<Integer, Integer> numToIndex = new java.util.HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numToIndex.containsKey(complement)) {
                return new int[] { numToIndex.get(complement), i };
            }
            numToIndex.put(nums[i], i);
        }
        return new int[] {};
    }

    public int[] twoSum2(int[] nums, int target) {
        java.util.Map<Integer, Integer> numberToIndex = new java.util.HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numberToIndex.containsKey(complement)) {
                return new int[] { numberToIndex.get(complement), i };

            }
            numberToIndex.put(nums[i], i);
        }
        return new int[] {};

    }

    public int[] twoSum3(int[] nums, int target) {
        int[] result = {};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remained = target - nums[i];
            if (map.containsKey(remained)) {
                return new int[] { map.get(remained), i };
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }

    public int[] twoSumV5(int[] nums, int target) {
        int[] result = new int[] { -1, -1 };
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int remains = target - nums[i];
            if (map.containsKey(remains)) {
                return new int[] { map.get(remains), i };
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }

    static class TwoSumTestCase {
        String description;
        int[] nums;
        int target;
        int[] expected;

        public TwoSumTestCase(String description, int[] nums, int target, int[] expected) {
            this.description = description;
            this.nums = nums;
            this.target = target;
            this.expected = expected;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Running 2Sum Data-Driven Test Suite...\n------------------------------------------");
        LeetCode1 solver = new LeetCode1();
        // 1. Define the list of test cases including inputs, targets, and expected
        // indices
        List<TwoSumTestCase> testCases = new ArrayList<>();

        testCases.add(new TwoSumTestCase(
                "Standard case",
                new int[] { 2, 7, 11, 15 }, 9,
                new int[] { 0, 1 }));

        testCases.add(new TwoSumTestCase(
                "Unsorted array with negative numbers",
                new int[] { 3, -2, 1, 9, 5 }, 3,
                new int[] { 1, 4 }));

        testCases.add(new TwoSumTestCase(
                "Identical values mapping to target",
                new int[] { 3, 3 }, 6,
                new int[] { 0, 1 }));

        testCases.add(new TwoSumTestCase(
                "Target requires non-adjacent elements",
                new int[] { 1, 5, 8, 3 }, 4,
                new int[] { 0, 3 }));

        // 2. Iterate and verify each layout sequentially
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TwoSumTestCase tc = testCases.get(i);

            // Invoke the 2Sum logic precisely once per execution loop layout
            int[] actual = solver.twoSumV5(tc.nums, tc.target);

            // Sort both to ensure order discrepancies don't break validation
            int[] actualSorted = actual.clone();
            int[] expectedSorted = tc.expected.clone();
            Arrays.sort(actualSorted);
            Arrays.sort(expectedSorted);

            if (Arrays.equals(actualSorted, expectedSorted)) {
                System.out.println(String.format("[PASS] Test %d: %s", i + 1, tc.description));
                passed++;
            } else {
                System.err.println(String.format("[FAIL] Test %d: %s", i + 1, tc.description));
                System.err.println("       Input:    " + Arrays.toString(tc.nums) + " | Target: " + tc.target);
                System.err.println("       Expected: " + Arrays.toString(tc.expected));
                System.err.println("       Actual:   " + Arrays.toString(actual));
            }
        }

        System.out.println("------------------------------------------");
        System.out.println(String.format("Execution Result: %d/%d Tests Passed.", passed, testCases.size()));
    }
}
