/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

LinkedList
 */
public class LT083_Remove_Duplicates_from_Sorted_List {
	public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)
            return head;
           
        ListNode pre = head;
        ListNode cur = head.next;
        
        while(cur!=null){
            if(pre.val == cur.val)
                pre.next = cur.next;
            else
                pre = cur;
                
            cur = cur.next;
        }
        
        return head;
    }

}
