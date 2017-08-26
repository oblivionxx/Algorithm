
/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

Tree
 */

import java.util.*;

import utils.TreeNode;

public class LT236_Lowest_Common_Ancestor_of_a_Binary_Tree {
    // recursive
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	// If the current (sub)tree contains both p and q, then the function result is their LCA. If only one of them is in that subtree, then the result is that one of them. If neither are in that
	// subtree, the result is null/None/nil.
	if (root == null || root == p || root == q)
	    return root;
	TreeNode left = lowestCommonAncestor(root.left, p, q);
	TreeNode right = lowestCommonAncestor(root.right, p, q);
	return left == null ? right : (right == null ? left : root);

    }

    // iterative with parent pointer idea.
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
	Map<TreeNode, TreeNode> parent = new HashMap<>();
	Deque<TreeNode> stack = new ArrayDeque<>();
	parent.put(root, null);
	stack.push(root);

	// end up when meet p and q. so parent keep all the parent node along the path to p and q.
	while (!parent.containsKey(p) || !parent.containsKey(q)) {
	    TreeNode node = stack.pop();
	    if (node.left != null) {
		parent.put(node.left, node);
		stack.push(node.left);
	    }
	    if (node.right != null) {
		parent.put(node.right, node);
		stack.push(node.right);
	    }
	}

	// After we found both p and q, we create a set of p's ancestors. Then we travel through q's ancestors, the first one appears in p's is our answer.
	Set<TreeNode> ancestors = new HashSet<>();
	while (p != null) {
	    ancestors.add(p);
	    p = parent.get(p);
	}

	while (!ancestors.contains(q))
	    q = parent.get(q);

	return q;
    }
}
