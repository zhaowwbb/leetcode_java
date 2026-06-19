import java.util.Arrays;

public class LeetCode35 {

    public int searchInsert(int[] nums, int target) {
        if (null == nums)
            return -1;
        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1) {
            if (nums[0] < target) {
                return 1;
            } else {
                return 0;
            }
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public int searchInsertV2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        LeetCode35 solver = new LeetCode35();

        // Multi-case datasets
        int[][] testInputs = {
                { 1, 3, 5, 6 },
                { 1, 3, 5, 6 },
                { 1, 3, 5, 6 },
                { 1, 3, 5, 6 }
        };
        int[] testTargets = { 5, 2, 7, 0 };
        int[] expectedOutputs = { 2, 1, 4, 0 };

        System.out.println("--- Running Search Insert Position Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            int[] currentInput = testInputs[i];
            int target = testTargets[i];
            int expected = expectedOutputs[i];

            // The single function call
            int actual = solver.searchInsertV2(currentInput, target);

            // Validation check
            if (actual == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (Array: " + Arrays.toString(currentInput) +
                        ", Target: " + target + " -> Index: " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Array: " + Arrays.toString(currentInput) +
                        " | Target: " + target + "\n  Expected Index: " + expected + " | Got: " + actual);
            }
        }
    }
}
