import java.util.ArrayList;
import java.util.List;

public class Util {
    public static void printNode(ListNode head) {
        ListNode n = head;
        while (n != null) {
            System.out.print("[" + n.val + "]->");
            n = n.next;
        }
        System.out.print("[]");
        System.out.println("");
    }

    public static ListNode createListNodeFromArray(int[] nums) {
        ListNode root = null;
        ListNode preNode = null;
        for (int i = 0; i < nums.length; i++) {
            if (null == preNode) {
                root = new ListNode(nums[i]);
                preNode = root;
            } else {
                ListNode n = new ListNode(nums[i]);
                preNode.next = n;
                preNode = n;
            }
        }
        return root;
    }

// Helper: Array -> Linked List
    public static ListNode createLinkedList(int[] elements) {
        if (elements == null || elements.length == 0) return null;
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (int val : elements) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper: Linked List -> Java List
    public static List<Integer> linkedListToArrayList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        return result;
    }

}
