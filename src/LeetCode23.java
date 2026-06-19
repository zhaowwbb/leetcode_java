import java.util.*;

public class LeetCode23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        // Initialize a Min-Heap based on node values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // Push the head node of each non-empty linked list into the heap
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Extract the smallest node continuously and append the next node from that
        // list
        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
            current.next = smallestNode;
            current = current.next;

            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }
        }

        return dummy.next;
    }

    public ListNode mergeKListsV1(ListNode[] lists) {
        ListNode dummyNode = new ListNode(0);
        ListNode current = dummyNode;

        while (true) {
            int moveIndex = -1;
            int minVal = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                // System.out.println("lists[i].val=" + lists[i].val + ", minVal=" + minVal);
                if (lists[i] != null && lists[i].val < minVal) {
                    moveIndex = i;
                    minVal = lists[i].val;
                    // System.out.println("moveIndex=" + moveIndex + ",minVal=" + minVal);
                }
            }
            if (moveIndex < 0) {
                // System.out.println("==== Done =====");
                // done,
                break;
            } else {
                current.next = lists[moveIndex];
                current = current.next;
                lists[moveIndex] = lists[moveIndex].next;
                // System.out.println("Move to next, lists[moveIndex]=" + lists[moveIndex]);
            }
        }
        return dummyNode.next;
    }

    public ListNode mergeKListsV2(ListNode[] lists) {
        ListNode dummyNode = new ListNode(0);
        ListNode current = dummyNode;
        while (true) {
            int minVal = Integer.MAX_VALUE;
            int moveIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < minVal) {
                    moveIndex = i;
                    minVal = lists[i].val;
                }
            }
            if (moveIndex < 0) {
                // System.out.println("===== Done =====");
                break;
            } else {
                current.next = lists[moveIndex];
                lists[moveIndex] = lists[moveIndex].next;
                current = current.next;
            }
        }

        return dummyNode.next;
    }

    public ListNode mergeKListsV3(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> (a.val - b.val));
        for (ListNode n : lists) {
            minHeap.add(n);
        }
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;

            if (node.next != null) {
                minHeap.add(node.next);
            }
        }
        return dummy.next;
    }

    public ListNode mergeKListsV4(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode max = new ListNode(Integer.MAX_VALUE);

        while (curr != null) {
            ListNode min = max;
            int index = -1;
            for (int i = 0; i < lists.length; i++) {
                ListNode n = lists[i];
                if (n != null && n.val <= min.val) {
                    min = n;
                    index = i;
                }
            }
            if (index >= 0) {
                curr.next = min;
                curr = curr.next;
                min = curr;
                lists[index] = lists[index].next;
            } else {
                // done
                break;
            }
        }
        return dummy.next;
    }

    public ListNode mergeKListsV5(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        PriorityQueue<ListNode> miniHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                miniHeap.add(lists[i]);
        }
        ListNode current = dummy;
        while (!miniHeap.isEmpty()) {
            ListNode small = miniHeap.poll();
            current.next = small;
            current = current.next;
            if (small.next != null) {
                miniHeap.add(small.next);
            }
        }

        return dummy.next;
    }

    public ListNode mergeKListsV6(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode n : lists) {
            minHeap.add(n);
        }
        while (!minHeap.isEmpty()) {
            ListNode small = minHeap.poll();
            tail.next = small;
            tail = tail.next;
            if (small.next != null) {
                minHeap.add(small.next);
            }
        }

        return dummy.next;
    }

    // Helper method to build a linked list from an array
    private static ListNode buildList(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to convert a linked list back to an ArrayList for verification
    private static List<Integer> listToArrayList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        return result;
    }

    // Test logic in the main method
    public static void main(String[] args) {
        LeetCode23 solver = new LeetCode23();

        // Test dataset mapping a collection of 2D arrays to their combined sorted
        // output
        int[][][] testCasesInputs = {
                { { 1, 4, 5 }, { 1, 3, 4 }, { 2, 6 } },
                {},
                { {} }
        };

        int[][] expectedOutputs = {
                { 1, 1, 2, 3, 4, 4, 5, 6 },
                {},
                {}
        };

        System.out.println("--- Running Merge k Sorted Lists Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testCasesInputs.length; i++) {
            int[][] currentCaseArrays = testCasesInputs[i];

            // Build the array of standard ListNode structures dynamically
            ListNode[] lists = new ListNode[currentCaseArrays.length];
            for (int j = 0; j < currentCaseArrays.length; j++) {
                lists[j] = buildList(currentCaseArrays[j]);
            }

            // Convert expected array to an ArrayList for deep comparison
            List<Integer> expectedList = new ArrayList<>();
            for (int val : expectedOutputs[i])
                expectedList.add(val);

            // The single function call point
            ListNode resultHead = solver.mergeKLists(lists);

            // Serialize the linked list result back to a standard collection flat form
            List<Integer> actualList = listToArrayList(resultHead);

            // Format inputs to print out informative diagnostic histories
            String inputsFormatted = Arrays.deepToString(currentCaseArrays);

            // Validation check
            if (actualList.equals(expectedList)) {
                System.out.println(
                        "Test Case " + (i + 1) + ": PASSED (Input: " + inputsFormatted + " -> " + actualList + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: " + inputsFormatted +
                        "\n  Expected: " + expectedList + "\n  Got:      " + actualList);
            }
        }
    }
}
