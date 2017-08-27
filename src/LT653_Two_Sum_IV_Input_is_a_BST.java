import java.util.HashSet;

import common.TreeNode;

/*
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False

Tree
 */
public class LT653_Two_Sum_IV_Input_is_a_BST {
    // traverse put val in hashset and loop for k-val
    public boolean findTarget(TreeNode root, int k) {
	HashSet<Integer> set = new HashSet<>();
	return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k) {
	if (root == null)
	    return false;
	if (set.contains(k - root.val))
	    return true;
	set.add(root.val);
	return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}
