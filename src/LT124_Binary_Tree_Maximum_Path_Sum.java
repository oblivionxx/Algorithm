import utils.TreeNode;

/*
Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.

Tree, DFS
 */
public class LT124_Binary_Tree_Maximum_Path_Sum {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
	help(root);
	return max;
    }

    public int help(TreeNode root) { // maxPathDownwards. returns the maximum pathSum of left/right subtree, not across root
	if (root == null)
	    return 0;
	int left = Math.max(0, help(root.left)); // be careful. pathSum could be negative.
	int right = Math.max(0, help(root.right));
	max = Math.max(root.val + left + right, max); // path across left, root, right
	return root.val + Math.max(left, right);
    }
}
