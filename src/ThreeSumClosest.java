import java.util.Arrays;

public class ThreeSumClosest {

    public int threeSumClosestV2(int[] nums, int target) {
        if(null == nums || nums.length < 3)return 0;
        int closestSum = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                int currentSum = nums[i] + nums[left] + nums[right];
                if(currentSum == target)return target;
                // int currentSum = target - sum;
                if(Math.abs(target - currentSum) < Math.abs(target - closestSum)){
                    closestSum = currentSum;
                }
                if(currentSum < target){
                    left++;
                }else{
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

    public void test(int[] nums, int target, int expectedResult) {
        int result = threeSumClosest(nums, target);
        System.out.printf("nums=[%s], expected:[%d], actual:[%d] %n", Arrays.toString(nums), expectedResult, result);

        result = threeSumClosestV2(nums, target);
        System.out.printf("nums=[%s], expected:[%d], actual:[%d] %n", Arrays.toString(nums), expectedResult, result);

    }

    public static void main(String[] args) {
        ThreeSumClosest util = new ThreeSumClosest();
        int[] nums = { -1, 2, 1, -4 };
        util.test(nums, 1, 2);
        nums = new int[] { 0, 0, 0 };
        util.test(nums, 1, 0);
    }
}
