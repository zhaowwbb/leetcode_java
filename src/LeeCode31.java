import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LeeCode31 {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return;
        }

        int[] copyAsc = nums.clone();
        // int[] copyDesc = nums.clone();
        Arrays.sort(copyAsc);
        boolean isBiggest = true;
        for (int i = 0; i < len; i++) {
            if (copyAsc[len - i - 1] != nums[i]) {
                isBiggest = false;
                break;
            }
        }
        if (isBiggest) {
            for (int i = 0; i < len; i++) {
                nums[i] = copyAsc[i];
            }
            return;
        }
        int srcIndex = -1;
        int dstIndex = -1;
        for (int i = len - 1; i >= 0; i--) {
            int curr = nums[i];
            // dstIndex = i;
            for (int j = i - i; j >= 0; j--) {
                if (nums[j] < curr) {
                    dstIndex = i;
                    srcIndex = j;
                    break;
                }
            }
            if (dstIndex >= 0) {
                break;
            }
        }
        if (srcIndex >= 0 && dstIndex >= 0) {
            int srcVal = nums[srcIndex];
            int dstVal = nums[dstIndex];
            for (int i = dstIndex; i > srcIndex; i--) {
                nums[i] = nums[i - 1];
            }
            nums[srcIndex] = dstVal;
        }

        // int swapIndex = -1;
        // int last = nums[len - 1];
        // for(int i = len - 1; i >= 0; i--){
        // int curr = nums[i];
        // if(curr < last){
        // swapIndex = i;
        // break;
        // }
        // }
        // if(swapIndex < 0){return;}
        // for(int i = len - 1; i > swapIndex; i--){
        // nums[i] = nums[i-1];
        // }
        // nums[swapIndex] = last;

    }

    public void nextPermutationV2(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;

        // Step 1: Find the first decreasing element from the right
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If pivot is found, find the element to swap with
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: Reverse the elements to the right of the pivot
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void test(int[] nums, String expected) {
        int[] copy = nums.clone();
        System.out.println("Input:" + Arrays.toString(copy));

        nextPermutation(copy);
        System.out.println("[V1]expected:" + expected + ",Result:" + Arrays.toString(copy));
        // System.out.println("[V1]expected:" + expected);
        // System.out.println("[V1]Result :" + Arrays.toString(copy));

        copy = nums.clone();
        nextPermutationV2(copy);
        System.out.println("[V2]expected:" + expected + ",Result:" + Arrays.toString(copy));
        // System.out.println("[V2]Result :" + Arrays.toString(copy));
        System.out.println("====================");
    }

    public static void main(String[] args) {
        LeeCode31 lc = new LeeCode31();

        int[] nums = new int[] { 1, 2, 3 };
        lc.test(nums, "[1,3,2]");

        nums = new int[] { 3, 2, 1 };
        lc.test(nums, "[1,2,3]");

        nums = new int[] { 1, 1, 5 };
        lc.test(nums, "[1,5,1]");

        nums = new int[] { 1, 3, 2 };
        lc.test(nums, "[2,1,3]");

        nums = new int[] { 2, 3, 1 };
        lc.test(nums, "[3,1,2]");
    }
}
