import common.TreeNode;

/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
   
Tree
 */
public class LT156_Binary_Tree_Upside_Down {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
	TreeNode node = root, parent = null, right = null;
	while (node != null) {
	    TreeNode left = node.left;
	    node.left = right;
	    right = node.right;
	    node.right = parent;
	    parent = node;
	    node = left;
	}
	return parent;
    }

    public TreeNode upsideDownBinaryTree2(TreeNode root) {
	if (root == null || root.left == null) {
	    return root;
	}

	TreeNode newRoot = upsideDownBinaryTree(root.left);
	root.left.left = root.right; // node 2 left children
	root.left.right = root; // node 2 right children
	root.left = null;
	root.right = null;
	return newRoot;
	// https://discuss.leetcode.com/topic/40924/java-recursive-o-logn-space-and-iterative-solutions-o-1-space-with-explanation-and-figure/2
    }
}
