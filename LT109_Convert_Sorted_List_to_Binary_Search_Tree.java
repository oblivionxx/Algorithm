/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
DFS, Linkedlist
 */
public class LT109_Convert_Sorted_List_to_Binary_Search_Tree {
	public TreeNode sortedListToBST(ListNode head) {
        return helper(head,null);      //head is start point, null is end point
    }
    
    private TreeNode helper(ListNode head, ListNode end){		//dont use end always=null. in this case, need to break slow.next = null. not necessary
        if(head==end)   return null;
        
        ListNode fast = head;
        ListNode slow = head;
        while(fast!=end && fast.next!=end){			//if length=odd. slow stop in odd center. fast=end.
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head, slow);
        root.right = helper(slow.next,end);
        
        return root;
    }
}
