import utils.ListNode;

/*
 Given a linked list, remove the nth node from the end of list and return its head.
   For example,
   Given linked list: 1->2->3->4->5, and n = 2.
   After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.
 */
/*
 * LinkedList, Two Pointer
 */
public class LT019_Remove_Nth_Node_From_End_Of_List {
    public ListNode removeNthFromEnd(ListNode head, int n) {
	if (head == null || head.next == null)
	    return null;
	ListNode fast = head, slow = head;
	while (n > 0) {
	    fast = fast.next;
	    n--;
	}

	// dont forget
	// already move to end. means delete head
	if (fast == null) {
	    head = head.next;
	    return head;
	}

	while (fast.next != null) {
	    fast = fast.next;
	    slow = slow.next; // slow is the node before the target node
	}

	slow.next = slow.next.next;
	return head;

    }
}
