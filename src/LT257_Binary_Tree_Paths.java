
/*
Given a binary tree, return all root-to-leaf paths.
For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]

Tree, DFS
 */
import java.util.*;

import utils.TreeNode;

public class LT257_Binary_Tree_Paths {
    public List<String> binaryTreePaths(TreeNode root) {
	List<String> rst = new ArrayList<String>();
	if (root == null)
	    return rst;

	dfs(root, new StringBuilder(), rst);
	return rst;
    }

    private void dfs(TreeNode root, StringBuilder sb, List<String> rst) {
	if (root.left == null && root.right == null) {
	    sb.append(root.val);
	    rst.add(sb.toString());
	}

	sb.append(root.val);
	sb.append("->");

	if (root.left != null)
	    dfs(root.left, new StringBuilder(sb), rst);
	if (root.right != null)
	    dfs(root.right, new StringBuilder(sb), rst);
    }
}
