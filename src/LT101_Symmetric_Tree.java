
/*
 Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
 */
/*
 * Tree, DFS
 */
import java.util.*;

import common.TreeNode;

public class LT101_Symmetric_Tree {
    // iterative
    public boolean isSymmetric(TreeNode root) {
	if (root == null)
	    return true;
	if (root.left == null && root.right == null)
	    return true;
	if (root.left == null || root.right == null)
	    return false;
	LinkedList<TreeNode> q1 = new LinkedList<TreeNode>();
	LinkedList<TreeNode> q2 = new LinkedList<TreeNode>();
	q1.add(root.left);
	q2.add(root.right);
	while (!q1.isEmpty() && !q2.isEmpty()) {
	    TreeNode n1 = q1.poll();
	    TreeNode n2 = q2.poll();

	    if (n1 == null && n2 == null)
		continue;

	    if ((n1 == null && n2 != null) || (n1 != null && n2 == null))
		return false;

	    if (n1.val != n2.val)
		return false;

	    q1.add(n1.left);
	    q2.add(n2.right);

	    q1.add(n1.right);
	    q2.add(n2.left);

	}
	return true;
    }

    // recursive
    public boolean isSymmetric2(TreeNode root) {
	if (root == null)
	    return true;
	else
	    return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
	if (left == null && right == null)
	    return true;

	if (left == null || right == null)
	    return false;

	return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left); // pay attention here.

    }

}
