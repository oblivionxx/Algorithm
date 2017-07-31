/*
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 * Tree
 */
public class LT572_Subtree_of_Another_Tree {
    // use isSameTree
    public boolean isSubtree(TreeNode s, TreeNode t) {
	// find and then use isSameTree.
	if (s == null && t == null)
	    return true;
	if (s == null || t == null)
	    return false;
	if (isSameTree(s, t))
	    return true;

	return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
	if (p == null && q == null)
	    return true;
	if (p != null && q != null) {
	    return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	return false;
    }
}
