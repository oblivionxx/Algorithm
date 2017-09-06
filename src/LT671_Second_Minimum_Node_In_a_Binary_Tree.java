import common.TreeNode;

/*
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

Tree
 */
public class LT671_Second_Minimum_Node_In_a_Binary_Tree {
    int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
	inorder(root);
	return second == Integer.MAX_VALUE ? -1 : second;
    }

    public void inorder(TreeNode root) {
	if (root == null)
	    return;
	inorder(root.left);
	if (root.val < first) {
	    second = first;
	    first = root.val;
	} else if (root.val < second && root.val > first) {
	    second = root.val;
	}
	inorder(root.right);
    }
}
