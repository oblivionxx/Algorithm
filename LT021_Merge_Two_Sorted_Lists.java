/*
 Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 LinkedList
 */
public class LT021_Merge_Two_Sorted_Lists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;		//dont = dummy.next here.
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        while(cur1!=null && cur2!=null){
        	if(cur1.val<cur2.val){
        		cur.next = cur1;
        		cur1 = cur1.next;
        	}else{
        		cur.next = cur2;
        		cur2 = cur2.next;
        	}
        	cur = cur.next;
        }
        
        if(cur1!=null)
        	cur.next = cur1;
        
        if(cur2!=null)
        	cur.next = cur2;
       
        
        return dummy.next;
    }
	
	public class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
}
