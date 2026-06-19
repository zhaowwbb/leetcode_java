import java.util.Arrays;

public class LeeCode34 {

    public int[] searchRange(int[] nums, int target) {
        int[] notExist = new int[] { -1, -1 };
        if (null == nums || nums.length == 0) {
            return notExist;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                System.out.println("mid=" + mid + ", len =" + nums.length);
                int startIndex = mid;
                int endIndex = mid;
                while (startIndex >= 0 && nums[startIndex] == target) {
                    startIndex--;
                }
                startIndex++;
                startIndex = startIndex < 0 ? 0 : startIndex;
                while (endIndex < nums.length && nums[endIndex] == target) {
                    endIndex++;
                }
                endIndex--;
                endIndex = endIndex == nums.length ? nums.length - 1 : endIndex;
                return new int[] { startIndex, endIndex };
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return notExist;
    }

    public int[] searchRangeV2(int[] nums, int target) {
        int[] result = new int[] { -1, -1 };
        if (null == nums || nums.length == 0)
            return result;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // check boundary index
                int start = mid;
                int end = mid;
                while (start - 1 >= 0 && nums[start - 1] == nums[start]) {
                    start--;
                }
                while (end + 1 < nums.length && nums[end] == nums[end + 1]) {
                    end++;
                }
                return new int[] { start, end };
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LeeCode34 solver = new LeeCode34();

        // Multi-case datasets
        int[][] testInputs = {
                { 5, 7, 7, 8, 8, 10 },
                { 5, 7, 7, 8, 8, 10 },
                {},
                { 2, 2 }
        };
        int[] testTargets = { 8, 6, 0, 2 };

        int[][] expectedOutputs = {
                { 3, 4 },
                { -1, -1 },
                { -1, -1 },
                { 0, 1 }
        };

        System.out.println("--- Running Find First and Last Position Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            int[] currentInput = testInputs[i];
            int target = testTargets[i];
            int[] expected = expectedOutputs[i];

            // The single function call
            int[] actual = solver.searchRangeV2(currentInput, target);

            // Validation check using Arrays.equals for deep array matching
            if (Arrays.equals(actual, expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (Array: " + Arrays.toString(currentInput) +
                        ", Target: " + target + " -> Range: " + Arrays.toString(actual) + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Array: " + Arrays.toString(currentInput) +
                        " | Target: " + target + "\n  Expected: " + Arrays.toString(expected) +
                        " | Got: " + Arrays.toString(actual));
            }
        }
    }
}
