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

    public static void main(String[] args) {
        LeetCode21 solver = new LeetCode21();

        System.out.println("Running Comprehensive Single-Call Test Suite");
        ListNode list1 = Util.createLinkedList(new int[] { -10, -5, 1, 2, 4, 5, 7 });
        ListNode list2 = Util.createLinkedList(new int[] { -3, 1, 3, 4 });

        // The SINGLE execution of the target method
        // ListNode mergedResult = solver.mergeTwoListsV4(list1, list2);
        ListNode mergedResult = solver.mergeTwoListsV6(list1, list2);

        List<Integer> actualOutput = Util.linkedListToArrayList(mergedResult);
        List<Integer> expectedOutput = List.of(-10, -5, -3, 1, 1, 2, 3, 4, 4, 5, 7);

        // Verification
        if (Objects.equals(expectedOutput, actualOutput)) {
            System.out.println("[PASS] All edge cases validated successfully via a single merge execution.");
            System.out.println("       Result: " + actualOutput);
        } else {
            System.err.println("[FAIL] Combined test verification failed!");
            System.err.println("       Expected: " + expectedOutput);
            System.err.println("       Actual:   " + actualOutput);
        }

        System.out.println("------------------------------------------\nExecution Complete.");
    }
}
