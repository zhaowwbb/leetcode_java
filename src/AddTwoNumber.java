// import java.lang.classfile.components.ClassPrinter.ListNode;

import java.util.Arrays;

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
public class AddTwoNumber {
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

    private ListNode createListNode(int[] arr) {
        ListNode head = null;
        ListNode current = null;
        for (int num : arr) {
            ListNode newNode = new ListNode(num);
            if (head == null) {
                head = newNode;
                current = head;
            } else {
                current.next = newNode;
                current = current.next;
            }
        }
        return head;
    }

    private void printListNode(ListNode node) {
        ListNode temp = node;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder result = new StringBuilder();
        ListNode n1 = new ListNode();
        ListNode n2 = new ListNode();
        int val = 0;
        int v1 = 0, v2 = 0;
        int carry = 0;
        n1 = l1;
        n2 = l2;
        while (true) {
            if (n1 != null) {

                v1 = n1.val;
                n1 = n1.next;
            } else {
                v1 = 0;
            }
            if (n2 != null) {

                v2 = n2.val;
                n2 = n2.next;
            } else {
                v2 = 0;
            }
            val = v1 + v2 + carry;
            if (val > 9) {
                val = val % 10;
                carry = 1;
            } else {
                carry = 0;
            }
            result.append(val);
            if (n1 == null && n2 == null) {
                if (carry == 1) {
                    result.append(1);
                }
                break;
            }
        }
        String s = result.toString();
        System.out.println("Result: " + s);
        ListNode preNode = null;
        ListNode resultNode = null;
        for (int i = 0; i < s.length(); i++) {
            ListNode node = new ListNode(Character.getNumericValue(s.charAt(i)));
            if (preNode == null) {
                preNode = node;
                resultNode = preNode;
            } else {
                preNode.next = node;
                preNode = node;
            }
        }

        return resultNode;
    }

    public ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            // current.val = sum % 10;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        return dummyHead.next;
    }

    public ListNode addTwoNumbersV3(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        int increase = 0;
        ListNode preNode = dummyNode;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + increase;
            if (sum > 9) {
                increase = 1;
                sum %= 10;
            } else {
                increase = 0;
            }
            ListNode node = new ListNode(sum);
            preNode.next = node;
            preNode = node;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + increase;
            if (sum > 9) {
                increase = 1;
                sum %= 10;
            } else {
                increase = 0;
            }
            ListNode node = new ListNode(sum);
            preNode.next = node;
            preNode = node;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + increase;
            if (sum > 9) {
                increase = 1;
                sum %= 10;
            } else {
                increase = 0;
            }
            ListNode node = new ListNode(sum);
            preNode.next = node;
            preNode = node;
            l1 = l1.next;
        }

        if (increase > 0) {
            ListNode node = new ListNode(1);
            preNode.next = node;
        }

        return dummyNode.next;
    }

    public ListNode addTwoNumbersV4(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode preNode = dummyNode;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;

            ListNode node = new ListNode(sum % 10);
            preNode.next = node;
            preNode = node;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummyNode.next;
    }

    public void test(int[] nums1, int[] nums2, String expected) {
        System.out.println("ListNode 1=" + Arrays.toString(nums1));
        System.out.println("ListNode 2=" + Arrays.toString(nums2));
        System.out.println("Expected=" + expected);

        ListNode l1 = createListNode(nums1);
        ListNode l2 = createListNode(nums2);

        ListNode result = addTwoNumbers(l1, l2);
        printListNode(result);

        addTwoNumbersV2(l1, l2);
        printListNode(result);

        addTwoNumbersV3(l1, l2);
        printListNode(result);

        addTwoNumbersV4(l1, l2);
        printListNode(result);
        System.out.println("################################");
    }

    public static void main(String[] args) {

        AddTwoNumber utils = new AddTwoNumber();
        int[] nums1 = {};
        int[] nums2 = {};
        nums1 = new int[] { 2, 4, 3 };
        nums2 = new int[] { 5, 6, 4 };
        utils.test(nums1, nums2, "[7,0,8]");

        nums1 = new int[] { 0 };
        nums2 = new int[] { 0 };
        utils.test(nums1, nums2, "[0]");

        nums1 = new int[] { 9, 9, 9, 9, 9, 9, 9 };
        nums2 = new int[] { 9, 9, 9, 9 };
        utils.test(nums1, nums2, "[8,9,9,9,0,0,0,1]");
    }
}
