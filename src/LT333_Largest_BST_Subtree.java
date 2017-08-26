import utils.TreeNode;

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
	// O(n^2)
	if (root == null)
	    return 0;
	if (isValid(root, Integer.MAX_VALUE, Integer.MIN_VALUE))
	    return count(root);
	else
	    return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    public int count(TreeNode n) {
	if (n == null)
	    return 0;
	return 1 + count(n.left) + count(n.right);
    }

    public boolean isValid(TreeNode n, int max, int min) {
	if (n == null)
	    return true;
	if (n.val < max && n.val > min)
	    return isValid(n.left, n.val, min) && isValid(n.right, max, n.val);
	else
	    return false;
    }

    // O(n)
    // Each subtree can decide if itself is a BST, and update a global size variable
    // Pass a 3-element array up. If subtree is not BST, size will be -1, and parent tree will not be BST
    private int largestBSTSubtreeSize = 0;

    public int largestBSTSubtree2(TreeNode root) {
	helper(root);
	return largestBSTSubtreeSize;
    }

    private int[] helper(TreeNode root) {
	// return 3-element array:
	// # of nodes in the subtree, leftmost value, rightmost value
	// # of nodes in the subtree will be -1 if it is not a BST
	int[] result = new int[3];
	if (root == null) {
	    return result;
	}
	int[] leftResult = helper(root.left);
	int[] rightResult = helper(root.right);
	if ((leftResult[0] == 0 || leftResult[0] > 0 && leftResult[2] < root.val)
		&& (rightResult[0] == 0 || rightResult[0] > 0 && rightResult[1] > root.val)) {
	    int size = 1 + leftResult[0] + rightResult[0];
	    largestBSTSubtreeSize = Math.max(largestBSTSubtreeSize, size);
	    int leftBoundary = leftResult[0] == 0 ? root.val : leftResult[1];
	    int rightBoundary = rightResult[0] == 0 ? root.val : rightResult[2];
	    result[0] = size;
	    result[1] = leftBoundary;
	    result[2] = rightBoundary;
	} else {
	    result[0] = -1;
	}
	return result;
    }
}
