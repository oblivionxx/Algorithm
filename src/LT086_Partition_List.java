import common.ListNode;

/*
 Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
 */
/*
 * LinkedList, Two Pointer.
 */
public class LT086_Partition_List {
    // 1. keep two list. left list and right list.Then combine
    public ListNode partition(ListNode head, int x) {
	ListNode large = new ListNode(0);
	ListNode small = new ListNode(0);
	ListNode smallHead = small, largeHead = large;
	while (head != null) {
	    if (head.val >= x) {
		large.next = head;
		large = large.next;
	    } else {
		small.next = head;
		small = small.next;
	    }
	    head = head.next;
	}

	// connect
	large.next = null;
	small.next = largeHead.next;

	return smallHead.next;

    }

    // 2. Dont understand.
    public ListNode partition2(ListNode head, int x) {
	if (head == null)
	    return null;
	ListNode helper = new ListNode(0);
	helper.next = head;
	ListNode walker = helper;
	ListNode runner = helper;
	while (runner.next != null) {
	    if (runner.next.val < x) {
		if (walker != runner) {
		    ListNode next = runner.next.next;
		    runner.next.next = walker.next;
		    walker.next = runner.next;
		    runner.next = next;
		} else
		    runner = runner.next;
		walker = walker.next;
	    } else {
		runner = runner.next;
	    }
	}
	return helper.next;
    }

}
