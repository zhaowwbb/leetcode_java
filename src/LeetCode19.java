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

    public static void main(String[] args) {
        LeetCode19 solver = new LeetCode19();

        System.out
                .println("Running Consolidated Single-Call Test Suite...\n------------------------------------------");

        /*
         * We assemble a composite list designed to evaluate multiple conditions.
         * Target: Remove the 5th element from the end (n = 5).
         * 
         * Index from end: 10 9 8 7 6 5 4 3 2 1
         * Input List: [10, 20, 0, 1, 2, 3, 4, 5, 0, 99]
         * ^
         * (Target Node)
         * 
         * By removing the 5th node from the end ('3'):
         * - It tests intermediate deletion (3 is between 2 and 4).
         * - It preserves the head boundary integrity (10 and 20 are left untouched).
         * - It preserves the trailing segments ([4, 5, 0, 99] remain intact).
         */
        ListNode compositeHead = Util.createLinkedList(new int[] { 10, 20, 0, 1, 2, 3, 4, 5, 0, 99 });
        int n = 5;

        // The EXACTLY ONE allowed call to the solver
        ListNode resultHead = solver.removeNthFromEndV6(compositeHead, n);

        List<Integer> actualOutput = Util.linkedListToArrayList(resultHead);
        List<Integer> expectedOutput = List.of(10, 20, 0, 1, 2, 4, 5, 0, 99); // '3' is removed

        // Verification
        if (Objects.equals(expectedOutput, actualOutput)) {
            System.out.println("[PASS] Multi-scenario criteria met in a single algorithmic execution step.");
            System.out.println("       Resulting Stream: " + actualOutput);
        } else {
            System.err.println("[FAIL] Composite structure check failed.");
            System.err.println("       Expected: " + expectedOutput);
            System.err.println("       Actual:   " + actualOutput);
        }

        System.out.println("------------------------------------------\nExecution Complete.");

    }
}
