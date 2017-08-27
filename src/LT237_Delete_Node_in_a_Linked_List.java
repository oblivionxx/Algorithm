import common.ListNode;

/*
Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function

Linkedlist
 */
public class LT237_Delete_Node_in_a_Linked_List {
    public void deleteNode(ListNode node) {
	// since it is singly linked, the link to the current node is not known
	// so MODIFY the value and link of the current node to the next next node.
	node.val = node.next.val;
	node.next = node.next.next;
    }
}
