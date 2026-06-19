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
        if (null == nums)
            return 0;
        int deleteIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[deleteIndex] = nums[i];
                deleteIndex++;
            }
        }
        return deleteIndex;
    }

    public static void main(String[] args) {
        LeeCode27 solver = new LeeCode27();
        // Multi-case datasets
        int[][] testInputs = {
                { 3, 2, 2, 3 },
                { 0, 1, 2, 2, 3, 0, 4, 2 },
                { 1 },
                {}
        };
        int[] testVals = { 3, 2, 1, 7 };

        int[] expectedLengths = { 2, 5, 0, 0 };
        int[][] expectedArrays = {
                { 2, 2 },
                { 0, 1, 3, 0, 4 },
                {},
                {}
        };

        System.out.println("--- Running Remove Element Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            int[] currentInput = testInputs[i];
            int valToRemove = testVals[i];
            int expectedLen = expectedLengths[i];
            int[] expectedArr = expectedArrays[i];

            // Capture original state for clear diagnostic logs before in-place mutations
            String originalInputStr = Arrays.toString(currentInput);

            // The single function call
            int actualLen = solver.removeElementV2(currentInput, valToRemove);

            // Verification Step 1: Check length matches
            boolean lengthMatches = (actualLen == expectedLen);

            // Verification Step 2: Check elements up to index actualLen (ignoring order or
            // remnants)
            // Note: Since LeetCode accepts elements in any order, we sort the sub-segments
            // to check them reliably
            int[] actualSubArray = Arrays.copyOf(currentInput, actualLen);
            int[] expectedSubArray = Arrays.copyOf(expectedArr, expectedLen);
            Arrays.sort(actualSubArray);
            Arrays.sort(expectedSubArray);

            boolean elementsMatch = Arrays.equals(actualSubArray, expectedSubArray);

            // Evaluation check
            if (lengthMatches && elementsMatch) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (Input: " + originalInputStr +
                        ", remove: " + valToRemove + " -> Elements: "
                        + Arrays.toString(Arrays.copyOf(currentInput, actualLen)) + ")");
            } else {
                System.err.println(
                        "Test Case " + (i + 1) + ": FAILED! Input: " + originalInputStr + " | target: " + valToRemove +
                                "\n  Expected Length: " + expectedLen + " | Got: " + actualLen +
                                "\n  Expected Elements: " + Arrays.toString(expectedArr) +
                                " | Got First " + actualLen + " Elements: "
                                + Arrays.toString(Arrays.copyOf(currentInput, actualLen)));
            }
        }
    }
}
