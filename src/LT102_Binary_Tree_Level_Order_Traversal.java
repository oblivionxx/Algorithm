
/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

Tree, BFS
 */
import java.util.*;

import utils.TreeNode;

public class LT102_Binary_Tree_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
	List<List<Integer>> res = new ArrayList<>();
	if (root == null)
	    return res;

	LinkedList<TreeNode> queue = new LinkedList<>();
	queue.add(root);
	while (!queue.isEmpty()) {
	    int size = queue.size();
	    List<Integer> item = new ArrayList<>();
	    for (int i = 0; i < size; i++) {
		TreeNode cur = queue.poll();
		if (cur.left != null) {
		    queue.add(cur.left);
		}
		if (cur.right != null) {
		    queue.add(cur.right);
		}

		item.add(cur.val);
	    }
	    res.add(item);
	}

	return res;
    }
}
