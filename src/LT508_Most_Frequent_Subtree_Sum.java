import java.util.HashMap;

import utils.TreeNode;

/*
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.

Tree, Hash Table
 */
public class LT508_Most_Frequent_Subtree_Sum {
    HashMap<Integer, Integer> map = new HashMap<>();
    int maxCount = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
	subTreeSum(root);
	return map.entrySet().stream().filter(map -> map.getValue() == maxCount).mapToInt(map -> map.getKey())
		.toArray();
    }

    public int subTreeSum(TreeNode root) {
	if (root == null)
	    return 0;
	int total = subTreeSum(root.left) + subTreeSum(root.right) + root.val;
	map.put(total, map.getOrDefault(total, 0) + 1);
	maxCount = Math.max(maxCount, map.get(total));
	return total;
    }
}
