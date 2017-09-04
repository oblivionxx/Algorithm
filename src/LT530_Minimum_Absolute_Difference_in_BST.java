import common.TreeNode;

/*
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
Note: There are at least two nodes in this BST.

BST
 */
public class LT530_Minimum_Absolute_Difference_in_BST {
    // inOrder traverse the tree and compare the delta between each of the
    // adjacent values. It's guaranteed to have the correct answer because it is
    // a BST thus inOrder traversal values are sorted. time complexity O(N),
    // space complexity O(1).

    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
	if (root == null)
	    return min;
	getMinimumDifference(root.left);
	if (prev != null) {
	    min = Math.min(min, root.val - prev);
	}
	prev = root.val;
	getMinimumDifference(root.right);
	return min;
    }

    // follow up. just BS. traverse the tree and then sort the values. get min diff.
    // or use treeset. time complexity O(NlgN), space complexity O(N).
    public static int getMinimumDifference2(TreeNode root) {
	int[] res = { Integer.MAX_VALUE };
	helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE, res);
	return res[0];
    }

    public static void helper(TreeNode node, int low, int high, int[] res) {
	if (node == null)
	    return;
	if (low != Integer.MIN_VALUE) {
	    // in the right subtree
	    res[0] = Math.min(res[0], node.val - low);
	}
	if (high != Integer.MAX_VALUE) {
	    // in the left subtree
	    res[0] = Math.min(res[0], high - node.val);
	}
	helper(node.left, low, node.val, res);
	helper(node.right, node.val, high, res);
    }

}
