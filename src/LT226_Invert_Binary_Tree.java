import java.util.LinkedList;
import java.util.Queue;

import common.TreeNode;

/*
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1

Tree
 */
public class LT226_Invert_Binary_Tree {
    public TreeNode invertTree(TreeNode root) {
	// Inplace recursive
	if (root == null)
	    return root;
	TreeNode tmp = root.left;
	root.left = invertTree(root.right);
	root.right = invertTree(tmp);

	return root;
    }

    // Iterative
    public TreeNode invertTree2(TreeNode root) {
	if (root == null)
	    return null;
	Queue<TreeNode> queue = new LinkedList<TreeNode>();
	queue.add(root);
	while (!queue.isEmpty()) {
	    TreeNode current = queue.poll();
	    TreeNode temp = current.left;
	    current.left = current.right;
	    current.right = temp;
	    if (current.left != null)
		queue.add(current.left);
	    if (current.right != null)
		queue.add(current.right);
	}
	return root;
    }
}
