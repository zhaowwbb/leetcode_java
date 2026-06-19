import java.util.Arrays;

public class LeetCode11 {

    public int maxArea(int[] height) {
        if (null == height || height.length < 2)
            return 0;
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = 1; j < height.length; j++) {
                max = Math.max(max, (j - i) * (Math.min(height[i], height[j])));
            }
        }

        return max;
    }

    public int maxAreaV2(int[] height) {
        if (null == height || height.length < 2)
            return 0;
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int width = right - left;
            int area = Math.min(height[left], height[right]) * width;
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public int maxAreaV3(int[] height) {
        if (null == height || height.length == 1)
            return 0;
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                max = Integer.max(max, (right - left) * height[left]);
                left++;
            } else {
                max = Integer.max(max, (right - left) * height[right]);
                right--;
            }
        }
        return max;
    }

    public int maxAreaV4(int[] height) {
        int maxArea = 0;
        if (null == height || height.length < 2)
            return maxArea;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int distance = right - left;
            int area = 0;
            if (height[left] < height[right]) {
                area = distance * height[left];
                left++;
            } else {
                area = distance * height[right];
                right--;
            }
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LeetCode11 solver = new LeetCode11();

        // Batching multiple test cases into a single dataset
        int[][] testInputs = {
                { 1, 8, 6, 2, 5, 4, 8, 3, 7 }, // Expected: 49
                { 1, 1 }, // Expected: 1
                { 4, 3, 2, 1, 4 }, // Expected: 16
                { 1, 2, 1 } // Expected: 2
        };

        int[] expectedOutputs = { 49, 1, 16, 2 };

        System.out.println("--- Running LeetCode Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testInputs.length; i++) {
            int[] currentCase = testInputs[i];
            int expected = expectedOutputs[i];

            // The single function call location
            int actual = solver.maxAreaV4(currentCase);

            // Verification logic
            if (actual == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (Output: " + actual + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Expected " + expected + ", but got " + actual);
            }
        }
    }
}
