import common.ListNode;

/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

LinkedList
 */
public class LT082_Remove_Duplicates_from_Sorted_List {
    public ListNode deleteDuplicates(ListNode head) {
	if (head == null || head.next == null)
	    return head;

	ListNode dummy = new ListNode(0);
	dummy.next = head;

	ListNode pre = dummy;
	while (pre.next != null && pre.next.next != null) {
	    if (pre.next.val == pre.next.next.val) {
		int dup = pre.next.val;
		while (pre.next != null && pre.next.val == dup)
		    pre.next = pre.next.next;					//update pre.next to point to non-duplicate ones
	    } else {
		pre = pre.next;							//pre is always pointing to one before duplicated ones
	    }
	}

	return dummy.next;
    }
}
