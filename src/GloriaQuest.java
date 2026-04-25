import java.util.HashSet;
import java.util.Set;

public class GloriaQuest {

    public static int calculateMaxSum(int[] arrayA, int[] arrayB, int[] arrayC) {
        int n = arrayA.length;
        int maxB = 0;
        int maxC = 0;

        // Tracks visited states to detect loops: "array_index"
        Set<String> visited = new HashSet<>();

        int currentIndex = 0;
        char currentArray = 'A';
        int step = 0; // 0: A->B, 1: B->A, 2: A->C, 3: C->A

        while (true) {
            // 1. Check for loop
            String state = currentArray + "_" + currentIndex;
            if (visited.contains(state)) {
                break;
            }
            visited.add(state);

            // 2. Track maximums in B and C
            if (currentArray == 'B') {
                maxB = Math.max(maxB, arrayB[currentIndex]);
            } else if (currentArray == 'C') {
                maxC = Math.max(maxC, arrayC[currentIndex]);
            }

            // 3. Determine next hop
            int nextIndex;
            char nextArray;

            // Get value at current position to use as next index
            int value = 0;
            if (currentArray == 'A')
                value = arrayA[currentIndex];
            else if (currentArray == 'B')
                value = arrayB[currentIndex];
            else if (currentArray == 'C')
                value = arrayC[currentIndex];

            nextIndex = value;

            // Pattern Logic: A -> B -> A -> C (repeats)
            if (currentArray == 'A') {
                if (step % 4 == 0) {
                    nextArray = 'B';
                } else { // step % 4 == 2
                    nextArray = 'C';
                }
            } else {
                // If currently at B or C, always return to A
                nextArray = 'A';
            }

            // 4. Check for Bounds Error
            if (nextIndex < 0 || nextIndex >= n) {
                break;
            }

            // Update for next iteration
            currentIndex = nextIndex;
            currentArray = nextArray;
            step++;
        }

        return maxB + maxC;
    }

    public static int calculateMaxSumV2(int[] arrayA, int[] arrayB, int[] arrayC) {
        int maxB = 0;
        int maxC = 0;
        Set<String> visitedSet = new HashSet<>();

        String currentArray = "A";
        String nextArray = "B";
        int currentIndex = 0;
        int nextIndex = 0;
        int maxSum = 0;
        int steps = 0;
        while (true) {
            int value = 0;
            String key = currentArray + "_" + currentIndex;
            if (visitedSet.contains(key)) {
                break;
            }
            visitedSet.add(key);
            if (currentArray.equals("B")) {
                maxB = Math.max(arrayB[currentIndex], maxB);
                value = arrayB[currentIndex];
            }
            if (currentArray.equals("C")) {
                maxC = Math.max(arrayC[currentIndex], maxC);
                value = arrayC[currentIndex];
            }
            if (currentArray.equals("A")) {
                if (steps % 4 == 0) {
                    nextArray = "B";

                } else {
                    nextArray = "C";
                }
                value = arrayA[currentIndex];
            } else {
                nextArray = "A";
            }
            if (value < 0 || value >= arrayA.length)
                break;
            currentArray = nextArray;
            currentIndex = value;
            steps++;
        }

        maxSum = maxB + maxC;
        return maxSum;
    }

    public static int calculateMaxSumV3(int[] arrayA, int[] arrayB, int[] arrayC) {
        int n = arrayA.length;
        int maxB = 0;
        int maxC = 0;
        String currentArray = "A";
        int currentIndex = 0;
        int maxSum = 0;
        int step = 0;
        Set<String> stateSet = new HashSet<>();
        while (true) {
            String state = currentArray + "_" + currentIndex;
            if (stateSet.contains(state)) {
                break;
            }
            stateSet.add(state);
            String nextArray = "B";
            int nextIndex = 0;
            if (currentArray.equals("A")) {
                nextIndex = arrayA[currentIndex];
                if (step % 4 == 0) {
                    nextArray = "B";
                } else {
                    nextArray = "C";
                }
            } else if (currentArray.equals("B")) {
                maxB = Math.max(arrayB[currentIndex], maxB);
                nextArray = "A";
                nextIndex = arrayB[currentIndex];
            } else {
                maxC = Math.max(arrayC[currentIndex], maxB);
                nextArray = "A";
                nextIndex = arrayC[currentIndex];
            }
            if (nextIndex < 0 || nextIndex >= n)
                break;
            currentArray = nextArray;
            currentIndex = nextIndex;
            step++;
        }
        maxSum = maxB + maxC;
        return maxSum;
    }

    public static void test() {
        int[] arrayA = { 2, 1, 3, 0 };
        int[] arrayB = { 1, 3, 2, 4 };
        int[] arrayC = { 4, 2, 5, 1 };
        // int[] arrayC = {4, 2, 5, 3};

        System.out.println("[V1]Result: " + calculateMaxSum(arrayA, arrayB, arrayC)); // Output: 7
        System.out.println("[V2]Result: " + calculateMaxSumV2(arrayA, arrayB, arrayC)); // Output: 7
        System.out.println("[V3]Result: " + calculateMaxSumV3(arrayA, arrayB, arrayC)); // Output: 7
    }

    public static void main(String[] args) {
        test();
    }
}
