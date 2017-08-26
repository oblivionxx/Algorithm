
/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?

Tree, Stack
 */
import java.util.*;

import utils.TreeNode;

public class LT144_Binary_Tree_Preorder_Traversal {
    // 1. recursive
    public List<Integer> preorderTraversal(TreeNode root) {
	List<Integer> res = new ArrayList<Integer>();
	addList(root, res);
	return res;
    }

    private void addList(TreeNode root, List<Integer> result) {
	if (root == null)
	    return;
	else {
	    result.add(root.val);
	    addList(root.left, result);
	    addList(root.right, result);
	}
    }

    // 2. iterative.
    public List<Integer> preorderTraversal2(TreeNode root) {
	List<Integer> res = new ArrayList<Integer>();
	if (root == null)
	    return res;
	Stack<TreeNode> stk = new Stack<TreeNode>();
	TreeNode cur = root;

	while (!stk.isEmpty() || cur != null) {
	    if (cur != null) {
		res.add(cur.val); // preOrder visit first
		if (cur.right != null)
		    stk.push(cur.right);
		cur = cur.left;
	    } else {
		cur = stk.pop();
	    }
	}

	return res;
    }
}
