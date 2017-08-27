import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import common.TreeNode;

/*
 * You need to find the largest value in each row of a binary tree.
 * 
 * Tree, BFS, DFS
 */
public class LT515_Find_Largest_Value_in_Each_Tree_Row {
    public List<Integer> largestValues(TreeNode root) {
	// level order. save localMax of each level into a list
	List<Integer> res = new ArrayList<>();
	if (root == null)
	    return res;

	LinkedList<TreeNode> queue = new LinkedList<>();
	queue.add(root);

	while (!queue.isEmpty()) {
	    int size = queue.size();
	    int levelMax = Integer.MIN_VALUE;
	    for (int i = 0; i < size; i++) {
		TreeNode cur = queue.poll();
		levelMax = Math.max(levelMax, cur.val);
		if (cur.left != null)
		    queue.add(cur.left);
		if (cur.right != null)
		    queue.add(cur.right);
	    }

	    res.add(levelMax);
	}

	return res;

    }
}
