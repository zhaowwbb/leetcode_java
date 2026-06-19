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
            if (nums[i] != nums[i - 1]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        return insertIndex;
    }

    public static void main(String[] args) {
        LeeCode26 solver = new LeeCode26();

        // Multi-case datasets
        int[][] testInputs = {
                { 1, 1, 2 },
                { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 },
                { 1, 2, 3 },
                {}
        };

        int[] expectedLengths = { 2, 5, 3, 0 };

        int[][] expectedArrays = {
                { 1, 2 },
                { 0, 1, 2, 3, 4 },
                { 1, 2, 3 },
                {}
        };

        System.out.println("--- Running Remove Duplicates from Sorted Array Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            int[] currentInput = testInputs[i];
            int expectedLen = expectedLengths[i];
            int[] expectedArr = expectedArrays[i];

            // Capture original state for failure logs before it gets modified in-place
            String originalInputStr = Arrays.toString(currentInput);

            // The single function call (modifies currentInput in-place)
            int actualLen = solver.removeDuplicatesV3(currentInput);

            // Validation step 1: Check returned length
            boolean lengthMatches = (actualLen == expectedLen);

            // Validation step 2: Check first 'k' elements in the modified array
            boolean elementsMatch = true;
            for (int j = 0; j < actualLen; j++) {
                if (currentInput[j] != expectedArr[j]) {
                    elementsMatch = false;
                    break;
                }
            }

            // Evaluation check
            if (lengthMatches && elementsMatch) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (Input: " + originalInputStr +
                        " -> Unique Elements: " + Arrays.toString(Arrays.copyOf(currentInput, actualLen)) + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: " + originalInputStr +
                        "\n  Expected Length: " + expectedLen + " | Got: " + actualLen +
                        "\n  Expected Elements: " + Arrays.toString(expectedArr) +
                        " | Got First " + actualLen + " Elements: "
                        + Arrays.toString(Arrays.copyOf(currentInput, actualLen)));
            }
        }
    }
}
