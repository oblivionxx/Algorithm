import common.TreeNode;

/*
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
    
Tree
 */
public class LT450_Delete_Node_in_a_BST {
    public TreeNode deleteNode(TreeNode root, int key) { // find the node with
							 // val=key
	if (root == null) {
	    return null;
	}

	if (root.val == key) {
	    return deleteRoot(root);
	} else if (root.val > key) {
	    root.left = deleteNode(root.left, key);
	} else {
	    root.right = deleteNode(root.right, key);
	}
	return root;
    }

    public TreeNode deleteRoot(TreeNode root) { // reorder the tree.
	if (root.left == null) {
	    return root.right;
	} else {
	    if (root.right == null) {
		return root.left;
	    }
	    TreeNode node = root.left; // get the max in left sub tree. append
				       // root.right to the left subtree.
	    while (node.right != null) {
		node = node.right;
	    }
	    node.right = root.right; // use root.left as new root.
	    return root.left;
	}
    }
}
