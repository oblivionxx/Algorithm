import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         / 
        2          
2. Now removing the leaf [2] would result in this tree:

          1          
3. Now removing the leaf [1] would result in the empty tree:

          []         
Returns [4, 5, 3], [2], [1].

Tree, DFS
 */
public class LT366_Find_Leaves_of_Binary_Tree {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
	helper(root);
	return res;
    }

    private int helper(TreeNode node) {
	if (null == node) {
	    return -1;
	}
	int level = 1 + Math.max(helper(node.left), helper(node.right)); // during the time we calculate height. add the node to corresponding level
	if (res.size() < level + 1) {
	    res.add(new ArrayList<Integer>()); // create a list for each level
	}
	res.get(level).add(node.val); // and leaf to correponding level. 4.5 at level 0. 3 at level 0
	return level;
    }
}
