import java.util.*;

public class ReverseNodesInkGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        current.next = head;
        ListNode nextHeader = head;
        ListNode n = head;
        Stack<ListNode> stack = new Stack<>();
        while (n != null) {
            if (stack.size() == k) {

                while (!stack.isEmpty()) {
                    ListNode tmp = stack.pop();
                    // System.out.println(" stack.pop(), tmp=" + tmp);
                    current.next = tmp;
                    current = current.next;
                }
                current.next = null;
                nextHeader = n;
                // n = n.next;
            } else {
                stack.push(n);
                // System.out.println(" stack.push(n), n=" + n);
                n = n.next;
            }
        }
        current.next = nextHeader;

        return dummy.next;
    }

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
        for(int i = 0; i < k; i++){
            if(null == n)return head;
            n = n.next;
        }

        ListNode pre = null;
        ListNode curr = head;
        for(int i = 0; i < k; i++){
            ListNode tempNext = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tempNext;
        }

        if(curr != null){
            head.next = reverseKGroupV3(curr, k);
        }
        return pre;
    }

    public void test(int[] nums, int k) {
        ListNode list = Util.createListNodeFromArray(nums);
        System.out.println("Input: k=" + k);
        Util.printNode(list);
        ListNode result = reverseKGroup(list, k);
        System.out.println("[V1] revers result:");
        Util.printNode(result);

        list = Util.createListNodeFromArray(nums);
        result = reverseKGroupV2(list, k);
        System.out.println("[V2] revers result:");
        Util.printNode(result);

        list = Util.createListNodeFromArray(nums);
        result = reverseKGroupV3(list, k);
        System.out.println("[V3] revers result:");
        Util.printNode(result);
        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        ReverseNodesInkGroup util = new ReverseNodesInkGroup();
        int k = 2;
        int[] nums = { 1, 2, 3, 4, 5 };
        util.test(nums, k);
        k = 3;
        nums = new int[] { 1, 2, 3, 4, 5 };
        util.test(nums, k);

    }
}
