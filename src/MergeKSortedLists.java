import java.util.*;

public class MergeKSortedLists {
   
    public ListNode mergeKListsV1(ListNode[] lists) {
        ListNode dummyNode = new ListNode(0);
        ListNode current = dummyNode;

        
        while(true){
            int moveIndex = -1;
            int minVal = Integer.MAX_VALUE;
            for(int i = 0; i < lists.length; i++){
                // System.out.println("lists[i].val=" + lists[i].val + ", minVal=" + minVal);
                if(lists[i] != null && lists[i].val < minVal){
                    moveIndex = i;
                    minVal = lists[i].val;
                    // System.out.println("moveIndex=" + moveIndex + ",minVal=" + minVal);
                }
            }
            if(moveIndex < 0){
                System.out.println("==== Done =====");
                //done, 
                break;
            }else{
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
    while(true){
        int minVal = Integer.MAX_VALUE;
        int moveIndex = -1;
        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null && lists[i].val < minVal){
                moveIndex = i;
                minVal = lists[i].val;
            } 
        }
        if(moveIndex < 0){
            System.out.println("=====  Done =====");
            break;
        }else{
            current.next = lists[moveIndex];
            lists[moveIndex] = lists[moveIndex].next;
            current = current.next;
        }
    }

    return dummyNode.next;
}

    public void test(){
        ListNode[] lists = createTestData();
        System.out.println("Input:");
        for(ListNode n : lists){
            Util.printNode(n);
        }
        ListNode result = mergeKListsV1(lists);
        System.out.println("[V1] Merged result:");
        Util.printNode(result);

        lists = createTestData();
        result = mergeKListsV2(lists);
        System.out.println("[V2] Merged result:");
        Util.printNode(result);
    }

    public static ListNode[] createTestData(){
         int[] nums1 = { 1,4,5 };
        int[] nums2 = { 1,3,4 };
        int[] nums3 = { 2,6 };
        ListNode list1 = Util.createListNodeFromArray(nums1);
        ListNode list2 = Util.createListNodeFromArray(nums2); 
        ListNode list3 = Util.createListNodeFromArray(nums3);   
        ListNode[] lists = new ListNode[] {list1, list2, list3};  
        return   lists;         
    } 
    public static void main(String[] args) {
        MergeKSortedLists util = new MergeKSortedLists();
     
        util.test();
    }
}
