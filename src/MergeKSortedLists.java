import java.util.*;

public class MergeKSortedLists {

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

    public void test() {
        ListNode[] lists = createTestData();
        System.out.println("Input:");
        for (ListNode n : lists) {
            Util.printNode(n);
        }
        ListNode result = mergeKListsV1(lists);
        System.out.println("[V1] Merged result:");
        Util.printNode(result);

        // lists = createTestData();
        // result = mergeKListsV2(lists);
        // System.out.println("[V2] Merged result:");
        // Util.printNode(result);

        // lists = createTestData();
        // result = mergeKListsV3(lists);
        // System.out.println("[V3] Merged result:");
        // Util.printNode(result);

        lists = createTestData();
        result = mergeKListsV4(lists);
        System.out.println("[V4] Merged result:");
        Util.printNode(result);

        lists = createTestData();
        result = mergeKListsV5(lists);
        System.out.println("[V5] Merged result:");
        Util.printNode(result);

        lists = createTestData();
        result = mergeKListsV6(lists);
        System.out.println("[V6] Merged result:");
        Util.printNode(result);
        System.out.println("===============================");
    }

    public static ListNode[] createTestData() {
        int[] nums1 = { 1, 4, 5 };
        int[] nums2 = { 1, 3, 4 };
        int[] nums3 = { 2, 6 };
        ListNode list1 = Util.createListNodeFromArray(nums1);
        ListNode list2 = Util.createListNodeFromArray(nums2);
        ListNode list3 = Util.createListNodeFromArray(nums3);
        ListNode[] lists = new ListNode[] { list1, list2, list3 };
        return lists;
    }

    public static void main(String[] args) {
        MergeKSortedLists util = new MergeKSortedLists();

        util.test();
    }
}
