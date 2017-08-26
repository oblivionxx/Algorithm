import java.util.Comparator;
import java.util.PriorityQueue;

/*
 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 Divide and Conquer, LinkedList, Heap
 */

public class LT023_Merge_k_Sorted_Lists {
	// 1. Divide and Conquer
	public ListNode mergeKLists(ListNode[] lists) {
		// O(nlgk).n is total number of node. O(n) for merge two list. O(lgk) is
		// for divide k.
		if (lists == null || lists.length == 0)
			return null;
		return mergeKLists(lists, 0, lists.length - 1);
	}

	public ListNode mergeKLists(ListNode[] lists, int l, int r) {
		if (l < r) {
			int mid = (l + r) / 2;
			return mergeTwoLists(mergeKLists(lists, l, mid), mergeKLists(lists, mid + 1, r));
		}

		return lists[l];
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy; // dont = dummy.next here.
		ListNode cur1 = l1;
		ListNode cur2 = l2;
		while (cur1 != null && cur2 != null) {
			if (cur1.val < cur2.val) {
				cur.next = cur1;
				cur1 = cur1.next;
			} else {
				cur.next = cur2;
				cur2 = cur2.next;
			}
			cur = cur.next;
		}
		if (cur1 != null)
			cur.next = cur1;

		if (cur2 != null)
			cur.next = cur2;
		return dummy.next;
	}

	// 2. Heap and PriorityQueue
	public ListNode mergeKListsSol2(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			public int compare(ListNode l1, ListNode l2) {
				return l1.val - l2.val;
			}
		});

		for (ListNode elm : lists) {
			if (elm != null)
				minHeap.offer(elm);
		}

		while (!minHeap.isEmpty()) {
			ListNode tmp = minHeap.poll();
			cur.next = tmp;
			if (tmp.next != null)
				minHeap.offer(tmp.next);
			cur = tmp;
		}

		return dummy.next;
	}
}
