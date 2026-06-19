import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class LeetCode18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        // 1. Sort the array to handle duplicates and use two-pointer convergence
        Arrays.sort(nums);
        int n = nums.length;

        // 2. First pointer
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicates for the first position
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // 3. Second pointer
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates for the second position
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                // 4. Two-pointer setup for the remaining two numbers
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // Use long to prevent integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for the third position
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        // Skip duplicates for the fourth position
                        while (left < right && nums[right] == nums[right - 1])
                            right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // Sum is too small, move left pointer rightward
                    } else {
                        right--; // Sum is too large, move right pointer leftward
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> fourSumV3(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4)
            return result;

        // 1. Sort the array
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // Skip duplicate for i
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // Optimization: Smallest sum in this iteration is too big
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
                break;
            // Optimization: Largest sum in this iteration is too small
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target)
                continue;

            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicate for j
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                // Optimization for j
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target)
                    break;
                if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target)
                    continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for left and right
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> fourSumV2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 4)
            return list;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
                break;
            if ((long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target)
                continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target)
                    break;
                if ((long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target)
                    continue;

                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long sum = (long) (nums[i] + nums[j] + nums[left] + nums[right]);
                    if (sum == target) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right - 1] == nums[right])
                            right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return list;
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (null == nums || nums.length < 3)
            return list;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return list;
    }

    public void printResult(List<List<Integer>> result, String version) {
        System.out.println("[" + version + "] result.size()=" + result.size());
        for (int i = 0; i < result.size(); i++) {
            List<Integer> row = result.get(i);
            System.out.print("    [");
            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j));
                if (j != row.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
            System.out.println("");
        }
    }

    public List<List<Integer>> fourSumV4(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 4)
            return result;
        int n = nums.length;
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[2] + nums[3];
        if (sum > target)
            return result;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        break;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public List<List<Integer>> fourSumV5(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 4)
            return result;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            // skip duplicated for the first element
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < n - 2; j++) {
                // skip duplicated for the second element
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long sum = (long) ((long) nums[i] + nums[j] + nums[left] + nums[right]);
                    System.out.println("4 sum[V5]=" + sum);
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // skip duplicated for the left
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        // skip duplicated for the right
                        while (left < right && nums[right] == nums[right - 1])
                            right--;
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public List<List<Integer>> fourSumV6(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 4)
            return result;
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            // int threeSumTarget = target - nums[i];
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int left = j + 1;
                int right = len - 1;
                while (left < right) {
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right - 1] == nums[right])
                            right--;
                        left++;
                        right--;
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return result;
    }

    // Helper method to normalize lists for order-independent comparison
    private static List<List<Integer>> normalize(List<List<Integer>> lists) {
        List<List<Integer>> normalized = new ArrayList<>();
        for (List<Integer> list : lists) {
            List<Integer> sortedInner = new ArrayList<>(list);
            Collections.sort(sortedInner); // Sort inner quadruplet elements
            normalized.add(sortedInner);
        }
        // Sort the outer list based on element comparisons
        normalized.sort((a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0)
                    return cmp;
            }
            return Integer.compare(a.size(), b.size());
        });
        return normalized;
    }

    // Test logic in the main method
    public static void main(String[] args) {
        LeetCode18 solver = new LeetCode18();

        // Multi-case datasets
        int[][] testInputs = {
                { 1, 0, -1, 0, -2, 2 },
                { 2, 2, 2, 2, 2 }
        };
        int[] testTargets = { 0, 8 };

        // Master list containing expected nested lists for each test case
        List<List<List<Integer>>> expectedOutputs = new ArrayList<>();

        // Case 1 expectations (target = 0)
        expectedOutputs.add(Arrays.asList(
                Arrays.asList(-2, -1, 1, 2),
                Arrays.asList(-2, 0, 0, 2),
                Arrays.asList(-1, 0, 0, 1)));

        // Case 2 expectations (target = 8)
        expectedOutputs.add(Arrays.asList(
                Arrays.asList(2, 2, 2, 2)));

        System.out.println("--- Running 4Sum Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            int[] currentInput = testInputs[i];
            int currentTarget = testTargets[i];
            List<List<Integer>> expected = expectedOutputs.get(i);

            // The single function call
            List<List<Integer>> actual = solver.fourSumV6(currentInput, currentTarget);

            // Normalize both collections to ensure order mismatches don't falsely fail the
            // test
            List<List<Integer>> normActual = normalize(actual);
            List<List<Integer>> normExpected = normalize(expected);

            if (normActual.equals(normExpected)) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (Input: " + Arrays.toString(currentInput) +
                        ", Target: " + currentTarget + " -> " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: " + Arrays.toString(currentInput) +
                        ", Target: " + currentTarget + "\n  Expected: " + expected + "\n  Got: " + actual);
            }
        }
    }
}
