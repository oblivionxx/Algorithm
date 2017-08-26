import utils.TreeNode;

/*
Given a complete binary tree, count the number of nodes.
Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Tree, Binary Search
 */
public class LT222_Count_Complete_Tree_Nodes {
    public int countNodes(TreeNode root) {
	// 从某节点一直向左的高度 = 一直向右的高度, 那么以该节点为root的子树一定是complete binary tree. 而 complete binary tree的节点数,可以用公式算出 2^h - 1. 如果高度不相等, 则递归调用 return countNode(left) + countNode(right) + 1.
	// 复杂度为O(h^2), 满二叉树一定是完全二叉树
	if (root == null)
	    return 0;
	int hl = 0, hr = 0;
	TreeNode l = root, r = root;
	while (l != null) {
	    hl++;
	    l = l.left;
	}
	while (r != null) {
	    hr++;
	    r = r.right;
	}
	if (hl == hr) {
	    return (2 << (hl - 1)) - 1;
	}

	return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
