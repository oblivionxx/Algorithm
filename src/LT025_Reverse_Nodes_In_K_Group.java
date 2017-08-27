import common.ListNode;

/*
 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 You may not alter the values in the nodes, only nodes itself may be changed.
 Only constant memory is allowed.

 For example,
 Given this linked list: 	   1->2->3->4->5
 For k = 2, you should return: 2->1->4->3->5
 For k = 3, you should return: 3->2->1->4->5
 */
/*
 * Linkedlist
 */
public class LT025_Reverse_Nodes_In_K_Group {
    public ListNode reverseKGroup(ListNode head, int k) {
	// Iterative.
	/*
	 * 0->1->2->3->4->5->6 | | begin end after call begin = reverse(begin, end)
	 * 
	 * 0->3->2->1->4->5->6 | | begin end
	 * 
	 * @return the reversed list's 'begin' node, which is the precedence of node end
	 */
	if (head == null || k == 1)
	    return head;
	ListNode dummy = new ListNode(0);
	dummy.next = head;
	ListNode pre = dummy;
	ListNode cur = head;
	int count = 0;
	while (cur != null) {
	    count++;
	    ListNode next = cur.next;
	    if (count % k == 0) {
		pre = reverse(pre, next); // (0,4)-> new pre =1.
	    }
	    cur = cur.next;
	}

	return dummy.next;
    }

    public ListNode reverse(ListNode begin, ListNode end) {
	ListNode cur = begin.next; // 1
	ListNode next = cur.next; // 2
	while (next != end) {
	    // switch two node.
	    cur.next = next.next; // 1->3
	    next.next = begin.next; // 2->1
	    begin.next = next; // 0->2
	    next = cur.next; // next = 3.
	}
	return cur; // cur node is not updated. only update link.return new 1.
    }

    public ListNode reverseKGroupSol2(ListNode head, int k) {
	// Recursive.
	if (head == null || k == 1)
	    return head;
	ListNode cur = head;

	int count = 0;
	while (cur != null && count != k) {
	    cur = cur.next;
	    count++;
	}

	if (count == k) {
	    cur = reverseKGroupSol2(cur, k); // cur is k+1th node
	    // reverse the current K group
	    while (count-- > 0) {
		ListNode tmp = head.next;
		head.next = cur;
		cur = head;
		head = tmp;
	    }
	    head = cur;
	}

	return head;

    }
}
