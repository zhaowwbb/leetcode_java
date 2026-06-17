import java.util.Arrays;

public class LeetCode16 {

    public int threeSumClosestV2(int[] nums, int target) {
        if (null == nums || nums.length < 3)
            return 0;
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                if (currentSum == target)
                    return target;
                // int currentSum = target - sum;
                if (Math.abs(target - currentSum) < Math.abs(target - closestSum)) {
                    closestSum = currentSum;
                }
                if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }

            }
        }
        return closestSum;
    }

    public int threeSumClosest(int[] nums, int target) {
        if (null == nums || nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;
        int preDistance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int sum1 = nums[i] + nums[right] + nums[right];
                int distance = sum - target;
                int sum1Distance = sum1 - target;
                if (Math.abs(distance) < Math.abs(preDistance)) {
                    preDistance = distance;
                    result = sum;

                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (Math.abs(sum1Distance) < Math.abs(preDistance)) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return result;
    }

    public int threeSumClosestV3(int[] nums, int target) {
        int sum = 0;
        if (null == nums || nums.length < 3)
            return sum;
        int n = nums.length;
        // sum = Integer.MAX_VALUE;
        int preDistance = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            while (left < right) {
                int distance = Math.abs(target - (nums[i] + nums[left] + nums[right]));
                if (distance < preDistance) {
                    sum = nums[i] + nums[left] + nums[right];
                    preDistance = distance;
                    right--;
                } else if (distance > preDistance) {
                    left++;
                } else {
                    // hit
                    break;
                }
            }
        }

        return sum;
    }

    public int threeSumClosestV4(int[] nums, int target) {
        if (null == nums || nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - currentSum) < Math.abs(target - closestSum)) {
                    closestSum = currentSum;
                }
                if (currentSum == target) {
                    return currentSum;
                } else if (currentSum > target) {
                    right--;
                } else if (currentSum < target) {
                    left++;
                }
            }

        }
        return closestSum;
    }

    public int threeSumClosestV5(int[] nums, int target) {
        if (null == nums || nums.length < 3)
            return 0;
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                if (currentSum == target)
                    return currentSum;
                if (Math.abs(target - currentSum) < Math.abs(target - closestSum)) {
                    closestSum = currentSum;
                }
                if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closestSum;
    }

    public int threeSumClosestV6(int[] nums, int target) {
        int closeSum = Integer.MAX_VALUE;
        int preDistance = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int distance = 0;
                if ((sum > 0 && target > 0) || (sum < 0 && target < 0)) {
                    distance = Math.abs(sum - target);
                } else {
                    distance = Math.abs(sum) + Math.abs(target);
                }
                if (distance < preDistance) {
                    closeSum = sum;
                    preDistance = distance;
                }
                // closeSum = Math.min(closeSum, distance);

                if (sum == target) {
                    return target;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return closeSum;
    }

    public int threeSumClosestV7(int[] nums, int target) {
        int closeSum = nums[0] + nums[1] + nums[2];
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            // while (i > 0 && i < len - 2 && nums[i] == nums[i - 1])
            //     continue;
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closeSum - target)) {
                    closeSum = sum;
                }
                if (sum == target) {
                    return sum;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return closeSum;
    }

    public void test(int[] nums, int target, int expectedResult) {
        int result = threeSumClosest(nums, target);
        System.out.printf("[V1], nums=[%s], expected:[%d], actual:[%d] %n", Arrays.toString(nums), expectedResult,
                result);

        result = threeSumClosestV2(nums, target);
        System.out.printf("[V2], nums=[%s], expected:[%d], actual:[%d] %n", Arrays.toString(nums), expectedResult,
                result);

        result = threeSumClosestV3(nums, target);
        System.out.printf("[V3], nums=[%s], expected:[%d], actual:[%d] %n", Arrays.toString(nums), expectedResult,
                result);

        result = threeSumClosestV4(nums, target);
        System.out.printf("[V4], nums=[%s], expected:[%d], actual:[%d] %n", Arrays.toString(nums), expectedResult,
                result);

        result = threeSumClosestV5(nums, target);
        System.out.printf("[V5], nums=[%s], expected:[%d], actual:[%d] %n", Arrays.toString(nums), expectedResult,
                result);

        result = threeSumClosestV6(nums, target);
        System.out.printf("[V6], nums=[%s], expected:[%d], actual:[%d] %n", Arrays.toString(nums), expectedResult,
                result);

        result = threeSumClosestV7(nums, target);
        System.out.printf("[V7], nums=[%s], expected:[%d], actual:[%d] %n", Arrays.toString(nums), expectedResult,
                result);
        System.out.println("############################");

    }

    public static void main(String[] args) {
        LeetCode16 util = new LeetCode16();
        int[] nums = { -1, 2, 1, -4 };
        util.test(nums, 1, 2);
        nums = new int[] { 0, 0, 0 };
        util.test(nums, 1, 0);
        nums = new int[] { 1,1,1,1 };
        util.test(nums, 1, 3);

    }
}
