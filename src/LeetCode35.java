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

    public void test(int[] nums, int target, int expected) {
        System.out.println("nums=" + Arrays.toString(nums));
        int actual = searchInsert(nums, target);
        System.out.printf("[V1] target=%d, expect=%d, actual=%d%n", target, expected, actual);
        actual = searchInsertV2(nums, target);
        System.out.printf("[V2] target=%d, expect=%d, actual=%d%n", target, expected, actual);
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        LeetCode35 lc = new LeetCode35();
        int[] nums = new int[] { 1, 3, 5, 6 };
        lc.test(nums, 5, 2);
        lc.test(nums, 2, 1);
        lc.test(nums, 7, 4);

    }
}
