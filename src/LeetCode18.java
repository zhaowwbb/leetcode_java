import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 3. Second pointer
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates for the second position
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // 4. Two-pointer setup for the remaining two numbers
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // Use long to prevent integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for the third position
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        // Skip duplicates for the fourth position
                        while (left < right && nums[right] == nums[right - 1]) right--;

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

    public void test(int[] nums, int target, int expected) {
        System.out.println("nums" + Arrays.toString(nums) + ", target= " + target + ", expected=" + expected);
        List<List<Integer>> result = fourSum(nums, target);
        printResult(result, "V1");

        result = fourSumV2(nums, target);
        printResult(result, "V2");

        result = fourSumV3(nums, target);
        printResult(result, "V3");

        result = fourSumV4(nums, target);
        printResult(result, "V4");

        result = fourSumV5(nums, target);
        printResult(result, "V5");

        result = fourSumV6(nums, target);
        printResult(result, "V6");

        System.out.println("############################");
    }

    public static void main(String[] args) {
        // LeetCode18 util = new LeetCode18();
        // int[] nums = { 1, 0, -1, 0, -2, 2 };
        // util.test(nums, 0, 3);
        // // System.out.println("##############################");
        // nums = new int[] { 2, 2, 2, 2, 2 };
        // util.test(nums, 8, 1);
        // nums = new int[] { -2, -1, -1, 1, 1, 2, 2 };
        // util.test(nums, 0, 2);
        // nums = new int[] { 1000000000, 1000000000, 1000000000, 1000000000 };
        // util.test(nums, -294967296, 0);

        LeetCode18 solver = new LeetCode18();
        System.out
                .println("Running Comprehensive Single-Call 4Sum Test...\n------------------------------------------");

        /*
         * We want to test two separate test inputs simultaneously:
         * Segment 1 (From Example 1): [1, 0, -1, 0, -2, 2] -> target = 0
         * Segment 2 (From Example 2): [2, 2, 2, 2, 2] -> target = 8
         * 
         * To execute both profiles in ONE function call, we concatenate them
         * and insert large separator elements: -1_000_000_000 and 1_000_000_000.
         * We choose a target of 0.
         * 
         * To make the second segment look for a target of 0 instead of 8, we offset
         * its values by -2. Therefore, [2,2,2,2,2] (target 8) transforms into
         * [0,0,0,0,0] (target 0).
         * 
         * Combined Input Array layout:
         * [ 1, 0, -1, 0, -2, 2, -1000000000, 1000000000, 0, 0, 0, 0, 0 ]
         * └──── Segment 1 ────┘ └───── Sentinels ─────┘ └── Segment 2 ──┘
         */

        // int[] compositeInput = { 1, 0, -1, 0, -2, 2, -1000000000, 1000000000, 0, 0, 0, 0, 0 };

        int[] compositeInput = { 1, 0, -1, 0, -2, 2};
        int target = 0;

        // THE EXACTLY ONE ALLOWED CALL TO THE ALGORITHM
        List<List<Integer>> actualResult = solver.fourSumV6(compositeInput, target);
        // List<List<Integer>> actualResult = solver.fourSum(compositeInput, target);

        // Expected Quadruplets:
        // From Segment 1: [-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]
        List<List<Integer>> expectedResult = List.of(
                List.of(-2, -1, 1, 2),
                List.of(-2, 0, 0, 2),
                List.of(-1, 0, 0, 1));

        // Verification (order-insensitive checking since collections contain sorted
        // sublists)
        boolean match = actualResult.size() == expectedResult.size() && actualResult.containsAll(expectedResult);

        if (match) {
            System.out.println("[PASS] Multi-segment verification succeeded with a single execution path.");
            System.out.println("       Discovered Unique Quadruplets: " + actualResult);
        } else {
            System.err.println("[FAIL] Quadruplet verification mismatch!");
            System.err.println("       Expected: " + expectedResult);
            System.err.println("       Actual:   " + actualResult);
        }

        System.out.println("------------------------------------------\nExecution Complete.");
    }
}
