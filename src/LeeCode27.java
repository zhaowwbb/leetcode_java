import java.util.*;

public class LeeCode27 {

    public int removeElement(int[] nums, int val) {
        if (null == nums)
            return 0;
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                newList.add(nums[i]);
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            nums[i] = newList.get(i);
        }

        return newList.size();
    }

    public int removeElementV2(int[] nums, int val) {
        if(null == nums)return 0;
        int deleteIndex = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[deleteIndex] = nums[i];
                deleteIndex++;
            }
        }
        return deleteIndex;
    }

    public void test(int[] nums, int val, int expected) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        System.out.println("Input: val=" + val);
        System.out.println(Arrays.toString(copy));
        int result = 0;
        result = removeElement(copy, val);
        System.out.printf("[V1] expected:[%d], actual:[%d] %n", expected, result);
        System.out.println(Arrays.toString(copy));

        copy = Arrays.copyOf(nums, nums.length);
        result = removeElementV2(copy, val);
        System.out.printf("[V2] expected:[%d], actual:[%d] %n", expected, result);
        System.out.println(Arrays.toString(copy));
        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        LeeCode27 lc = new LeeCode27();

        int[] nums = { 3, 2, 2, 3 };
        int val = 3;
        lc.test(nums, val, 2);

        nums = new int[] { 0, 1, 2, 2, 3, 0, 4, 2 };
        val = 2;
        lc.test(nums, val, 5);
    }
}
