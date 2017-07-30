import java.util.*;

/*
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();

Reservoir Sampling
 */
public class LT382_Linked_List_Random_Node {
    /**
     * @param head
     * The linked list's head. Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    ListNode head;
    Random random;

    public LT382_Linked_List_Random_Node(ListNode head) {
        //http://blog.jobbole.com/42550/
        this.head = head;       
        random = new Random();     
    }

    /** Returns a random node's value. */
    public int getRandom() {
	// random. 1/i possibility to replace the value
	ListNode c = head;
	int r = c.val;
	for (int i = 1; c.next != null; i++) { // read value in the stream
	    c = c.next;
	    if (random.nextInt(i + 1) == i) // random between [0,i+1)
		r = c.val; // return value with 1/i possibility
	}

	return r;
    }
}
