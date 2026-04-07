import java.util.Arrays;
import java.util.List;

public class TwoSum {

/**
     * Determines the array index (0, 1, or 2) that holds the maximum value.
     * * @param p1 The integer at position 0
     * @param p2 The integer at position 1
     * @param p3 The integer at position 2
     * @return The index (0, 1, or 2) of the maximum value. 
     * If there is a tie, it returns the first occurrence.
     */
    public int findMaxPosition(int p1, int p2, int p3) {
        // We initialize the max value as p1 (position 0)
        int maxVal = p1;
        int maxPos = 0;

        // Compare with p2 (position 1)
        if (p2 > maxVal) {
            maxVal = p2;
            maxPos = 1;
        }

        // Compare with p3 (position 2)
        if (p3 > maxVal) {
            maxPos = 2;
        }

        return maxPos;
    }    

/**
     * Counts occurrences where a value is significantly different from its neighbors.
     * @param data List of Double numbers
     * @return Number of occurrences meeting the criteria
     */
    public int countPatternOccurrences(List<Double> data) {
        if (data == null || data.size() < 3) {
            return 0; // Need at least 3 elements to have a middle value
        }

        int count = 0;
        double threshold = 5.0;

        // Start at 1, end before the last element
        for (int i = 1; i < data.size() - 1; i++) {
            double prev = data.get(i - 1);
            double curr = data.get(i);
            double next = data.get(i + 1);

            // Condition 1: Neighbors are all greater than current + 5
            boolean isLocalValley = (prev > curr + threshold) && (next > curr + threshold);

            // Condition 2: Neighbors are all less than current - 5
            boolean isLocalPeak = (prev < curr - threshold) && (next < curr - threshold);

            if (isLocalValley || isLocalPeak) {
                count++;
            }
        }

        return count;
    }

    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length -1; i++){
            for(int j = 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
        
    }

    public int[] twoSumOptimized(int[] nums, int target) {
        java.util.Map<Integer, Integer> numToIndex = new java.util.HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numToIndex.containsKey(complement)) {
                return new int[]{numToIndex.get(complement), i};
            }
            numToIndex.put(nums[i], i);
        }
        return new int[]{};
    }

    public int[] twoSum2(int[] nums, int target){
        java.util.Map<Integer, Integer> numberToIndex = new java.util.HashMap<>();
        for(int i =0; i < nums.length; i++){
            int complement = target - nums[i];
            if(numberToIndex.containsKey(complement)){
                return new int[]{numberToIndex.get(complement), i};

            }
            numberToIndex.put(nums[i], i);
        }
        return new int[]{};

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        TwoSum utils = new TwoSum();
        
        // Example: p2 is the largest (10), so it should return 1
        int result = utils.findMaxPosition(5, 10, 3);
        System.out.println("The position of the maximum value is: " + result);

// Test Case 1: Mixed patterns
        // Index 1 (curr=2.0): 10 > 7 and 15 > 7 (Yes)
        // Index 2 (curr=15.0): 2 < 10 and 4 < 10 (Yes)
        List<Double> test1 = Arrays.asList(10.0, 2.0, 15.0, 4.0, 20.0); 
        System.out.println("Test 1 Result (Expected 2): " + utils.countPatternOccurrences(test1));

        // Test Case 2: No patterns (differences are exactly 5 or less)
        List<Double> test2 = Arrays.asList(10.0, 5.0, 10.0); 
        System.out.println("Test 2 Result (Expected 0): " + utils.countPatternOccurrences(test2));

        // Test Case 3: Empty or small list
        List<Double> test3 = Arrays.asList(1.0, 10.0);
        System.out.println("Test 3 Result (Expected 0): " + utils.countPatternOccurrences(test3));        


// Test Case 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = utils.twoSum(nums1, target1);
        result1 = utils.twoSumOptimized(nums1, target1); // Testing the optimized version
        result1 = utils.twoSum2(nums1, target1);
        System.out.println("Result 1: [" + result1[0] + ", " + result1[1] + "]"); // Expected [0, 1]

        // Test Case 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = utils.twoSum(nums2, target2);
        result2 = utils.twoSumOptimized(nums2, target2); // Testing the optimized version
        result2 = utils.twoSum2(nums2, target2);
        System.out.println("Result 2: [" + result2[0] + ", " + result2[1] + "]"); // Expected [1, 2]


    }
}
