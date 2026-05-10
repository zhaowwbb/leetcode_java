import java.util.*;
import java.util.stream.Collectors;

public class StockCarRaceSimulation {

    public record CarRacer(String name, int score) {
    }

    public List<String> solution(String[][] laps) {
        List<String> result = new ArrayList<>();
        PriorityQueue<CarRacer> queue = new PriorityQueue<>((a, b) -> b.score - a.score);
        Set<String> eliminatedSet = new HashSet<>();
        for (int i = 0; i < laps.length; i++) {
            String[] lapData = laps[i];
            if (!queue.isEmpty()) {
                queue.clear();
            }
            // System.out.println("lap: " + i);
            for (int j = 0; j < laps[i].length; j++) {
                String[] pair = laps[i][j].split(" ");
                String name = pair[0];
                String score = pair[1];
                if (!eliminatedSet.contains(name)) {
                    CarRacer cr = new CarRacer(name, Integer.parseInt(score));
                    queue.add(cr);
                }
            }
            if (!queue.isEmpty()) {
                CarRacer cr = queue.poll();
                // System.out.println("Top CarRacer" + cr);
                int badScore = cr.score;

                // System.out.println("Eliminate CarRacer" + cr);
                eliminatedSet.add(cr.name);

                if (!queue.isEmpty() && queue.peek().score == badScore) {
                    // more than one will be elimnated, do name sort
                    List<String> tmpList = new ArrayList<>();
                    tmpList.add(cr.name);
                    while (!queue.isEmpty() && queue.peek().score == badScore) {
                        cr = queue.poll();
                        eliminatedSet.add(cr.name);
                        tmpList.add(cr.name);
                        System.out.println("  Also eliminate CarRacer" + cr);
                    }
                    // result.addAll(tmpList.stream().sorted().collect(Collectors.toList()));
                    Collections.sort(tmpList);
                    result.addAll(tmpList);
                } else {
                    result.add(cr.name);
                }

            }

        }
        return result;
    }

    public List<String> solutionV2(String[][] laps) {
        Map<String, Integer> bestTimes = new HashMap<>();
        Set<String> activeDrivers = new HashSet<>();
        List<String> eliminatedOrder = new ArrayList<>();

        // Initialize active drivers from the first lap
        for (String entry : laps[0]) {
            String name = entry.split(" ")[0];
            activeDrivers.add(name);
        }

        // Process each lap
        for (String[] lap : laps) {
            if (activeDrivers.isEmpty())
                break;

            // 1. Update best times for all drivers from this lap's data
            for (String entry : lap) {
                String[] parts = entry.split(" ");
                String name = parts[0];
                int time = Integer.parseInt(parts[1]);

                bestTimes.put(name, Math.min(bestTimes.getOrDefault(name, Integer.MAX_VALUE), time));
            }

            // 2. Find the slowest (max) best time among STILL ACTIVE drivers
            int slowestBest = Integer.MIN_VALUE;
            for (String driver : activeDrivers) {
                slowestBest = Math.max(slowestBest, bestTimes.get(driver));
            }

            // 3. Identify all active drivers who have this slowest time
            List<String> toEliminate = new ArrayList<>();
            for (String driver : activeDrivers) {
                if (bestTimes.get(driver) == slowestBest) {
                    toEliminate.add(driver);
                }
            }

            // 4. Sort alphabetically, add to result, and remove from active
            Collections.sort(toEliminate);
            eliminatedOrder.addAll(toEliminate);
            activeDrivers.removeAll(toEliminate);
        }

        // If any drivers are left (though logic usually clears them), add them
        // alphabetically
        if (!activeDrivers.isEmpty()) {
            List<String> leftovers = new ArrayList<>(activeDrivers);
            Collections.sort(leftovers);
            eliminatedOrder.addAll(leftovers);
        }

        return eliminatedOrder;
    }

    public List<String> solutionV3(String[][] laps) {
        List<String> result = new ArrayList<>();
        Set<String> eliminateSet = new HashSet<>();
        PriorityQueue<CarRacer> queue = new PriorityQueue<>((a, b) -> b.score - a.score);
        for (String[] lap : laps) {
            if (!queue.isEmpty()) {
                queue.clear();
            }
            for (String raceData : lap) {
                String[] pair = raceData.split(" ");
                String name = pair[0];
                int score = Integer.parseInt(pair[1]);
                if (!eliminateSet.contains(name)) {
                    CarRacer cr = new CarRacer(name, score);
                    queue.add(cr);
                }
            }
            if (!queue.isEmpty()) {
                CarRacer cr = queue.poll();
                int worsetScore = cr.score;
                if (!queue.isEmpty() && queue.peek().score == worsetScore) {
                    // more than one be eliminated
                    List<String> tmpList = new ArrayList<>();
                    tmpList.add(cr.name);
                    eliminateSet.add(cr.name);
                    while (!queue.isEmpty() && queue.peek().score == worsetScore) {
                        cr = queue.poll();
                        tmpList.add(cr.name);
                        eliminateSet.add(cr.name);
                    }
                    //sort name
                    Collections.sort(tmpList);
                    result.addAll(tmpList);
                } else {
                    result.add(cr.name);
                    eliminateSet.add(cr.name);
                }
            }
        }

        return result;
    }

    public void test(String[][] laps, String[] expectedResult) {
        System.out.println("Input:");
        for (int i = 0; i < laps.length; i++) {
            System.out.println(Arrays.toString(laps[i]));
        }
        System.out.println("expectedResult=" + Arrays.toString(expectedResult));
        List<String> result = solution(laps);
        System.out.println("[V1] actual Result=" + result);

        result = solutionV2(laps);
        System.out.println("[V2] actual Result=" + result);

        result = solutionV3(laps);
        System.out.println("[V3] actual Result=" + result);
        System.out.println("=====================");
    }

    public static void main(String[] args) {
        StockCarRaceSimulation util = new StockCarRaceSimulation();
        String[][] laps = {
                { "Harold 154", "Gina 155", "Juan 160" },
                { "Juan 152", "Gina 153", "Harold 160" },
                { "Harold 148", "Gina 150", "Juan 151" }
        };
        String[] expectedResult = { "Juan", "Harold", "Gina" };
        util.test(laps, expectedResult);

        laps = new String[][] {
                { "Gina 155", "Eddie 160", "Joy 161", "Harold 163" },
                { "Harold 151", "Gina 153", "Joy 160", "Eddie 160" },
                { "Harold 149", "Joy 150", "Gina 152", "Eddie 154" },
                { "Harold 148", "Gina 150", "Eddie 151", "Joy 155" }
        };
        expectedResult = new String[] { "Harold", "Eddie", "Joy", "Gina" };
        util.test(laps, expectedResult);
    }
}
