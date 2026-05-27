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

    public void test(int[] nums, int target, int expected) {
        int actual = search(nums, target);
        System.out.println("nums=" + Arrays.toString(nums));
        System.out.printf("[V1] target=%d, expect=%d, actual=%d%n", target, expected, actual);
        actual = searchV2(nums, target);
        System.out.printf("[V2] target=%d, expect=%d, actual=%d%n", target, expected, actual);
        actual = searchV3(nums, target);
        System.out.printf("[V3] target=%d, expect=%d, actual=%d%n", target, expected, actual);
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        LeeCode33 lc = new LeeCode33();
        int[] nums = new int[] { 4, 5, 6, 7, 0, 1, 2 };
        lc.test(nums, 0, 4);
        nums = new int[] { 4, 5, 6, 7, 0, 1, 2 };
        lc.test(nums, 3, -1);
        nums = new int[] { 1 };
        lc.test(nums, 0, -1);
        nums = new int[] { 3, 1 };
        lc.test(nums, 1, 1);
    }
}
