import java.util.Arrays;

public class ContainerWithMostWater {

    public int maxAreaV2(int[] height) {
        if(null == height || height.length < 2)return 0;
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while(left < right){
            int width = right - left;
            int area = Math.min(height[left] , height[right]) * width;
            maxArea = Math.max(maxArea, area);
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }

        return maxArea;
    }

    public int maxArea(int[] height) {
        if(null == height || height.length < 2)return 0;
        int max = 0;
        for(int i = 0; i < height.length - 1; i++){
            for(int j = 1; j < height.length; j++){
                max = Math.max(max, (j - i) * (Math.min(height[i], height[j])));
            }
        }

        return max;
    }
    
    public void test(int[] height, int expectedResult){
        int result = 0;
        result = maxArea(height);
        System.out.printf("height=[%s], expected:[%d], actual:[%d]%n", Arrays.toString(height), expectedResult, result);
        result = maxAreaV2(height);
        System.out.printf("height=[%s], expected:[%d], actual:[%d]%n", Arrays.toString(height), expectedResult, result);
    }

    public static void main(String[] args) {
        ContainerWithMostWater util = new ContainerWithMostWater();
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        util.test(height, 49);
        height = new int[]{1,1};
        util.test(height, 1);
    }
}
