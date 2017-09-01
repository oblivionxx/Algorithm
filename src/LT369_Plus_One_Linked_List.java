import common.ListNode;

/*
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4

LinkedList
 */
public class LT369_Plus_One_Linked_List {
    public ListNode plusOne(ListNode head) {
	// 1. reverse. plus1 and reverse back
	if (head == null)
	    return null;
	ListNode cur = reverseList(head);
	ListNode dummy = cur;
	int carry = 1;
	while (cur != null) {
	    int sum = cur.val + carry;
	    carry = sum / 10;
	    cur.val = sum % 10;
	    if (cur.next == null)
		break; // otherwise. [9] null exception
	    cur = cur.next;

	}
	if (carry != 0)
	    cur.next = new ListNode(1);

	return reverseList(dummy);
    }

    public ListNode reverseList(ListNode head) {
	if (head == null)
	    return head;
	if (head.next == null)
	    return head;
	ListNode newHead = reverseList(head.next);
	head.next.next = head;
	head.next = null;
	return newHead;
    }
    
    //stack?
}
