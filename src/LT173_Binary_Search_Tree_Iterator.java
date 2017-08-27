import java.util.Stack;

import common.TreeNode;

/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
Calling next() will return the next smallest number in the BST.
Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Tree, Stack, Design
 */
public class LT173_Binary_Search_Tree_Iterator {
    // Ineorder
    Stack<TreeNode> stk = new Stack<>();

    public LT173_Binary_Search_Tree_Iterator(TreeNode root) {
	while (root != null) {
	    stk.push(root);
	    root = root.left;
	}
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
	return !stk.isEmpty();

    }

    /** @return the next smallest number */
    public int next() {
	TreeNode cur = stk.pop();
	int res = cur.val;
	if (cur.right != null) {
	    cur = cur.right;
	    while (cur != null) {
		stk.push(cur);
		cur = cur.left;
	    }
	}
	return res;
    }
}

/**
 * Your BSTIterator will be called like this: BSTIterator i = new BSTIterator(root); while (i.hasNext()) v[f()] = i.next();
 */
