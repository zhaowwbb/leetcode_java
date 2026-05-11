public class Knapsack {

    /**
     * @param capacity Max weight the backpack can hold
     * @param weights  Array of item weights
     * @param values   Array of item values
     * @param n        Number of items
     */
    public static int solveKnapsack(int capacity, int[] weights, int[] values, int n) {
        // Create a DP table where dp[i][w] is the max value using
        // first i items and weight limit w.
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the table in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // Base case: No items or no capacity = 0 value
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }
                // If the item's weight is less than the current capacity 'w'
                else if (weights[i - 1] <= w) {
                    // Max of: (Item value + value of remaining space) OR (Value without this item)
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]],
                            dp[i - 1][w]);
                } else {
                    // Item is too heavy, skip it
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    public static int solveKnapsackV2(int capacity, int[] weights, int[] values, int n) {
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    int left = values[i - 1] + dp[i - 1][w - weights[i - 1]];
                    int right = dp[i - 1][w];
                    dp[i][w] = Math.max(left, right);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    public static int solveKnapsackV3(int capacity, int[] weights, int[] values, int n) {
        int[][] dp = new int[n + 1][capacity + 1];
        for(int i = 0; i <= n; i++){
            for(int w = 0; w <= capacity; w++){
                if(i == 0 || w == 0){
                    dp[i][w] = 0;
                }else if(weights[i - 1] <= w){
                    int first = values[i-1] + dp[i-1][w - weights[i-1]];
                    int second = dp[i-1][w];
                    dp[i][w] = Math.max(first, second);
                }else{
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] values = { 60, 100, 120 };
        int[] weights = { 10, 20, 30 };
        int capacity = 50;
        int n = values.length;

        int maxValue = solveKnapsack(capacity, weights, values, n);
        System.out.println("[V1]Maximum value in backpack: " + maxValue);
        maxValue = solveKnapsackV2(capacity, weights, values, n);
        System.out.println("[V2]Maximum value in backpack: " + maxValue);
        maxValue = solveKnapsackV3(capacity, weights, values, n);
        System.out.println("[V3]Maximum value in backpack: " + maxValue);
    }
}