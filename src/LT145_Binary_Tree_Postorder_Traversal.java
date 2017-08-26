
/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?

Tree, Stack
 */
import java.util.*;

import utils.TreeNode;

public class LT145_Binary_Tree_Postorder_Traversal {
    // recursion
    public List<Integer> postorderTraversal(TreeNode root) {
	List<Integer> res = new ArrayList<Integer>();
	addList(root, res);
	return res;
    }

    private void addList(TreeNode root, List<Integer> res) {
	if (root == null)
	    return;
	else {
	    addList(root.left, res);
	    addList(root.right, res);
	    res.add(root.val);
	}
    }

    // iterative recheck
    public List<Integer> postorderTraversal2(TreeNode root) {
	List<Integer> lst = new ArrayList<Integer>();

	if (root == null)
	    return lst;

	Stack<TreeNode> stack = new Stack<TreeNode>();
	stack.push(root);

	TreeNode prev = null;
	while (!stack.empty()) {
	    TreeNode curr = stack.peek();

	    // go down the tree.
	    // check if current node is leaf, if so, process it and pop stack,
	    // otherwise, keep going down
	    if (prev == null || prev.left == curr || prev.right == curr) {
		// prev == null is the situation for the root node
		if (curr.left != null) {
		    stack.push(curr.left);
		} else if (curr.right != null) {
		    stack.push(curr.right);
		} else {
		    stack.pop();
		    lst.add(curr.val);
		}

		// go up the tree from left node
		// need to check if there is a right child
		// if yes, push it to stack
		// otherwise, process parent and pop stack
	    } else if (curr.left == prev) {
		if (curr.right != null) {
		    stack.push(curr.right);
		} else {
		    stack.pop();
		    lst.add(curr.val);
		}

		// go up the tree from right node
		// after coming back from right node, process parent node and pop stack.
	    } else if (curr.right == prev) {
		stack.pop();
		lst.add(curr.val);
	    }
	    prev = curr;
	}

	return lst;
    }
}
