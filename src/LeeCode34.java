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
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){
                //check boundary index
                int start = mid;
                int end = mid;
                while(start - 1 >=0 && nums[start - 1] == nums[start]){
                    start--;
                }
                while(end + 1 < nums.length && nums[end] == nums[end + 1]){
                    end++;
                }
                return new int[]{start, end};
            }else if(nums[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return result;
    }

    public void test(int[] nums, int target, String expected) {
        System.out.println("nums=" + Arrays.toString(nums));

        int[] result = searchRange(nums, target);
        String actual = Arrays.toString(result);
        System.out.printf("[V1] target=%d, expect=%s, actual=%s%n", target, expected, actual);

        result = searchRangeV2(nums, target);
        actual = Arrays.toString(result);
        System.out.printf("[V2] target=%d, expect=%s, actual=%s%n", target, expected, actual);
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        LeeCode34 lc = new LeeCode34();
        int[] nums = new int[] { 5, 7, 7, 8, 8, 10 };
        lc.test(nums, 8, "[3,4]");

        nums = new int[] { 5, 7, 7, 8, 8, 10 };
        lc.test(nums, 6, "[-1,-1]");

        nums = new int[] {};
        lc.test(nums, 0, "[-1,-1]");

        nums = new int[] { 1, 4 };
        lc.test(nums, 4, "[1,1]");
    }
}
