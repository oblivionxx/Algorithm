import common.ListNode;

/*
 You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 */

/*
 * Linkedlist, Math
 */
public class LT002_Add_Two_Numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	ListNode res = new ListNode(0);
	ListNode cur = res;
	int carry = 0;

	while (l1 != null || l2 != null || carry != 0) {
	    int sum = 0;
	    if (l1 != null) {
		sum += l1.val;
		l1 = l1.next; // move cursor
	    }
	    if (l2 != null) {
		sum += l2.val;
		l2 = l2.next;
	    }
	    sum += carry;
	    carry = sum / 10;
	    cur.next = new ListNode(sum % 10);
	    cur = cur.next; // move curNode. different with dummyHead
	}
	return res.next;
    }

}
