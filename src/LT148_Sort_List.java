import common.ListNode;

/*
Sort a linked list in O(n log n) time using constant space complexity.

Linkedlist, Sort
 */
public class LT148_Sort_List {
    // Merge Sort.
    // Find center. Recursion left half and right half. then merge two sorted list.
    public ListNode sortList(ListNode head) {
	if (head == null || head.next == null)
	    return head;

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

	// recursion
	ListNode leftlist = null, rightlist = null;
	if (firsthalf != secondhalf) {
	    leftlist = sortList(firsthalf);
	    rightlist = sortList(secondhalf);
	}

	return mergeTwoLists(leftlist, rightlist);
    }

    public ListNode mergeTwoLists(ListNode leftlist, ListNode rightlist) {
	if (rightlist == null)
	    return leftlist;
	if (leftlist == null)
	    return rightlist;

	ListNode fakehead = new ListNode(-1);
	ListNode ptr = fakehead;
	while (rightlist != null && leftlist != null) {
	    if (rightlist.val < leftlist.val) {
		ptr.next = rightlist;
		ptr = ptr.next;
		rightlist = rightlist.next;
	    } else {
		ptr.next = leftlist;
		ptr = ptr.next;
		leftlist = leftlist.next;
	    }
	}

	if (rightlist != null)
	    ptr.next = rightlist;
	if (leftlist != null)
	    ptr.next = leftlist;

	return fakehead.next;
    }
}
