import java.util.*;

/*
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

Divide and Conquer, Binary Index Tree, Segment Tree, BST
 */
public class LT315_Count_of_Smaller_Numbers_After_Self {
    // Traverse from nums[len - 1] to nums[0], and build a binary search tree,
    // which stores:
    // val: value of nums[i]
    // count: if val == root.val, there will be count number of smaller numbers
    // on the right
    public List<Integer> countSmaller(int[] nums) {
	List<Integer> res = new ArrayList<>();
	if (nums == null || nums.length == 0)
	    return res;
	TreeNode root = new TreeNode(nums[nums.length - 1]);
	res.add(0); // only the last one. count is not 1 as default
	for (int i = nums.length - 2; i >= 0; i--) {
	    int count = insertNode(root, nums[i]);
	    res.add(count);
	}
	Collections.reverse(res);
	return res;
    }

    public int insertNode(TreeNode root, int val) {
	int thisCount = 0;
	while (true) {
	    if (val <= root.val) { // [1,3]->starting from root 3.
		root.count++; // root.count save the number of asc nums on the
			      // right. count updated. but not stored in res
			      // list
		if (root.left == null) {
		    root.left = new TreeNode(val); // put in the BST. left side
		    break;
		} else {
		    root = root.left;
		}
	    } else {
		thisCount += root.count; // thisCount is used for count for new
					 // Number. default =1
		if (root.right == null) {
		    root.right = new TreeNode(val);
		    break;
		} else {
		    root = root.right;
		}
	    }
	}
	return thisCount;
    }

    class TreeNode {
	TreeNode left;
	TreeNode right;
	int val;
	int count = 1;

	public TreeNode(int val) {
	    this.val = val;
	}
    }

    // Naive O(n^2) way TLE
    public List<Integer> countSmaller2(int[] nums) {
	List<Integer> res = new ArrayList<>();
	for (int i = 0; i < nums.length; i++) {
	    int count = 0;
	    for (int j = i + 1; j < nums.length; j++) {
		if (nums[j] < nums[i])
		    count++;
	    }
	    res.add(count);
	}

	return res;
    }
}
