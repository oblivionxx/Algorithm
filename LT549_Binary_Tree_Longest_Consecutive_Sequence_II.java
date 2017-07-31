/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].

Tree
 */
public class LT549_Binary_Tree_Longest_Consecutive_Sequence_II {
    public int longestConsecutive(TreeNode root) {
	helper(root);
	return maxlen;
    }

    private int maxlen = 0;

    private int[] helper(TreeNode root) { // longest consecutive path length.
					  // store both inc and dec
	if (root == null)
	    return new int[] { 0, 0 };
	int inc = 1, dec = 1;
	int[] left = helper(root.left), right = helper(root.right);
	if (root.left != null) {
	    if (root.left.val == root.val + 1)
		inc += left[0]; // if not consecutive. then inc = dec =1
	    if (root.left.val == root.val - 1)
		dec += left[1];
	}
	if (root.right != null) {
	    if (root.right.val == root.val + 1)
		inc = Math.max(inc, 1 + right[0]);
	    if (root.right.val == root.val - 1)
		dec = Math.max(dec, 1 + right[1]);
	}
	maxlen = Math.max(inc + dec - 1, maxlen); // update maxLen =
						  // left+right-1
	return new int[] { inc, dec };
    }
}
