import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LeetCode19 {

    public ListNode removeNthFromEndV3(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyHead.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (null == head)
            return null;
        ListNode result = head;
        int size = 0;
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        if (size < n)
            return head;
        if (size == n)
            return head.next;
        int count = size - n - 1;
        System.out.println("count=" + count);
        ListNode preNode = head;
        while (preNode != null && count > 0) {
            preNode = preNode.next;
            count--;
        }
        preNode.next = preNode.next.next;
        return head;
    }

    public ListNode removeNthFromEndV4(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = head;

        ListNode fastNode = null;
        // calculate fast node
        for (int i = 0; i <= n; i++) {
            if (pre.next != null) {
                fastNode = pre.next;
                pre = pre.next;
            } else {
                return dummyHead.next;
            }
        }
        pre = head;
        while (pre != null) {
            if (fastNode.next == null) {
                ListNode temp = pre.next;
                pre.next = temp.next;
                break;
            }
            fastNode = fastNode.next;
            pre = pre.next;
        }

        return dummyHead.next;
    }

    public ListNode removeNthFromEndV5(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode fast = dummyNode;
        ListNode slow = dummyNode;
        // move fast node
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // remove
        slow.next = slow.next.next;

        return dummyNode.next;
    }

    public ListNode removeNthFromEndV6(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preNode = dummy;
        ListNode fastNode = head;
        for (int i = 0; i < n; i++) {
            if (preNode.next != null) {
                fastNode = preNode.next;
                preNode = fastNode;
            } else {
                // < n node
                return dummy.next;
            }
        }
        preNode = dummy;
        while (preNode.next != null) {
            if (fastNode.next == null) {
                // delete
                ListNode tmp = preNode.next;
                preNode.next = tmp.next;
                break;
            }
            preNode = preNode.next;
            fastNode = fastNode.next;
        }

        return dummy.next;
    }

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

    // Helper method to convert a linked list to an ArrayList for easy comparison
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
        LeetCode19 solver = new LeetCode19();

        // Multi-case datasets
        int[][] testLists = {
                { 1, 2, 3, 4, 5 },
                { 1 },
                { 1, 2 }
        };
        int[] testN = { 2, 1, 1 };

        // Expected outputs as standard arrays
        int[][] expectedOutputs = {
                { 1, 2, 3, 5 },
                {},
                { 1 }
        };

        System.out.println("--- Running Remove Nth Node From End Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < testLists.length; i++) {
            // Build the dynamic structure fresh for each pass
            ListNode head = buildList(testLists[i]);
            int n = testN[i];

            // Convert expected array to List structure for comparison
            List<Integer> expectedList = new ArrayList<>();
            for (int val : expectedOutputs[i])
                expectedList.add(val);

            // The single function call
            ListNode resultHead = solver.removeNthFromEndV6(head, n);

            // Convert actual linked list result to an ArrayList for verification
            List<Integer> actualList = listToArrayList(resultHead);

            // Validation check
            if (actualList.equals(expectedList)) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (Input: " + Arrays.toString(testLists[i]) +
                        ", n = " + n + " -> " + actualList + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: " + Arrays.toString(testLists[i]) +
                        ", n = " + n + " | Expected: " + expectedList + ", but got: " + actualList);
            }
        }
    }
}
