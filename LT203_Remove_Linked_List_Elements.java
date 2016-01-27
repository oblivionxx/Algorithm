/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

LinkedList
 */
public class LT203_Remove_Linked_List_Elements {
	public ListNode removeElements(ListNode head, int val) {
	    
        ListNode cur = head;
        ListNode next = null;
        
        if(head==null)
            return null;
        if(head.val==val)
            return removeElements(head.next, val);
        
        while(cur.next!=null){
            next= cur.next;
            if(next.val==val){
                cur.next = next.next;
            }
            else
                cur = next;
        }
        
        return head;
            
    }
}
