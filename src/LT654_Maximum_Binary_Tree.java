import common.TreeNode;

/*
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].

Tree
 */
public class LT654_Maximum_Binary_Tree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
	return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int l, int r) {
	if (l > r)
	    return null;
	int max = nums[l], index = l;
	for (int i = l; i <= r; i++) {
	    if (nums[i] > max) {
		index = i;
		max = nums[i];
	    }
	}
	TreeNode root = new TreeNode(max);
	root.left = helper(nums, l, index - 1);
	root.right = helper(nums, index + 1, r);

	return root;
    }
}
