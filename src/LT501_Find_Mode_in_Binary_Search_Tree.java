import java.util.*;

import utils.TreeNode;

/*
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

Tree
 */
public class LT501_Find_Mode_in_Binary_Search_Tree {
    private HashMap<Integer, Integer> map = new HashMap<>();
    int max = 0;

    public int[] findMode(TreeNode root) {
	// 1. do one traversial. count the most freq occurred number. using extra space
	if (root == null)
	    return new int[0];
	inorder(root);

	List<Integer> list = new LinkedList<>();
	for (int i : map.keySet()) {
	    if (map.get(i) == max)
		list.add(i);
	}

	int[] res = new int[list.size()];
	for (int i = 0; i < res.length; i++)
	    res[i] = list.get(i);
	return res;
    }

    public void inorder(TreeNode node) {
	if (node != null) {
	    inorder(node.left);
	    map.put(node.val, map.getOrDefault(node.val, 0) + 1);
	    max = Math.max(max, map.get(node.val));
	    inorder(node.right);
	}
    }
}
