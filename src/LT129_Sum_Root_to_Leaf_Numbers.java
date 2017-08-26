import utils.TreeNode;

/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

Tree, DFS
 */
public class LT129_Sum_Root_to_Leaf_Numbers {
    public int sumNumbers(TreeNode root) {
	return sumUp(root, 0);
    }

    private int sumUp(TreeNode root, int res) {
	if (root == null)
	    return 0;

	if (root.left == null && root.right == null) // cannot remove this. case: 9. if remove this, will return 0 not 9.
	    return res * 10 + root.val;

	else
	    return sumUp(root.left, res * 10 + root.val) + sumUp(root.right, res * 10 + root.val);

    }
}
