import java.util.*;

public class TopStockSums {

    public static void findTop3BySum(String[] names, float[][] prices) {
        List<StockResult> results = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            float top3Sum = calculateTop3Sum(prices[i]);
            results.add(new StockResult(names[i], top3Sum));
        }

        // Sort stocks by their calculated sum in descending order
        results.sort((a, b) -> Float.compare(b.totalSum, a.totalSum));

        System.out.println("Top 3 Stocks by Sum of their Top 3 Prices:");
        for (int i = 0; i < Math.min(3, results.size()); i++) {
            StockResult r = results.get(i);
            System.out.printf("%d. %s - Total Sum: %.2f\n", (i + 1), r.name, r.totalSum);
        }
    }

    private static float calculateTop3Sum(float[] stockPrices) {
        // Min-heap to keep track of the 3 largest prices for a single stock
        PriorityQueue<Float> minHeap = new PriorityQueue<>();

        for (float price : stockPrices) {
            minHeap.offer(price);
            if (minHeap.size() > 3) {
                minHeap.poll(); // Remove the smallest of the 4 elements
            }
        }

        float sum = 0;
        while (!minHeap.isEmpty()) {
            sum += minHeap.poll();
        }
        return sum;
    }

    static class StockResult {
        String name;
        float totalSum;

        StockResult(String name, float totalSum) {
            this.name = name;
            this.totalSum = totalSum;
        }
    }

    static class StockPriceV2 {
        String name;
        float totalSum;

        StockPriceV2(String name, float totalSum) {
            this.name = name;
            this.totalSum = totalSum;
        }

        public float getSum() {
            return this.totalSum;
        }
    }

    public static void findTop3BySumV2(String[] names, float[][] prices) {
        // PriorityQueue<StockPriceV2> stocks = new
        // PriorityQueue<>(Comparator.comparing(s->s.totalSum));

        List<StockPriceV2> stocks = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            float sumPrice = 0;
            PriorityQueue<Float> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (float f : prices[i]) {
                pq.offer(f);
            }

            for (int k = 0; k < 3; k++) {
                Float f = pq.poll();
                sumPrice += f;
                // System.out.println("stock=" + names[i] + "f=" + f + ",sumPrice=" + sumPrice);
            }

            StockPriceV2 sp = new StockPriceV2(names[i], sumPrice);
            stocks.add(sp);
            // if (stocks.size() > 3)
            // stocks.poll();

        }
        System.out.println("stocks.size()=" + stocks.size());
        stocks.sort((s1, s2) -> Float.compare(s2.totalSum, s1.totalSum));
        int i = 0;
        for (int j = 0; j <= 2; j++) {
            StockPriceV2 r = stocks.get(j);
            // StockPriceV2 r = stocks.poll();
            System.out.printf("%d. %s - Total Sum: %.2f\n", (i + 1), r.name, r.totalSum);
            i++;
        }
    }

    static class StockPriceV3 {
        String name;
        float totalSum;

        StockPriceV3(String name, float totalSum) {
            this.name = name;
            this.totalSum = totalSum;
        }
    }

    public static void findTop3BySumV3(String[] names, float[][] prices) {
        List<StockPriceV3> result = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            float sum = 0;
            PriorityQueue<Float> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (float f : prices[i]) {
                pq.offer(f);
                if (pq.size() > 3)
                    pq.poll();
            }
            while (!pq.isEmpty()) {
                sum += pq.poll();
            }
            StockPriceV3 sp = new StockPriceV3(names[i], sum);
            result.add(sp);
        }
        result.sort((s1, s2) -> Float.compare(s2.totalSum, s1.totalSum));
        for (int i = 0; i < Math.min(result.size(), 3); i++) {
            StockPriceV3 r = result.get(i);
            // StockPriceV2 r = stocks.poll();
            System.out.printf("%d. %s - Total Sum: %.2f\n", (i + 1), r.name, r.totalSum);
        }
    }

    static class StockPriceV4 {
        String name;
        float totalSum;

        StockPriceV4(String name, float totalSum) {
            this.name = name;
            this.totalSum = totalSum;
        }
    }

    public static float calculateTop3SumV4(float[] price) {
        PriorityQueue<Float> minHeap = new PriorityQueue<>();
        for (float f : price) {
            minHeap.offer(f);
            if (minHeap.size() > 3) {
                minHeap.poll();
            }
        }
        float sum = 0;
        while (!minHeap.isEmpty()) {
            sum += minHeap.poll();
        }
        return sum;
    }

    public static void findTop3BySumV4(String[] names, float[][] prices) {
        List<StockPriceV4> result = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            float sum = calculateTop3SumV4(prices[i]);
            StockPriceV4 sp = new StockPriceV4(names[i], sum);
            result.add(sp);
        }
        result.sort((s1, s2) -> Float.compare(s2.totalSum, s1.totalSum));
        for (int i = 0; i < Math.min(3, result.size()); i++) {
            StockPriceV4 r = result.get(i);
            System.out.printf("%d. %s - Total Sum: %.2f\n", (i + 1), r.name, r.totalSum);
        }
    }

    public static void main(String[] args) {
        String[] stocks = { "AAPL", "GOOGL", "MSFT", "AMZN", "TSLA" };

        // Example: Prices for each stock (can be different lengths)
        float[][] prices = {
                { 150.0f, 160.0f, 140.0f, 155.0f }, // AAPL: top 3 are 160, 155, 150 (Sum: 465)
                { 2800.0f, 2900.0f, 2700.0f, 2850.0f }, // GOOGL: top 3 are 2900, 2850, 2800 (Sum: 8550)
                { 300.0f, 310.0f, 305.0f, 290.0f }, // MSFT: top 3 are 310, 305, 300 (Sum: 915)
                { 3400.0f, 3300.0f, 3500.0f, 3450.0f }, // AMZN: top 3 are 3500, 3450, 3400 (Sum: 10350)
                { 700.0f, 800.0f, 750.0f, 720.0f } // TSLA: top 3 are 800, 750, 720 (Sum: 2270)
        };
        System.out.println("[1]#####################");
        findTop3BySum(stocks, prices);
        System.out.println("[2]#####################");
        findTop3BySumV2(stocks, prices);
        System.out.println("[3]#####################");
        findTop3BySumV3(stocks, prices);
        System.out.println("[4]#####################");
        findTop3BySumV4(stocks, prices);
    }
}