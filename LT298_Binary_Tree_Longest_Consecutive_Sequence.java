/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

Tree
 */
public class LT298_Binary_Tree_Longest_Consecutive_Sequence {
	public int longestConsecutive(TreeNode root) {				//Increasing from parent to child node
        int[] max = new int[]{0};
        helper(root, max);
        return max[0];
        
    }
    
    public int helper(TreeNode root, int[] max){
        if(root==null) return 0;
        int res = 1;
        int left = helper(root.left, max);
        int right = helper(root.right, max);
        if (root.left == null || root.val + 1 != root.left.val) {
            left = 0;
        }
        if (root.right == null || root.val + 1 != root.right.val) {
            right = 0;
        }
        res += Math.max(right, left);
        max[0] = Math.max(res, max[0]);
        return res;
    
    }
}
