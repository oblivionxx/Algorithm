import java.util.HashMap;

import common.TreeNode;

/*
Given preorder and inorder traversal of a tree, construct the binary tree.
Tree, Array, DFS
 */
public class LT105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
	HashMap<Integer, Integer> map = new HashMap<>();
	for (int i = 0; i < inorder.length; i++) { // use inorder as a map.
	    map.put(inorder[i], i);
	}

	return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    public TreeNode helper(int[] pre, int preL, int preR, int[] in, int inL, int inR, HashMap<Integer, Integer> map) {
	if (preL > preR || inL > inR)
	    return null; // important.
	TreeNode root = new TreeNode(pre[preL]);
	int index = map.get(root.val);

	// from inorder find how many elements for left tree and right tree.
	// inorder index is root. left->[inL, index-1]. has index-inL-1 elements. right->[index+1, inR].
	// preorder preL is root. left -> preL+1~(left_elements) preL+index-inL. right->preL+index-inL+1~preR
	root.left = helper(pre, preL + 1, preL + index - inL, in, inL, index - 1, map); // be careful. index-1. index+1. since index is root.
	root.right = helper(pre, preL + index - inL + 1, preR, in, index + 1, inR, map);

	return root;
    }
}
