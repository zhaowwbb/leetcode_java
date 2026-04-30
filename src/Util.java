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

}
