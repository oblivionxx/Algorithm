import java.util.*;

import common.TreeNode;

/*
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.

Tree
 */
public class LT637_Average_of_Levels_in_Binary_Tree {
    public List<Double> averageOfLevels(TreeNode root) {
	List<Double> res = new ArrayList<>();
	Queue<TreeNode> queue = new LinkedList<>();
	if (root == null)
	    return null;
	queue.offer(root);
	while (!queue.isEmpty()) {
	    int size = queue.size();
	    long sum = 0;
	    for (int i = 0; i < size; i++) {
		TreeNode cur = queue.poll();
		sum += cur.val;
		if (cur.left != null)
		    queue.offer(cur.left);
		if (cur.right != null)
		    queue.offer(cur.right);
	    }
	    res.add((double) sum / size);
	}

	return res;
    }
}
