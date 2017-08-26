import utils.ListNode;

/*
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...

LinkedList
 */
public class LT328_Odd_Even_Linked_List {
    public ListNode oddEvenList(ListNode head) {
	if (head == null || head.next == null)
	    return head;

	ListNode odd = head;
	ListNode even = head.next, evenHead = head.next;
	while (even != null && even.next != null) {
	    odd.next = even.next; // 1.next -> 2.next=3
	    odd = odd.next; // 1 ->3
	    even.next = odd.next; // 2.next ->3.next = 4
	    even = even.next; // 2 ->4 so odd and even will be two list
	}

	odd.next = evenHead; // connect oddEnd with evenHead
	return head;
    }
}
