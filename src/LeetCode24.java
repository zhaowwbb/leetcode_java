import java.util.Arrays;

public class LeetCode24 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;

            first.next = second.next;
            second.next = first;
            current.next = second;

            current = first;
        }

        return dummy.next;
    }

    public ListNode swapPairsV2(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode first = head;
        ListNode second = head != null ? head.next : null;
        ListNode nextHead = second != null ? second.next : null;
        ListNode current = dummy;
        while (first != null || second != null || nextHead != null) {
            if (second != null) {
                nextHead = second.next;
                second.next = null;
                current.next = second;
                current = current.next;
            } else {
                nextHead = null;
            }
            if (first != null) {
                first.next = null;
                current.next = first;
                current = current.next;
            }
            if (nextHead != null) {
                first = nextHead;
                second = first != null ? first.next : null;
            } else {
                break;
            }
        }

        return dummy.next;
    }

    public ListNode swapPairsV3(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        current.next = head;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;

            current.next = second;
            first.next = second.next;
            second.next = first;
            current = first;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        LeetCode24 solver = new LeetCode24();

        System.out.println("--- Running Tests ---");

        // One unified function call handles creation, execution, and validation for
        // each case
        runTestCase(solver, new int[] { 1, 2, 3, 4 }, new int[] { 2, 1, 4, 3 }, "Standard Even List");
        runTestCase(solver, new int[] { 1, 2, 3, 4, 5 }, new int[] { 2, 1, 4, 3, 5 }, "Odd Length List");
        runTestCase(solver, new int[] { 1 }, new int[] { 1 }, "Single Node List");
        runTestCase(solver, new int[] {}, new int[] {}, "Empty List");
    }

    /**
     * Unified test runner function.
     */
    private static void runTestCase(LeetCode24 solver, int[] input, int[] expected, String testName) {
        ListNode head = createList(input);
        ListNode resultHead = solver.swapPairs(head);
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
