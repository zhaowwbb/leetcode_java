import java.util.Arrays;

public class LeeCode33 {

    public int search(int[] nums, int target) {
        if (null == nums)
            return -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public int searchV2(int[] nums, int target) {
        int notFound = -1;
        if (null == nums)
            return notFound;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return notFound;
    }

    public int searchV3(int[] nums, int target) {
        if (null == nums)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LeeCode33 solver = new LeeCode33();

        // Multi-case datasets
        int[][] testInputs = {
                { 4, 5, 6, 7, 0, 1, 2 },
                { 4, 5, 6, 7, 0, 1, 2 },
                { 1 },
                { 5, 1, 3 }
        };
        int[] testTargets = { 0, 3, 0, 5 };
        int[] expectedOutputs = { 4, -1, -1, 0 };

        System.out.println("--- Running Search in Rotated Sorted Array Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            int[] currentInput = testInputs[i];
            int target = testTargets[i];
            int expected = expectedOutputs[i];

            // The single function call
            int actual = solver.searchV3(currentInput, target);

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
