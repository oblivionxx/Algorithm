/*
 Given a linked list, swap every two adjacent nodes and return its head.
 For example,
 Given 1->2->3->4, you should return the list as 2->1->4->3.
 Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */
/*
 * LinkedList
 */
public class LT024_Swap_Nodes_In_Pairs {
	public ListNode swapPairs(ListNode head) {
		//Time O(n), space O(1)
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        
        while(cur.next!=null && cur.next.next!=null){
        	ListNode first = cur.next;
        	ListNode second = cur.next.next;
        	first.next = second.next;
        	second.next = first;
        	cur.next = second;
        	cur = cur.next.next; //move two steps
        }
        
        return dummy.next;
    }
	
	
	public ListNode swapPairsSol2(ListNode head) {
		//Recursion. O(n) space
        if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
	
	public class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}
}
