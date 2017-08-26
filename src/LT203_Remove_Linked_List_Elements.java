/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

LinkedList
 */
public class LT203_Remove_Linked_List_Elements {
	public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        while(cur!=null){
            if(cur.val==val){
                cur = cur.next;
                pre.next = cur;
            }else{
                cur = cur.next;
                pre = pre.next;
            }
        }
        
        return dummy.next;
        
    }
}
