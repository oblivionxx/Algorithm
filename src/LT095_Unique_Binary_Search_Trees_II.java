
/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
Tree, DP  
 */
import java.util.*;

import common.TreeNode;

public class LT095_Unique_Binary_Search_Trees_II {
    public List<TreeNode> generateTrees(int n) {
	// 以i为根节点的树，其左子树由[1, i-1]构成， 其右子树由[i+1, n]构成
	return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int left, int right) {
	List<TreeNode> res = new ArrayList<TreeNode>();
	if (left > right) {
	    res.add(null);
	    return res;
	}

	for (int i = left; i <= right; i++) {
	    List<TreeNode> lefts = generateTrees(left, i - 1);
	    List<TreeNode> rights = generateTrees(i + 1, right);
	    // permutation then store into res
	    for (int j = 0; j < lefts.size(); j++)
		for (int k = 0; k < rights.size(); k++) {
		    TreeNode newTree = new TreeNode(i);
		    newTree.left = lefts.get(j);
		    newTree.right = rights.get(k);
		    res.add(newTree);
		}
	}

	return res;
    }
}
