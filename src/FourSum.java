import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class FourSum {

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

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (null == nums || nums.length < 4)
            return list;

        int remainedTarget = 0;
        int[] threeSumArray = new int[nums.length - 1];
        Set<String> keySet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int fourVal = nums[i];
            remainedTarget = target - fourVal;
            int tempPos = i + 1;
            for (int j = 0; j < threeSumArray.length; j++) {
                if (tempPos == nums.length) {
                    tempPos = 0;
                }
                threeSumArray[j] = nums[tempPos];
                tempPos++;
            }
            List<List<Integer>> threeList = threeSum(threeSumArray, remainedTarget);
            for (int k = 0; k < threeList.size(); k++) {
                List<Integer> Klist = threeList.get(k);
                // System.out.println("Klist=" + Klist.toString());

                List<Integer> fourItem = new ArrayList<>();
                fourItem.add(fourVal);
                fourItem.addAll(Klist);
                int[] tt = fourItem.stream().mapToInt(x -> x).toArray();
                Arrays.sort(tt);
                String key = Arrays.toString(tt);
                // System.out.println("key=" + key);
                if (keySet.contains(key)) {
                    // skip
                } else {
                    list.add(fourItem);
                    keySet.add(key);
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
        if(null == nums || nums.length < 4)return result;
        int n = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < n - 3; i++){
            //skip duplicated for the first element
            if(i > 0 && nums[i] == nums[i - 1])continue;
            for(int j = i + 1; j < n - 2; j++){
                //skip duplicated for the second element
                if(j > i + 1 && nums[j] == nums[j - 1])continue;
                int left = j + 1;
                int right = n - 1;
                while(left < right){
                    long sum = (long)(nums[i] + nums[j] + nums[left] + nums[right]);
                    if(sum == target){
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        //skip duplicated for the left 
                        while(left < right && nums[left] == nums[left + 1])left++;
                        //skip duplicated for the right
                        while(left < right && nums[right] == nums[right - 1])right--;
                        left++;
                        right--;
                    }else if(sum < target){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public void test(int[] nums, int target) {
        System.out.println("nums" + Arrays.toString(nums) + ", target= " + target);
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
    }

    public static void main(String[] args) {
        FourSum util = new FourSum();
        int[] nums = { 1, 0, -1, 0, -2, 2 };
        util.test(nums, 0);
        System.out.println("##############################");
        nums = new int[] { 2, 2, 2, 2, 2 };
        util.test(nums, 8);
    }
}
