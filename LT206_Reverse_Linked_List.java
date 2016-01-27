/*
Reverse a singly linked list.

Hint:
A linked list can be reversed either iteratively or recursively. Could you implement both?

Linkedlist
 */
public class LT206_Reverse_Linked_List {

	//1.recursive
   public ListNode reverseList(ListNode head) {
        if(head==null || head.next ==null) return head;
        
        // recursively
        //What is the reverse of null (the empty list)? null.
        //What is the reverse of a one element list? the element.
        //What is the reverse of an n element list? the reverse of the second element on followed by the first element.
        ListNode elm = head.next;
        head.next = null; //important. head is now the end.
        
        ListNode reverseRest = reverseList(elm);		//start from reverseRest, end at elm.
        elm.next = head;								//elm is the reverseRest's end node.
        return reverseRest;
    }
   
   //2. iterative. recheck
   public ListNode reverseList2(ListNode head) {
       if(head==null || head.next ==null) return head;
       
       ListNode next = null;
       ListNode cur = null;
       
       while(head!=null){
           next = head.next;
           head.next = cur;
           cur = head;
           head = next;
       }
       
       return cur;
   }
}
