import common.ListNode;

/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

Linkedlist
 */
public class LT160_Intersection_of_Two_Linked_Lists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	if (headA == null || headB == null) {
	    return null;
	}

	int len1 = getLength(headA);
	int len2 = getLength(headB);

	if (len1 > len2) {
	    while (len1 > len2) {
		headA = headA.next;
		len1--;
	    }
	}
	if (len2 > len1) {
	    while (len2 > len1) {
		headB = headB.next;
		len2--;
	    }
	}

	while (headA != null) {
	    if (headA == headB) {
		return headA;

	    }

	    headA = headA.next;
	    headB = headB.next;

	}
	return null;
    }

    private int getLength(ListNode head) {
	int len = 0;
	while (head != null) {
	    len++;
	    head = head.next;
	}
	return len;
    }
}
