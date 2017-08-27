
/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

Tree, DFS
 */
import java.util.*;

import common.TreeNode;

public class LT113_Path_Sum_II {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
	List<List<Integer>> rst = new ArrayList<List<Integer>>();
	if (root == null)
	    return rst;

	dfs(root, sum, rst, new ArrayList<Integer>());
	return rst;
    }

    public void dfs(TreeNode root, int sum, List<List<Integer>> rst, List<Integer> cur) {

	cur.add(root.val);
	if (root.left == null && root.right == null && sum == root.val) {
	    rst.add(cur);
	    return;
	}

	if (root.left != null)
	    dfs(root.left, sum - root.val, rst, new ArrayList<Integer>(cur));
	if (root.right != null)
	    dfs(root.right, sum - root.val, rst, new ArrayList<Integer>(cur));

    }

}
