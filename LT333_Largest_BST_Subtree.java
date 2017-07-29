/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?

Tree
 */
public class LT333_Largest_BST_Subtree {
	public int largestBSTSubtree(TreeNode root) {
        //O(n^2)
        if(root == null) return 0;
        if(isValid(root, Integer.MAX_VALUE, Integer.MIN_VALUE)) 
            return count(root);
        else 
            return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }
    
   public int count (TreeNode n){
        if(n == null)
            return 0;
        return 1 + count(n.left) + count(n.right);
   }
   
    public boolean isValid(TreeNode n, int max, int min){
        if(n == null) return true;
        if(n.val < max && n.val > min) 
            return isValid(n.left, n.val, min) && isValid(n.right, max, n.val);
        else 
            return false;
    }
    
    //O(n)???
}
