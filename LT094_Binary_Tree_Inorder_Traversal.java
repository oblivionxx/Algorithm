
/*
Given a binary tree, return the inorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?

Tree, HashTable, Stack
 */
import java.util.*;

public class LT094_Binary_Tree_Inorder_Traversal {
	// iterative
	public List<Integer> inorderTraversal1(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		Stack<TreeNode> stk = new Stack<TreeNode>();
		TreeNode cur = root;

		while (!stk.isEmpty() || cur != null) {
			if (cur != null) {
				stk.push(cur); // inOrder push left child.
				cur = cur.left;
			} else {
				cur = stk.pop(); // get the left child
				res.add(cur.val); // left child first
				cur = cur.right;
			}
		}

		return res;
	}

	// recursive
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		addList(root, res);
		return res;
	}

	private void addList(TreeNode root, List<Integer> result) {
		if (root == null)
			return;
		else {
			addList(root.left, result);
			result.add(root.val);
			addList(root.right, result);
		}
	}
}
