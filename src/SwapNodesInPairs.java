public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode first = head;
        ListNode second = head != null ? head.next : null;
        ListNode current = dummy;
        ListNode nextHead = second != null ? second.next : null;

        while (first != null || second != null) {
            // System.out.println("first=" + first + ", second=" + second);
            // ListNode tmp = null;
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
                // current.next = null;
            }
            // System.out.println(" current=" + current + ", nextHead=" + nextHead);
            if (nextHead == null) {
                break;
            }
            first = nextHead;
            second = (first != null) ? first.next : null;
            // System.out.println(" move, first=" + first + ", second=" + second);

        }

        // System.out.println(" class[out] first=" + first + ", second=" + second);

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

    public void test(int[] nums) {
        ListNode list = Util.createListNodeFromArray(nums);
        System.out.println("Input:");
        Util.printNode(list);
        ListNode result = swapPairs(list);
        System.out.println("[V1] Swap result:");
        Util.printNode(result);

        list = Util.createListNodeFromArray(nums);
        result = swapPairsV2(list);
        System.out.println("[V2] Swap result:");
        Util.printNode(result);

        list = Util.createListNodeFromArray(nums);
        result = swapPairsV3(list);
        System.out.println("[V3] Swap result:");
        Util.printNode(result);

        System.out.println("=======================================");
    }

    public static void main(String[] args) {
        SwapNodesInPairs util = new SwapNodesInPairs();
        int[] nums = { 1, 2, 3, 4 };
        util.test(nums);
        nums = new int[] {};
        util.test(nums);
        nums = new int[] { 1 };
        util.test(nums);
        nums = new int[] { 1, 2, 3 };
        util.test(nums);
    }
}
