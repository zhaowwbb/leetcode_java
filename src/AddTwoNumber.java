// import java.lang.classfile.components.ClassPrinter.ListNode;

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
        // StringBuilder num1 = new StringBuilder();
        // ListNode temp1 = l1;
        // while (temp1 != null) {
        //     num1.append(temp1.val);
        //     temp1 = temp1.next;
        // }

        // String s1 = num1.reverse().toString();
        // StringBuilder num2 = new StringBuilder();
        // ListNode temp2 = l2;
        // while (temp2 != null) {
        //     num2.append(temp2.val);
        //     temp2 = temp2.next;
        // }
        // String s2 = num2.reverse().toString();
        // System.out.println("    s1: " + s1   );
        // System.out.println("    s2: " + s2   );

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
                if(carry == 1){
                    result.append(1);
                }   
                break;
            }
        }
        String s = result.toString();
        System.out.println("    result: " + s   );
        ListNode preNode = null;   
        ListNode resultNode = null;
        for (int i = 0; i < s.length(); i++) {
            ListNode node = new ListNode(Character.getNumericValue(s.charAt(i)));
            if(preNode == null){
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
        while(l1 != null || l2 != null || carry != 0){
            int val1 = (l1 != null)? l1.val : 0;
            int val2 = (l2 != null)? l2.val : 0;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            // current.val = sum % 10;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }


    public static void main(String[] args) {
        System.out.println("Hello, World!");

        AddTwoNumber utils = new AddTwoNumber();
        ListNode l1 = utils.new ListNode(2);
        l1.next = utils.new ListNode(4);
        l1.next.next = utils.new ListNode(3);

        ListNode l2 = utils.new ListNode(5);
        l2.next = utils.new ListNode(6);
        l2.next.next = utils.new ListNode(4);

        ListNode result = utils.addTwoNumbers(l1, l2);     
        utils.printListNode(result);  
        result = utils.addTwoNumbersV2(l1, l2);     
        utils.printListNode(result);   
 

        l1 = utils.createListNode(new int[]{9,9,9,9,9,9,9});
        l2 = utils.createListNode(new int[]{9,9,9,9});
        result = utils.addTwoNumbers(l1, l2);
        utils.printListNode(result);   
        result = utils.addTwoNumbersV2(l1, l2);
        utils.printListNode(result); 
    }
}
