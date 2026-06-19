import java.util.*;

public class LeetCode25 {

    // public ListNode reverseKGroup(ListNode head, int k) {
    // ListNode dummy = new ListNode(0);
    // ListNode current = dummy;
    // current.next = head;
    // ListNode nextHeader = head;
    // ListNode n = head;
    // Stack<ListNode> stack = new Stack<>();
    // while (n != null) {
    // if (stack.size() == k) {

    // while (!stack.isEmpty()) {
    // ListNode tmp = stack.pop();
    // // System.out.println(" stack.pop(), tmp=" + tmp);
    // current.next = tmp;
    // current = current.next;
    // }
    // current.next = null;
    // nextHeader = n;
    // // n = n.next;
    // } else {
    // stack.push(n);
    // // System.out.println(" stack.push(n), n=" + n);
    // n = n.next;
    // }
    // }
    // current.next = nextHeader;

    // return dummy.next;
    // }

    private ListNode reverse(ListNode head, ListNode stopNode) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != stopNode) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public ListNode reverseKGroupV2(ListNode head, int k) {
        // 1. Check if there are at least k nodes left
        ListNode check = head;
        for (int i = 0; i < k; i++) {
            if (check == null)
                return head; // Fewer than k nodes, return as is
            check = check.next;
        }

        // 2. Reverse k nodes
        ListNode prev = null;
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // 3. 'head' is now the tail of the reversed segment.
        // Connect it to the result of the next k-group reversal.
        if (curr != null) {
            head.next = reverseKGroup(curr, k);
        }

        // 'prev' is the new head of this reversed segment
        return prev;
    }

    public ListNode reverseKGroupV3(ListNode head, int k) {
        ListNode n = head;
        for (int i = 0; i < k; i++) {
            if (null == n)
                return head;
            n = n.next;
        }

        ListNode pre = null;
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            ListNode tempNext = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tempNext;
        }

        if (curr != null) {
            head.next = reverseKGroupV3(curr, k);
        }
        return pre;
    }

    // Main logic to reverse nodes in k-groups
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        // Check if there are at least k nodes left
        ListNode check = head;
        for (int i = 0; i < k; i++) {
            if (check == null)
                return head;
            check = check.next;
        }

        // Reverse k nodes
        ListNode current = head;
        ListNode next = null;
        ListNode prev = null;
        int count = 0;

        while (count < k && current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        if (next != null) {
            head.next = reverseKGroup(next, k);
        }

        return prev;
    }

    public static void main(String[] args) {
        LeetCode25 solver = new LeetCode25();

        // Test inputs grouped into arrays
        int[][] inputs = {
                { 1, 2, 3, 4, 5 },
                { 1, 2, 3, 4, 5 },
                { 1, 2, 3, 4 },
                { 1, 2 },
                {}
        };

        int[] kValues = { 2, 3, 4, 1, 2 };

        int[][] expectedResults = {
                { 2, 1, 4, 3, 5 },
                { 3, 2, 1, 4, 5 },
                { 4, 3, 2, 1 },
                { 1, 2 },
                {}
        };

        String[] testNames = {
                "k = 2 (Even group matching)",
                "k = 3 (Leftover nodes left alone)",
                "k = 4 (k equals length of list)",
                "k = 1 (No changes expected)",
                "Empty list"
        };

        System.out.println("--- Running Looped Tests ---");

        // Loop through all test cases sequentially
        for (int i = 0; i < inputs.length; i++) {
            runTestCase(solver, inputs[i], kValues[i], expectedResults[i], testNames[i]);
        }
    }

    /**
     * Unified test runner function.
     */
    private static void runTestCase(LeetCode25 solver, int[] input, int k, int[] expected, String testName) {
        ListNode head = createList(input);
        ListNode resultHead = solver.reverseKGroupV3(head, k);
        int[] actual = listToArray(resultHead);

        if (Arrays.equals(actual, expected)) {
            System.out.println("[PASS] " + testName);
        } else {
            System.out.println("[FAIL] " + testName);
            System.out.println("   Expected: " + Arrays.toString(expected));
            System.out.println("   Actual:   " + Arrays.toString(actual));
        }
    }

    // Helper: Array -> Linked List
    private static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    // Helper: Linked List -> Array
    private static int[] listToArray(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        int[] result = new int[count];
        temp = head;
        for (int i = 0; i < count; i++) {
            result[i] = temp.val;
            temp = temp.next;
        }
        return result;
    }
}
