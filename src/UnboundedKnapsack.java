import java.util.Arrays;

public class UnboundedKnapsack {

    /**
     * Finds the maximum value that can be obtained given a total capacity,
     * weights, and values of available items (unbounded item usage).
     *
     * @param capacity Total weight capacity of the knapsack.
     * @param weights  Array containing the weights of the items.
     * @param values   Array containing the values of the items.
     * @return The maximum value achievable.
     * @throws IllegalArgumentException if inputs are invalid or mismatched.
     */
    public static int getMaxKnapsackValue(int capacity, int[] weights, int[] values) {
        // Validation guard clauses
        if (weights == null || values == null || weights.length != values.length) {
            throw new IllegalArgumentException("Weights and values arrays must be non-null and match in length.");
        }
        if (capacity < 0) {
            throw new IllegalArgumentException("Knapsack capacity cannot be negative.");
        }
        if (capacity == 0 || weights.length == 0) {
            return 0;
        }

        int n = weights.length;

        // dp[i] will store the maximum value achievable with weight capacity 'i'
        int[] dp = new int[capacity + 1];

        // Iterate through all capacities from 1 to total capacity
        for (int w = 1; w <= capacity; w++) {
            // Check every item for the current capacity
            for (int i = 0; i < n; i++) {
                if (weights[i] <= w) {
                    // Update the maximum value for capacity 'w' by either:
                    // 1. Keeping the current value at dp[w]
                    // 2. Taking the item 'i' and looking up the optimal value for the remaining
                    // capacity (w - weights[i])
                    dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
                }
            }
        }

        return dp[capacity];
    }

    public static int getMaxKnapsackValueV2(int capacity, int[] weights, int[] values) {
        int n = values.length;
        int[] dp = new int[capacity + 1];
        for (int w = 0; w <= capacity; w++) {
            for (int i = 0; i < n; i++) {
                if (weights[i] <= w) {
                    dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
                }
            }
        }

        return dp[capacity];
    }

    public static int getMaxKnapsackValueV3(int capacity, int[] weights, int[] values) {
        int[] dp = new int[capacity + 1];
        for (int w = 1; w <= capacity; w++) {
            for (int i = 0; i < values.length; i++) {
                if (weights[i] <= w) {
                    dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
                }
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        // Test Case
        int capacity = 8;
        int[] values = { 10, 40, 50, 70 };
        int[] weights = { 1, 3, 4, 5 };

        System.out.println("Executing Unbounded Knapsack Optimization...");
        System.out.println("Weights:  " + Arrays.toString(weights));
        System.out.println("Values:   " + Arrays.toString(values));
        System.out.println("Capacity: " + capacity);

        int maxVal = getMaxKnapsackValue(capacity, weights, values);

        System.out.println("------------------------------------------");
        System.out.println("[V1]Maximum Value Obtainable: " + maxVal); // Expected Output: 110 (Taking weight 3 and
                                                                       // weight 5 -> 40 + 70)

        maxVal = getMaxKnapsackValueV2(capacity, weights, values);
        System.out.println("[V2]Maximum Value Obtainable: " + maxVal);
        maxVal = getMaxKnapsackValueV3(capacity, weights, values);
        System.out.println("[V3]Maximum Value Obtainable: " + maxVal);
    }
}