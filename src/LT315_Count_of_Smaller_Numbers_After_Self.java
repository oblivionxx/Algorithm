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
    // 1. Naive O(n^2) way TLE
    public List<Integer> countSmaller(int[] nums) {
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

    // 2. Merge Sort
    // Example: nums = [5,2,6,1], indexes = [0,1,2,3]
    // After sort:indexes=[3,1,0,2]
    // While doing the merge part, say that we are merging left[] and right[], left[] and right[] are already sorted.
    // We keep a rightcount to record how many numbers from right[] we have added and keep an array count[] to record the result.
    // When we move a number from right[] into the new sorted array, we increase rightcount by 1.
    // When we move a number from left[] into the new sorted array, we increase count[ index of the number ] by rightcount.
    // new_indexes is a temporary subarray that was created for doing the merge. After the merge was done successfully on the temporary subarray new_indexes, he is overwriting elements of indexes
    // array with the appropriate elements from the temporary subarray new_indexes
    int[] count;

    public List<Integer> countSmaller2(int[] nums) {
	List<Integer> res = new ArrayList<Integer>();
	count = new int[nums.length];
	int[] indexes = new int[nums.length];
	for (int i = 0; i < nums.length; i++) {
	    indexes[i] = i;
	}
	mergesort(nums, indexes, 0, nums.length - 1);
	for (int i = 0; i < count.length; i++) {
	    res.add(count[i]);
	}
	return res;
    }

    private void mergesort(int[] nums, int[] indexes, int start, int end) {
	if (end <= start) {
	    return;
	}
	int mid = (start + end) / 2;
	mergesort(nums, indexes, start, mid);
	mergesort(nums, indexes, mid + 1, end);
	merge(nums, indexes, start, end);
    }

    private void merge(int[] nums, int[] indexes, int start, int end) {
	int mid = (start + end) / 2;
	int left_index = start;
	int right_index = mid + 1;
	int rightcount = 0;
	int[] new_indexes = new int[end - start + 1];

	int sort_index = 0;
	while (left_index <= mid && right_index <= end) { 		// merge into new array new_indexes
	    if (nums[indexes[left_index]] > nums[indexes[right_index]]) {
		new_indexes[sort_index] = indexes[right_index];		
		rightcount++; 						// merge right part to new array. 我们需要记录的，是右侧比左侧小的个数
		right_index++;
	    } else {
		new_indexes[sort_index] = indexes[left_index];
		count[indexes[left_index]] += rightcount; 		// update count[left]
		left_index++;
	    }
	    sort_index++;
	}
	while (left_index <= mid) { 					// left part is larger numbers. add to the end.
	    new_indexes[sort_index] = indexes[left_index];
	    count[indexes[left_index]] += rightcount;
	    left_index++;
	    sort_index++;
	}
	while (right_index <= end) {
	    new_indexes[sort_index++] = indexes[right_index++];
	}
	for (int i = start; i <= end; i++) {
	    indexes[i] = new_indexes[i - start];
	}
    }

    // 3. Segment Tree
    static class segmentTreeNode {
	int start, end, count;
	segmentTreeNode left, right;

	segmentTreeNode(int start, int end, int count) {
	    this.start = start;
	    this.end = end;
	    this.count = count;
	    left = null;
	    right = null;
	}
    }

    public static List<Integer> countSmaller3(int[] nums) {
	// write your code here
	List<Integer> result = new ArrayList<Integer>();

	int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	for (int i : nums) {
	    min = Math.min(min, i);

	}
	if (min < 0) {
	    for (int i = 0; i < nums.length; i++) {
		nums[i] -= min;// deal with negative numbers, seems a dummy way
	    }
	}
	for (int i : nums) {
	    max = Math.max(max, i);
	}
	segmentTreeNode root = build(0, max);
	for (int i = 0; i < nums.length; i++) {
	    updateAdd(root, nums[i]);
	}
	for (int i = 0; i < nums.length; i++) {
	    updateDel(root, nums[i]);
	    result.add(query(root, 0, nums[i] - 1));
	}
	return result;
    }

    public static segmentTreeNode build(int start, int end) {
	if (start > end)
	    return null;
	if (start == end)
	    return new segmentTreeNode(start, end, 0);
	int mid = (start + end) / 2;
	segmentTreeNode root = new segmentTreeNode(start, end, 0);
	root.left = build(start, mid);
	root.right = build(mid + 1, end);
	root.count = root.left.count + root.right.count;
	return root;
    }

    public static int query(segmentTreeNode root, int start, int end) {
	if (root == null)
	    return 0;
	if (root.start == start && root.end == end)
	    return root.count;
	int mid = (root.start + root.end) / 2;
	if (end < mid) {
	    return query(root.left, start, end);
	} else if (start > end) {
	    return query(root.right, start, end);
	} else {
	    return query(root.left, start, mid) + query(root.right, mid + 1, end);
	}
    }

    public static void updateAdd(segmentTreeNode root, int val) {
	if (root == null || root.start > val || root.end < val)
	    return;
	if (root.start == val && root.end == val) {
	    root.count++;
	    return;
	}
	int mid = (root.start + root.end) / 2;
	if (val <= mid) {
	    updateAdd(root.left, val);
	} else {
	    updateAdd(root.right, val);
	}
	root.count = root.left.count + root.right.count;
    }

    public static void updateDel(segmentTreeNode root, int val) {
	if (root == null || root.start > val || root.end < val)
	    return;
	if (root.start == val && root.end == val) {
	    root.count--;
	    return;
	}
	int mid = (root.start + root.end) / 2;
	if (val <= mid) {
	    updateDel(root.left, val);
	} else {
	    updateDel(root.right, val);
	}
	root.count = root.left.count + root.right.count;
    }

    // 4. BST
    // Traverse from nums[len - 1] to nums[0], and build a binary search tree,
    // which stores:
    // val: value of nums[i]
    // count: if val == root.val, there will be count number of smaller numbers
    // on the right
    public List<Integer> countSmaller4(int[] nums) {
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
}
