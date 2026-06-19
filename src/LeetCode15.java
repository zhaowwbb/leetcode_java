import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class LeetCode15 {

    public List<List<Integer>> threeSumV3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 3)
            return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // System.out.println("---");
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // while (i > 0 && nums[i] == nums[i - 1]) {
            // continue;
            // }
            int left = i + 1;
            int right = nums.length - 1;
            // System.out.println("i=" + i + ",left=" + left + ",right=" + right);
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                    // System.out.println("##i=" + i + ",left=" + left + ",right=" + right);
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
            // System.out.println("XXXXXX");
        }

        // System.out.println("result.size()=" + result.size());
        // for (int i = 0; i < result.size(); i++) {
        // List<Integer> row = result.get(i);
        // System.out.print("[");
        // for (int j = 0; j < row.size(); j++) {
        // System.out.print(row.get(j));
        // if (j != row.size() - 1) {
        // System.out.print(",");
        // }
        // }
        // System.out.print("]");
        // System.out.println("");
        // }

        return result;
    }

    public List<List<Integer>> threeSumV2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 3)
            return result;

        Arrays.sort(nums);
        // int left = 0;
        // int right = nums.length - 1;
        for (int i = 0; i < nums.length - 2; i++) {
            // remove duplicate
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (null == nums || nums.length < 3)
            return result;
        int sum = 0;
        Set<String> tripSet = new HashSet<String>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                for (int k = 2; k < nums.length; k++) {
                    if (i != j && i != k && j != k) {
                        sum = nums[i] + nums[j] + nums[k];
                        if (sum == 0) {
                            int[] tmp = new int[] { nums[i], nums[j], nums[k] };
                            Arrays.sort(tmp);

                            String key = Arrays.toString(tmp);
                            if (!tripSet.contains(key)) {
                                List<Integer> triplet = new ArrayList<Integer>();
                                triplet.add(nums[i]);
                                triplet.add(nums[j]);
                                triplet.add(nums[k]);
                                result.add(triplet);
                                tripSet.add(key);
                            } else {
                                // System.out.println("Remove duplicated!");
                            }
                        }
                    }
                }
            }
        }
        return result;
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

    public List<List<Integer>> threeSumV4(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 3)
            return result;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }

            }
        }

        return result;
    }

    public List<List<Integer>> threeSumV5(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 3)
            return result;

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return result;
    }

    public List<List<Integer>> threeSumV6(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    private static List<List<Integer>> normalize(List<List<Integer>> lists) {
        List<List<Integer>> normalized = new ArrayList<>();
        for (List<Integer> list : lists) {
            List<Integer> sortedInner = new ArrayList<>(list);
            Collections.sort(sortedInner); // Sort inner triplet elements
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
        LeetCode15 solver = new LeetCode15();

        // 2D Array holding multiple test cases
        int[][] testInputs = {
                { -1, 0, 1, 2, -1, -4 },
                { 0, 1, 1 },
                { 0, 0, 0 }
        };

        // Master list containing expected nested lists for each test case
        List<List<List<Integer>>> expectedOutputs = new ArrayList<>();

        // Case 1 expectations
        expectedOutputs.add(Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)));
        // Case 2 expectations
        expectedOutputs.add(new ArrayList<>());
        // Case 3 expectations
        expectedOutputs.add(Arrays.asList(Arrays.asList(0, 0, 0)));

        System.out.println("--- Running 3Sum Tests ---");

        // Single function call location inside the evaluation loop
        for (int i = 0; i < testInputs.length; i++) {
            int[] currentInput = testInputs[i];
            List<List<Integer>> expected = expectedOutputs.get(i);

            // The single function call
            List<List<Integer>> actual = solver.threeSumV6(currentInput);

            // Normalize both collections to ensure order mismatches don't falsely fail the
            // test
            List<List<Integer>> normActual = normalize(actual);
            List<List<Integer>> normExpected = normalize(expected);

            if (normActual.equals(normExpected)) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (Input: " + Arrays.toString(currentInput) + " -> "
                        + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: " + Arrays.toString(currentInput) +
                        "\n  Expected: " + expected + "\n  Got: " + actual);
            }
        }
    }
}
