import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class ThreeSum {

    public List<List<Integer>> threeSumV3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 3)
            return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            System.out.println("---");
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // while (i > 0 && nums[i] == nums[i - 1]) {
            // continue;
            // }
            int left = i + 1;
            int right = nums.length - 1;
            System.out.println("i=" + i + ",left=" + left + ",right=" + right);
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
                    System.out.println("##i=" + i + ",left=" + left + ",right=" + right);
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
            System.out.println("XXXXXX");
        }

        System.out.println("result.size()=" + result.size());
        for (int i = 0; i < result.size(); i++) {
            List<Integer> row = result.get(i);
            System.out.print("[");
            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j));
                if (j != row.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
            System.out.println("");
        }

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

    public void test(int[] nums) {
        List<List<Integer>> result = threeSum(nums);
        System.out.println("result.size()=" + result.size());
        for (int i = 0; i < result.size(); i++) {
            List<Integer> row = result.get(i);
            System.out.print("[");
            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j));
                if (j != row.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
            System.out.println("");
        }

        result = threeSumV2(nums);
        System.out.println("result.size()=" + result.size());
        for (int i = 0; i < result.size(); i++) {
            List<Integer> row = result.get(i);
            System.out.print("[");
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

    public static void main(String[] args) {
        ThreeSum util = new ThreeSum();
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        util.test(nums);
        System.out.println("#################");
        nums = new int[] { 0, 1, 1 };
        util.test(nums);
        System.out.println("#################");
        nums = new int[] { 0, 0, 0 };
        util.test(nums);
    }
}
