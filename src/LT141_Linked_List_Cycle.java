import utils.ListNode;

/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?

Linkedlist, Two Pointer
 */
public class LT141_Linked_List_Cycle {
    public boolean hasCycle(ListNode head) {
	// 复杂度O(n)的方法，使用两个指针slow,fast。两个指针都从表头开始走，slow每次走一步，fast每次走两步，如果fast遇到null，则说明没有环，返回false；如果slow==fast，说明有环，并且此时fast超了slow一圈，返回true。

	ListNode fast = head;
	ListNode slow = head;

	while (fast != null && fast.next != null) {
	    slow = slow.next;
	    fast = fast.next.next;
	    if (fast == slow)
		return true;
	}

	return false;

    }
}
