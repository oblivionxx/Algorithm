import java.util.LinkedList;

import utils.TreeNode;

/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Tree, BFS, DFS
 */
public class LT111_Minimum_Depth_Of_Binary_Tree {
    // recursive
    public int minDepth(TreeNode root) {

	if (root == null)
	    return 0;
	if (root.left == null && root.right == null)
	    return 1;

	if (root.left == null)
	    return 1 + minDepth(root.right);
	else if (root.right == null)
	    return 1 + minDepth(root.left);
	else
	    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    // iterative
    public int minDepth2(TreeNode root) {
	int level = 0;
	LinkedList<TreeNode> queue = new LinkedList<>();
	queue.add(root);
	while (!queue.isEmpty() && root != null) {
	    int size = queue.size();
	    for (int i = 0; i < size; i++) {
		TreeNode cur = queue.poll();
		if (cur.left == null && cur.right == null)
		    return 1 + level;
		if (cur.left != null) {
		    queue.add(cur.left);
		}
		if (cur.right != null) {
		    queue.add(cur.right);
		}
	    }
	    level++;
	}

	return level;
    }
}