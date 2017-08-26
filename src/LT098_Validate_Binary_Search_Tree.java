/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Tree, DFS
 */
public class LT098_Validate_Binary_Search_Tree {
	public boolean isValidBST(TreeNode root) {
		return helper(root, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
	}

	private boolean helper(TreeNode root, double max, double min) {
		if (root == null)
			return true;
		if (root.val >= max || root.val <= min)
			return false;

		return helper(root.left, root.val, min) && helper(root.right, max, root.val);
	}
}
