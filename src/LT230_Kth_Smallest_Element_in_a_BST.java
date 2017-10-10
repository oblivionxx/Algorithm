import java.util.Stack;

import common.TreeNode;

/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).

Tree, Binary Search
 */
public class LT230_Kth_Smallest_Element_in_a_BST {
    // use in-order iterative. pop k times, return result
    public int kthSmallest(TreeNode root, int k) {
	if (root == null)
	    return 0;

	Stack<TreeNode> stk = new Stack<TreeNode>();
	TreeNode cur = root;

	while (!stk.isEmpty() || cur != null) {
	    if (cur != null) {
		stk.push(cur); // inOrder push, go down to the left child
		cur = cur.left;
	    }
	    // if no left child
	    else {
		cur = stk.pop(); // get the last left child
		k--;
		if (k == 0) {
		    return cur.val;
		}
		cur = cur.right;
	    }
	}

	return 0;
    }

    // Recursive.
    int count = 0;
    int result = Integer.MIN_VALUE;

    public int kthSmallest2(TreeNode root, int k) {
	traverse(root, k);
	return result;
    }

    public void traverse(TreeNode root, int k) {
	if (root == null)
	    return;
	traverse(root.left, k);
	count++;
	if (count == k)
	    result = root.val;
	traverse(root.right, k);
    }
    
    //Follow up: 
    public int kthSmallest3(TreeNode root, int k) {
        int left = nodeCount(root.left);  // this value can be saved in the root node
        if(left + 1 == k) {
            return root.val;
        } else if (left + 1 < k) {
            return kthSmallest3(root.right, k - left - 1);
        } else {
            return kthSmallest3(root.left, k);
        }
    }
    
    private int nodeCount(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }
}
