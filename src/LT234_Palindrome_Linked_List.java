import utils.ListNode;

/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?

Linkedlist, Two Pointer
 */
public class LT234_Palindrome_Linked_List {
    public boolean isPalindrome(ListNode head) {
	// 1、遍历链表，快慢指针，找到链表后半部分。
	if (head == null || head.next == null)
	    return true;

	ListNode slow = head;
	ListNode fast = head;

	while (fast.next != null && fast.next.next != null) {
	    slow = slow.next;
	    fast = fast.next.next;
	}

	ListNode firsthalf = head;
	ListNode secondhalf = slow.next;
	slow.next = null; // break to two parts

	// 2、反转链表，可以参考Reverse Linked List

	ListNode reverse = reverseList(secondhalf);

	// 3、然后比较前半部分和后半部分的val值。
	while (firsthalf != null && reverse != null) {
	    if (firsthalf.val != reverse.val)
		return false;

	    firsthalf = firsthalf.next;
	    reverse = reverse.next;
	}

	return true;
    }

    public ListNode reverseList(ListNode head) {
	if (head == null || head.next == null)
	    return head;

	ListNode elm = head.next;
	head.next = null; // important

	ListNode reverseRest = reverseList(elm);
	elm.next = head;
	return reverseRest;
    }
}
