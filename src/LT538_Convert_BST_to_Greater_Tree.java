import java.util.Stack;

import common.TreeNode;

/*
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
          
Tree
 */
public class LT538_Convert_BST_to_Greater_Tree {
    // recursive
    // do a reverse inorder traversal to traverse the nodes of the tree in
    // descending order. In the process, we keep track of the running sum of all
    // nodes which we have traversed thus far.
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
	if (root == null)
	    return null;

	convertBST(root.right);
	root.val += sum;
	sum = root.val;
	convertBST(root.left);

	return root;
    }

    public TreeNode convertBST2(TreeNode root) {
	// iterative
	if (root == null)
	    return root;
	Stack<TreeNode> stk = new Stack<TreeNode>();
	TreeNode cur = root;
	int res = 0;
	while (!stk.isEmpty() || cur != null) {
	    if (cur != null) {
		stk.push(cur); // inOrder push right child.
		cur = cur.right;
	    } else {
		cur = stk.pop(); // get the right child
		int add = cur.val;
		cur.val += res; // res track the sum on the right
		res += add;
		cur = cur.left;
	    }
	}

	return root;
    }
}
