import java.util.LinkedList;

import common.TreeNode;

/*
 * Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.

Tree, DFS, BFS
 */
public class LT513_Find_Bottom_Left_Tree_Value {
    public int findBottomLeftValue(TreeNode root) {
	// right to left bfs return last node
	LinkedList<TreeNode> list = new LinkedList<>();
	list.add(root);
	while (!list.isEmpty()) {
	    root = list.poll();
	    if (root.right != null)
		list.add(root.right);
	    if (root.left != null)
		list.add(root.left);
	}

	return root.val;
    }

    // left to right bfs, remember 1st value
    public int findBottomLeftValue2(TreeNode root) {
	int result = 0;
	LinkedList<TreeNode> list = new LinkedList<>();
	list.add(root);
	while (!list.isEmpty()) {
	    int size = list.size();
	    for (int i = 0; i < size; i++) { // size of each level
		TreeNode node = list.poll();
		if (i == 0)
		    result = node.val; // save 1st value
		if (node.left != null)
		    list.add(node.left);
		if (node.right != null)
		    list.add(node.right);
	    }
	}
	return result;
    }

    int maxDepth = 0, res = 0;

    public int findBottomLeftValue3(TreeNode root) {
	// 2. recursion
	// 因为深度搜索时每层最左边的节点都是该层第一个被访问的节点，所以当访问到该节点时，节点层数会比记录的层数要大，这样就能知道此时找到的是某一层最左边的节点，因此更新最左节点值和层数，当遍历完成后，最左节点值记录就是最底层的最左叶子的值
	helper(root, 1);
	return res;
    }

    public void helper(TreeNode root, int curDepth) {
	if (root == null)
	    return;
	if (curDepth > maxDepth) {
	    maxDepth = curDepth; // leftmost node
	    res = root.val;
	}

	helper(root.left, curDepth + 1);
	helper(root.right, curDepth + 1);
    }
}
