import java.util.*;

public class ReconstructCircularSymbolSequenceDemo {

    public int[] calculate(int[][] input) {
        int len = input.length;
        // Map<Integer, Integer> pairMap = new HashMap<>();
        // Map<Integer, Integer> reversedMap = new HashMap<>();
        Map<Integer, List<Integer>> pairMap = new HashMap<>();
        Map<Integer, List<Integer>> reversedMap = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int[] pair = input[i];
            int key = pair[0];
            int value = pair[1];

            if (pairMap.containsKey(key)) {
                pairMap.get(key).add(value);
            } else {
                List<Integer> adjList = new ArrayList<>();
                adjList.add(value);
                pairMap.put(key, adjList);
            }

            if (reversedMap.containsKey(value)) {
                reversedMap.get(value).add(key);
            } else {
                List<Integer> adjList = new ArrayList<>();
                adjList.add(key);
                reversedMap.put(value, adjList);
            }
        }
        // for (int i = 0; i < len; i++) {
        // int[] pair = input[i];
        // int key = pair[0];
        // int value = pair[1];
        // }
        Set<Integer> set = new HashSet<>();
        set.addAll(pairMap.keySet());
        set.addAll(reversedMap.keySet());
        boolean[] visited = new boolean[set.size()];
        Map<Integer, Boolean> visitedMap = new HashMap<>();
        for (Integer key : set) {
            visitedMap.put(key, false);
        }
        int startKey = input[0][0];

        // System.out.println("Print pairMap");
        // pairMap.forEach((k, v) -> System.out.println(" key=[" + k + "], value=" +
        // v));
        // System.out.println("Print reversedMap");
        // reversedMap.forEach((k, v) -> System.out.println(" key=" + k + ", value=" +
        // v));
        List<Integer> result = new ArrayList<>();
        tranverse(startKey, visitedMap, pairMap, reversedMap, result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public void tranverse(int key, Map<Integer, Boolean> visitedMap, Map<Integer, List<Integer>> pairMap,
            Map<Integer, List<Integer>> reversedMap, List<Integer> result) {
        if (!visitedMap.get(key)) {

            visitedMap.put(key, true);
            // System.out.println("[" + key + "]");
            result.add(key);
        } else {
            return;
        }

        List<Integer> adjList = pairMap.get(key);
        if (adjList == null) {
            // System.out.println("pairMap adjList is null");
            adjList = reversedMap.get(key);
            // System.out.println("reversedMap adjList =" + adjList);
        }
        if (null == adjList) {
            // System.out.println("No more adjList for " + key);
            return;
        }
        for (Integer newKey : adjList) {
            tranverse(newKey, visitedMap, pairMap, reversedMap, result);
        }
    }

    public List<Integer> reconstructCircleV2(int[][] symbolPairs) {
        // 1. Build Adjacency List
        // Since it's a circle, every symbol has exactly 2 neighbors
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] pair : symbolPairs) {
            adj.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            adj.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }

        int n = adj.size();
        List<Integer> result = new ArrayList<>();

        // 2. Start at any arbitrary symbol
        int current = adj.keySet().iterator().next();
        int prev = -1; // No previous symbol for the first step

        // 3. Walk the circle
        for (int i = 0; i < n; i++) {
            result.add(current);

            // Get neighbors of the current symbol
            List<Integer> neighbors = adj.get(current);

            // Pick the neighbor that isn't the one we just came from
            int next = (neighbors.get(0) == prev) ? neighbors.get(1) : neighbors.get(0);

            prev = current;
            current = next;
        }

        return result;
    }

    public List<Integer> reconstructCircleV3(int[][] symbolPairs) {
        List<Integer> result = new ArrayList<>();
        // create adj list
        int len = symbolPairs.length;
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int key = symbolPairs[i][0];
            int value = symbolPairs[i][1];
            adj.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            adj.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }
        // int adjSize = adj.size();
        int current = symbolPairs[0][0];
        int pre = -1;
        for (int i = 0; i < adj.size(); i++) {
            List<Integer> neighbors = adj.get(current);
            result.add(current);
            int next = 0;
            if (neighbors.get(0) == pre) {
                next = neighbors.get(1);
            } else {
                // pre = neighbors.get(1);
                next = neighbors.get(0);
            }
            pre = current;
            current = next;
        }
        return result;
    }

    public List<Integer> reconstructCircleV4(int[][] symbolPairs) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int len = symbolPairs.length;
        for (int i = 0; i < len; i++) {
            int key = symbolPairs[i][0];
            int value = symbolPairs[i][1];
            adj.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            adj.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        }
        int size = adj.size();
        int current = symbolPairs[0][0];
        int pre = -1;
        for (int i = 0; i < size; i++) {
            result.add(current);
            List<Integer> neighbors = adj.get(current);

            int next = (neighbors.get(0) == pre) ? neighbors.get(1) : neighbors.get(0);
            pre = current;
            current = next;
        }
        return result;
    }

    public List<Integer> reconstructCircleV5(int[][] symbolPairs) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
     
        for(int[] pair : symbolPairs){
            int key = pair[0];
            int value = pair[1];
            map.computeIfAbsent(key, k->new ArrayList<>()).add(value);
            map.computeIfAbsent(value, k-> new ArrayList<>()).add(key);
        }
        int current = symbolPairs[0][0];
        int pre = -1;
        int size = map.size();
        for(int i = 0; i < size; i++){
            result.add(current);
            List<Integer> neighbors = map.get(current);
            int next = (pre == neighbors.get(0))? neighbors.get(1): neighbors.get(0);

            pre = current;
            current = next;
        }

        return result;
    }

    public void test(int[][] input, int[] expected) {
        System.out.println("Input: ");
        for (int i = 0; i < input.length; i++) {
            System.out.print(Arrays.toString(input[i]));
        }
        System.out.println("");
        System.out.println("expected: " + Arrays.toString(expected));

        int[] result = calculate(input);
        System.out.println("[V1] Actual: " + Arrays.toString(result));

        List<Integer> resultList = null;

        resultList = reconstructCircleV2(input);
        System.out.println("[V2] Actual: " + resultList);

        resultList = reconstructCircleV3(input);
        System.out.println("[V3] Actual: " + resultList);

        resultList = reconstructCircleV4(input);
        System.out.println("[V4] Actual: " + resultList);

        resultList = reconstructCircleV5(input);
        System.out.println("[V5] Actual: " + resultList);
        System.out.println("===================================");
    }

    public static void main(String[] args) {
        ReconstructCircularSymbolSequenceDemo demo = new ReconstructCircularSymbolSequenceDemo();
        int[][] symbolPairs = { { 3, 5 }, { 1, 4 }, { 2, 4 }, { 1, 5 }, { 2, 3 } };
        int[] expected = { 3, 5, 1, 4, 2 };
        demo.test(symbolPairs, expected);
    }
}
