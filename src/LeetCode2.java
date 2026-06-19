// import java.lang.classfile.components.ClassPrinter.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
public class LeetCode2 {

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

    public ListNode addTwoNumbersV5(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        int carry = 0;
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int leftVal = l1 != null ? l1.val : 0;
            int rightVal = l2 != null ? l2.val : 0;
            int nextVal = leftVal + rightVal + carry;
            carry = nextVal / 10;
            nextVal = nextVal % 10;
            cur.next = new ListNode(nextVal);
            cur = cur.next;

            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummy.next;
    }

    static class AddTwoNumbersTestCase {
        String description;
        int[] l1Values;
        int[] l2Values;
        List<Integer> expected;

        public AddTwoNumbersTestCase(String description, int[] l1Values, int[] l2Values, List<Integer> expected) {
            this.description = description;
            this.l1Values = l1Values;
            this.l2Values = l2Values;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        LeetCode2 solver = new LeetCode2();
        System.out.println("Running Add Two Numbers Test Suite...\n------------------------------------------");

        // 1. Build the suite collection mapping inputs to structured validations
        List<AddTwoNumbersTestCase> testCases = new ArrayList<>();

        // Example 1: 342 + 465 = 807
        testCases.add(new AddTwoNumbersTestCase(
                "Standard matching length addition with carry-over",
                new int[] { 2, 4, 3 },
                new int[] { 5, 6, 4 },
                List.of(7, 0, 8)));

        // Example 2: 0 + 0 = 0
        testCases.add(new AddTwoNumbersTestCase(
                "Single zero nodes",
                new int[] { 0 },
                new int[] { 0 },
                List.of(0)));

        // Example 3: 9999999 + 9999 = 10009998
        testCases.add(new AddTwoNumbersTestCase(
                "Mismatched list lengths with a cascading final carry",
                new int[] { 9, 9, 9, 9, 9, 9, 9 },
                new int[] { 9, 9, 9, 9 },
                List.of(8, 9, 9, 9, 0, 0, 0, 1)));

        // 2. Iterate and execute each configuration exactly once
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            AddTwoNumbersTestCase tc = testCases.get(i);

            ListNode head1 = Util.createLinkedList(tc.l1Values);
            ListNode head2 = Util.createLinkedList(tc.l2Values);

            // Execute the core algorithm target
            // ListNode actualHead = solver.addTwoNumbers(head1, head2);
            ListNode actualHead = solver.addTwoNumbersV5(head1, head2);
            List<Integer> actualResultList = Util.linkedListToArrayList(actualHead);

            if (Objects.equals(tc.expected, actualResultList)) {
                System.out.println(String.format("[PASS] Test %d: %s", i + 1, tc.description));
                passed++;
            } else {
                System.err.println(String.format("[FAIL] Test %d: %s", i + 1, tc.description));
                System.err.println("       Expected: " + tc.expected);
                System.err.println("       Actual:   " + actualResultList);
            }
        }

        System.out.println("------------------------------------------");
        System.out.println(String.format("Execution Result: %d/%d Tests Passed.", passed, testCases.size()));
    }
}
