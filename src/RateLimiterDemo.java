import java.util.*;

public class RateLimiterDemo {

    // --- Logic ---
    private final long windowSizeMs = 5000; // 5-second window
    private final Queue<Event> eventQueue = new LinkedList<>();
    private final Map<String, Integer> userCounts = new HashMap<>();

    static class Event {
        long timestamp;
        String userId;

        Event(String userId, long timestamp) {
            this.userId = userId;
            this.timestamp = timestamp;
        }
    }

    /**
     * Core logic: Adds an event and prunes old ones based on the provided time.
     */
    public void processEvent(String userId, long currentTime) {
        // 1. Add the new event
        eventQueue.add(new Event(userId, currentTime));
        userCounts.put(userId, userCounts.getOrDefault(userId, 0) + 1);

        // 2. Evict events older than 5 seconds (currentTime - 5000)
        while (!eventQueue.isEmpty() && (currentTime - eventQueue.peek().timestamp) > windowSizeMs) {
            Event expired = eventQueue.poll();
            int count = userCounts.get(expired.userId);
            if (count <= 1) {
                userCounts.remove(expired.userId);
            } else {
                userCounts.put(expired.userId, count - 1);
            }
        }

        System.out.printf("[%dms] User: %s | Current Window Count: %d%n",
                currentTime, userId, userCounts.getOrDefault(userId, 0));
    }

    public void processEventWrapper(String userId, long currentTime) {
        // processEvent(userId, currentTime);
        // processEvent1(userId, currentTime);
        processEvent2(userId, currentTime);
    }

    public void processEvent1(String userId, long currentTime) {
        Event event = new Event(userId, currentTime);
        eventQueue.add(event);
        userCounts.put(userId, userCounts.getOrDefault(userId, 0) + 1);
        Event top = eventQueue.peek();
        if (top.timestamp - currentTime > windowSizeMs) {
            if (userCounts.containsKey(top.userId)) {
                userCounts.put(top.userId, userCounts.get(top.userId) - 1);
            }
            eventQueue.poll();
        }
        System.out.printf("[%dms] User: %s | Current Window Count: %d%n",
                currentTime, userId, userCounts.getOrDefault(userId, 0));

    }

    public void processEvent2(String userId, long currentTime) {
        Event event = new Event(userId, currentTime);
        eventQueue.add(event);
        userCounts.put(userId, userCounts.getOrDefault(userId, 0) + 1);
        while (!eventQueue.isEmpty() && currentTime - eventQueue.peek().timestamp > windowSizeMs) {
            Event expired = eventQueue.poll();
            int count = userCounts.get(expired.userId);
            if (count == 1) {
                userCounts.remove(expired.userId);
            } else {
                userCounts.put(expired.userId, count - 1);
            }
        }
    }

    // --- Test Suite ---
    public static void main(String[] args) {
        RateLimiterDemo demo = new RateLimiterDemo();
        long startTime = 1000; // Start at 1 second mark

        // System.out.println("--- Scenario 1: Rapid Fire (Under 5s) ---");
        demo.processEventWrapper("Alice", startTime); // T=1000, Count=1
        demo.processEventWrapper("Alice", startTime + 1000); // T=2000, Count=2
        demo.processEventWrapper("Bob", startTime + 1500); // T=2500, Count=1
        demo.processEventWrapper("Alice", startTime + 4000); // T=5000, Count=3

        // System.out.println("\n--- Scenario 2: Expiration Starts ---");
        // This event is at T=6500.
        // The event at T=1000 is now 5.5s old, so it should be evicted.
        demo.processEventWrapper("Alice", startTime + 5500); // T=6500, Alice Count should be 3 (Added 1, Evicted 1)

        // System.out.println("\n--- Scenario 3: Massive Gap (Everything Expires) ---");
        // Jump to T=20000 (20 seconds in). All previous events are long gone.
        demo.processEventWrapper("Alice", startTime + 19000); // T=20000, Count=1

        // System.out.println("\n--- Scenario 4: Bob's Return ---");
        // Bob hasn't been seen since T=2500. He should start fresh at 1.
        demo.processEventWrapper("Bob", startTime + 20000); // T=21000, Count=1

        demo.userCounts.forEach((k, v) -> System.out.println(k + ", count= " + v));
        System.out.println("Queue size: " + demo.eventQueue.size());
        demo.eventQueue.forEach(v -> System.out.println("  {userid:" + v.userId + ", timestamp:" + v.timestamp + "}"));
        System.out.println("=============================");
    }
}