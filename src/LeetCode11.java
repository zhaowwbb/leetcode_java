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

    public void test(int[] height, int expectedResult) {
        int result = 0;
        result = maxArea(height);
        System.out.printf("[V1] height=[%s], expected:[%d], actual:[%d]%n", Arrays.toString(height), expectedResult,
                result);
        result = maxAreaV2(height);
        System.out.printf("[V2] height=[%s], expected:[%d], actual:[%d]%n", Arrays.toString(height), expectedResult,
                result);
        result = maxAreaV3(height);
        System.out.printf("[V3] height=[%s], expected:[%d], actual:[%d]%n", Arrays.toString(height), expectedResult,
                result);
        result = maxAreaV4(height);
        System.out.printf("[V4] height=[%s], expected:[%d], actual:[%d]%n", Arrays.toString(height), expectedResult,
                result);
        System.out.println("##########################");
    }

    public static void main(String[] args) {
        LeetCode11 util = new LeetCode11();
        int[] height = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        util.test(height, 49);
        height = new int[] { 1, 1 };
        util.test(height, 1);
    }
}
