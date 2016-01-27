/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.

HashTable, Linkedlist
 */
public class LT138_Copy_List_with_Random_Pointer {
	public RandomListNode copyRandomList(RandomListNode head) {
        
    }
}

class RandomListNode {
	int label;
	RandomListNode next, random;
	RandomListNode(int x){ 
		this.label = x; 
	}
}