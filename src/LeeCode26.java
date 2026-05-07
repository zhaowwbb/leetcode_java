import java.util.*;

public class LeeCode26 {

    public int removeDuplicates(int[] nums) {
        if (null == nums)
            return 0;
        if (nums.length == 1)
            return 1;
        int count = 1;
        List<Integer> uniqueList = new ArrayList<>();
        int pre = nums[0];
        uniqueList.add(pre);
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            if (curr != pre) {
                uniqueList.add(curr);
                pre = curr;
                continue;
            }
        }
        // copy to nums
        for (int i = 0; i < uniqueList.size(); i++) {
            nums[i] = uniqueList.get(i);
        }
        for (int i = uniqueList.size(); i < nums.length; i++) {
            nums[i] = -1;
        }

        return uniqueList.size();
    }

    public int removeDuplicatesV2(int[] nums) {
        if (null == nums || nums.length == 0)
            return 0;
        int insertIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        return insertIndex;
    }

    public int removeDuplicatesV3(int[] nums) {
        if (null == nums || nums.length == 0)
            return 0;
        int insertIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i-1]){
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        return insertIndex;
    }

    public void test(int[] nums, int expected) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        System.out.println("Input:");
        System.out.println(Arrays.toString(copy));
        int k = 0;
        k = removeDuplicates(copy);
        System.out.printf("[V1] expected:[%d], actual:[%d] %n", expected, k);
        System.out.println(Arrays.toString(copy));

        copy = Arrays.copyOf(nums, nums.length);
        k = removeDuplicatesV2(copy);
        System.out.printf("[V2] expected:[%d], actual:[%d] %n", expected, k);
        System.out.println(Arrays.toString(copy));

        copy = Arrays.copyOf(nums, nums.length);
        k = removeDuplicatesV3(copy);
        System.out.printf("[V3] expected:[%d], actual:[%d] %n", expected, k);
        System.out.println(Arrays.toString(copy));
        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        LeeCode26 util = new LeeCode26();
        int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        util.test(nums, 5);

        nums = new int[] { 1, 1, 2 };
        util.test(nums, 2);
    }
}
