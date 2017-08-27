import common.ListNode;

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
	// two pointer to find the position. seperate two part and connect
	if (head == null || head.next == null || k == 0)
	    return head;
	ListNode dummy = new ListNode(0);

	// count length of list
	ListNode cnt = head;
	int length = 0;
	while (cnt != null) {
	    length++;
	    cnt = cnt.next;
	}

	ListNode fast = head, slow = head;
	k = k % length;
	while (k > 0) {
	    fast = fast.next;
	    k--;
	}

	while (fast.next != null) {
	    fast = fast.next;
	    slow = slow.next;
	}

	fast.next = head;
	dummy.next = slow.next;
	slow.next = null;

	return dummy.next;
    }
}
