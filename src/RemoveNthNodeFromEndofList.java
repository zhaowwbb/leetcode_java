public class RemoveNthNodeFromEndofList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode createListNodeFromArray(int[] nums) {
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

    public ListNode removeNthFromEndV3(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;

        for(int i = 0; i <= n ;i++){
            fast = fast.next;
        }

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummyHead.next;

    }

    public ListNode removeNthFromEndV2(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        boolean isStop = false;
        ListNode fastNode = dummyHead;
        ListNode slowNode = dummyHead;
        while(fastNode != null && slowNode != null){
            //move fast
            for(int i = 0; i <= n; i++){
                fastNode =  fastNode.next;
                if(null == fastNode){return dummyHead.next;}
            }
            if(fastNode == null){
                slowNode.next =  slowNode.next.next;
            }
            slowNode = slowNode.next;
        }
        return dummyHead.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(null == head)return null;
        ListNode result = head;
        int size = 0;
        ListNode node = head;
        while(node != null){
            size++;
            node = node.next;
        }
        if(size < n)return head;
        if(size == n)return head.next;
        int count = size - n - 1;
        System.out.println("count=" + count);
        ListNode preNode = head;
        while(preNode != null && count > 0){
            preNode = preNode.next; 
            count--;
        }
        preNode.next = preNode.next.next;
        return head;
    }

    private void printNode(ListNode head) {
        ListNode n = head;
        while (n != null) {
            System.out.print("[" + n.val + "]->");
            n = n.next;
        }
        System.out.print("[]");
        System.out.println("");
    }

    public void test(ListNode head, int n) {
        printNode(head);
        System.out.println("Remove n=" + n);
        // ListNode result = removeNthFromEnd(head, n);
        // ListNode result = removeNthFromEndV2(head, n);
        ListNode result = removeNthFromEndV3(head, n);
        printNode(result);
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndofList util = new RemoveNthNodeFromEndofList();
        int[] nums = { 1, 2, 3, 4, 5 };
        ListNode head = util.createListNodeFromArray(nums);

        util.test(head, 2);
        System.out.println("############################");
        nums = new int[] { 1 };
        head = util.createListNodeFromArray(nums);
        util.test(head, 1);
        System.out.println("############################");
        nums = new int[] { 1, 2 };
        head = util.createListNodeFromArray(nums);
        util.test(head, 1);
    }
}
