import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LeetCode21 {

    public ListNode mergeTwoListsV2(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(0);
        ListNode current = dummyNode;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        return dummyNode.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(0);
        ListNode tailNode = null;
        ListNode node = null;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node = new ListNode(list1.val);
                if (tailNode == null) {
                    tailNode = node;
                    dummyNode.next = tailNode;
                } else {
                    tailNode.next = node;
                    tailNode = node;
                }

                list1 = list1.next;
            } else {
                node = new ListNode(list2.val);
                if (tailNode == null) {
                    tailNode = node;
                    dummyNode.next = tailNode;
                } else {
                    tailNode.next = node;
                    tailNode = node;
                }

                list2 = list2.next;
            }
        }

        while (list1 != null) {
            node = new ListNode(list1.val);
            if (tailNode != null) {
                tailNode.next = node;
            } else {
                // tailNode = node;
                dummyNode.next = node;
            }

            tailNode = node;
            list1 = list1.next;
        }
        while (list2 != null) {
            node = new ListNode(list2.val);
            if (tailNode != null) {
                tailNode.next = node;
            } else {
                dummyNode.next = node;
            }
            tailNode = node;
            list2 = list2.next;
        }
        return dummyNode.next;
    }

    public ListNode mergeTwoListsV3(ListNode list1, ListNode list2) {
        ListNode dummyList = new ListNode(0);
        ListNode pre = dummyList;
        while (list1 != null && list2 != null) {
            int v1 = (list1 == null) ? Integer.MIN_VALUE : list1.val;
            int v2 = (list2 == null) ? Integer.MIN_VALUE : list2.val;
            if (v1 <= v2) {
                if (list1 != null) {
                    pre.next = list1;
                    pre = pre.next;
                    list1 = list1.next;
                }
            } else {
                if (list2 != null) {
                    pre.next = list2;
                    pre = pre.next;
                    list2 = list2.next;
                }
            }
        }
        if (list1 != null) {
            pre.next = list1;
        }
        if (list2 != null) {
            pre.next = list2;
        }

        return dummyList.next;
    }

    public ListNode mergeTwoListsV4(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(0);
        ListNode current = dummyNode;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = (list1 != null) ? list1 : list2;
        return dummyNode.next;
    }

    public ListNode mergeTwoListsV5(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        // dummy.next = list1;
        ListNode node = dummy;
        while (list1 != null || list2 != null) {
            int v1 = list1 != null ? list1.val : Integer.MIN_VALUE;
            int v2 = list2 != null ? list2.val : Integer.MIN_VALUE;
            if (v1 < v2) {
                if (list1 != null) {
                    node.next = list1;
                    list1 = list1.next;
                } else {
                    node.next = list2;
                    list2 = list2.next;
                }
                node = node.next;
            } else {
                if (list2 != null) {
                    node.next = list2;
                    list2 = list2.next;
                } else {
                    node.next = list1;
                    list1 = list1.next;
                }
                node = node.next;
            }
        }

        return dummy.next;
    }

    public ListNode mergeTwoListsV6(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }
        return dummy.next;
    }

    // Helper method to build a linked list from an array
    private static ListNode buildList(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to convert a linked list to an ArrayList for easy comparison
    private static List<Integer> listToArrayList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode21 solver = new LeetCode21();

        // 2D Array datasets for list1 and list2
        int[][] list1Inputs = {
                { 1, 2, 4 },
                {},
                {}
        };
        int[][] list2Inputs = {
                { 1, 3, 4 },
                {},
                { 0 }
        };

        // Expected outputs as standard arrays
        int[][] expectedOutputs = {
                { 1, 1, 2, 3, 4, 4 },
                {},
                { 0 }
        };

        System.out.println("--- Running Merge Two Sorted Lists Tests ---");

        // Loop through all test cases, executing the function call exactly once per
        // iteration
        for (int i = 0; i < list1Inputs.length; i++) {
            // Build the dynamic structures fresh for each pass
            ListNode l1 = buildList(list1Inputs[i]);
            ListNode l2 = buildList(list2Inputs[i]);

            // Convert expected array to List structure for comparison
            List<Integer> expectedList = new ArrayList<>();
            for (int val : expectedOutputs[i])
                expectedList.add(val);

            // The single function call
            ListNode resultHead = solver.mergeTwoListsV6(l1, l2);

            // Convert actual linked list result back to an ArrayList for verification
            List<Integer> actualList = listToArrayList(resultHead);

            // Create strings for visualization
            String input1Str = Arrays.toString(list1Inputs[i]);
            String input2Str = Arrays.toString(list2Inputs[i]);

            // Validation check
            if (actualList.equals(expectedList)) {
                System.out.println("Test Case " + (i + 1) + ": PASSED (" + input1Str + " + " + input2Str + " -> "
                        + actualList + ")");
            } else {
                System.err.println("Test Case " + (i + 1) + ": FAILED! Input: " + input1Str + " and " + input2Str +
                        " | Expected: " + expectedList + ", but got: " + actualList);
            }
        }
    }
}
