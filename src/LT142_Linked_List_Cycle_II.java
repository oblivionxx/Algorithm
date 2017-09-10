import common.ListNode;

/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?

LinkedList, Two Pointer
 */
public class LT142_Linked_List_Cycle_II {
    public ListNode detectCycle(ListNode head) {
	ListNode fast = head;
	ListNode slow = head;

	while (true) { // while true here!! either go to the end = no cycle or fast/slow meet at a point
	    if (fast == null || fast.next == null)
		return null; // no cycle
	    slow = slow.next;
	    fast = fast.next.next;
	    if (fast == slow)
		break;
	}

	// if continue to this part, means exist cycle
	slow = head;
	while (slow != fast) {
	    slow = slow.next;
	    fast = fast.next;
	}

	return slow;
    }
}
