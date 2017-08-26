import utils.ListNode;

/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

Linkedlist
 */
public class LT143_Reorder_List {

    // cut into two list. reverse the latter. combine together.
    public void reorderList(ListNode head) {
	if (head == null || head.next == null)
	    return;

	// find mid
	ListNode slow = head;
	ListNode fast = head;
	while (fast.next != null && fast.next.next != null) {
	    fast = fast.next.next;
	    slow = slow.next;
	}

	// two start of the two halves
	ListNode firsthalf = head;
	ListNode secondhalf = slow.next;
	slow.next = null;

	// reverse next half
	secondhalf = reverseList(secondhalf);

	// connect. firsthalf is pointed to head.
	while (firsthalf != null && secondhalf != null) {
	    ListNode next = secondhalf.next; // tmp
	    secondhalf.next = firsthalf.next;
	    firsthalf.next = secondhalf;
	    firsthalf = secondhalf.next;
	    secondhalf = next;
	}
    }

    public ListNode reverseList(ListNode head) {
	if (head == null || head.next == null)
	    return head;

	ListNode next = null;
	ListNode cur = null;

	while (head != null) {
	    next = head.next;
	    head.next = cur;
	    cur = head;
	    head = next;
	}
	return cur;
    }
}
