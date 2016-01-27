
/*
 Given a list, rotate the list to the right by k places, where k is non-negative.

 For example:
 Given 1->2->3->4->5->NULL and k = 2,
 return 4->5->1->2->3->NULL.
 */
/*
 * LinkedList, Two Pointer
 */
public class LT061_Rotate_List {
	public ListNode rotateRight(ListNode head, int k) {
        //idea is to find the node using two pointer. then break link. if k=0, then remove nothing. 
		//attention: if k>length of list. or the node is head or end.
		
		while(head==null || head.next==null||k==0) return head;
        
		//count length of list
		ListNode cnt = head;
		int length = 0;
		while(cnt!=null){
			length++;
			cnt = cnt.next;
		}
		
		int loop = k%length;	//k>length case.
		if(loop==0) return head;
		
		//use fast and slow pointer
		ListNode fast = head, slow = head, dummy = new ListNode(0);
		while(loop>0){
			fast = fast.next;
			loop--;
		}
		
		while(fast.next!=null){
			fast = fast.next;
			slow = slow.next;
		}
		//slow.next is the new head.
		dummy.next = slow.next;
		fast.next  = head;
		slow.next = null;
		
		return dummy.next;
		
    }
}
