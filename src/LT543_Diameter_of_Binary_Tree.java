import common.TreeNode;

/*
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * Tree
 */
public class LT543_Diameter_of_Binary_Tree {
    int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
	// post order
	// diameter of root =
	// max(root-left-dia，root-right-dia，root-left-depth+root-right-depth)
	maxDepth(root);
	return diameter;
    }

    public int maxDepth(TreeNode root) {
	if (root == null)
	    return 0;
	int left = maxDepth(root.left);
	int right = maxDepth(root.right);
	diameter = Math.max(diameter, left + right); // diameter on the right is
						     // the subtree diameter now. use height no need plus 1(count edges).
	return 1 + Math.max(left, right);
    }
}
