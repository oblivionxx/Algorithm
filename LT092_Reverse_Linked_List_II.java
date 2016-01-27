/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

LinkedList
 */
public class LT092_Reverse_Linked_List_II {
	 public ListNode reverseBetween(ListNode head, int m, int n) {
        //get the mth element, then start to reverse until nth
        //反转的方法就是每读到一个结点，把它插入到m结点前面位置，然后m结点接到读到结点的下一个。
        //总共只需要一次扫描，所以时间是O(n)，只需要几个辅助指针，空间是O(1)。
        
        //Set up the head node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode preNode = dummy; 
        //find m-1 th node
        int count = 1;
        while(preNode.next!=null && m>count)
        {
            preNode = preNode.next;
            count++;
        }
        if(m>count)
            //already null
            return head;
        
        //reverse from the next node of preNode
        ListNode mNode = preNode.next;    //mth
        ListNode mCur = mNode.next;       //m+1 th
                                          //Next m+2th
        while(mCur!= null && n>count)
        {
            ListNode Next = mCur.next;    //next position
            mCur.next = preNode.next;       //reverse mNode and mCur
            preNode.next = mCur;
            mNode.next = Next;
            mCur = Next;                    //move to next position
            count++;
        }
        return dummy.next;
    }
}
