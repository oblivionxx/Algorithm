import common.ListNode;

/*
Sort a linked list using insertion sort.

LinkedList, Sort
 */
public class LT147_Insertion_Sort_List {

    public ListNode insertionSortList(ListNode head) {
	ListNode dummy = new ListNode(0); // new empty list for storing the sorted list
	ListNode pre = dummy;
	ListNode cur = head;
	// 因为是链表的插入操作，需要维护pre，cur和next3个指针。
	// pre始终指向sorted list的fakehead，cur指向当前需要被插入的元素，next指向下一个需要被插入的元素。

	while (cur != null) {
	    ListNode next = cur.next; // store for next position
	    // IMPORTANT using pre = dummy.
	    pre = dummy; // pre始终start from sorted list的fakehead. compare pre and cur.val.
	    while (pre.next != null && pre.next.val <= cur.val)
		pre = pre.next; // find the insert place. next value is larger than the value to be inserted

	    // insert cur between pre and pre.next
	    cur.next = pre.next;
	    pre.next = cur;
	    cur = next;
	}

	return dummy.next;
    }
}
