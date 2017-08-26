/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

Segment Tree, Binary Index Tree
 */
public class LT307_Range_Sum_Query_Mutable {
    // Segment Tree
    class SegmentTreeNode {
	int start, end;
	SegmentTreeNode left, right;
	int sum;

	public SegmentTreeNode(int start, int end) {
	    this.start = start;
	    this.end = end;
	    this.left = null;
	    this.right = null;
	    this.sum = 0;
	}
    }

    SegmentTreeNode root = null;

    public LT307_Range_Sum_Query_Mutable(int[] nums) {
	root = buildTree(nums, 0, nums.length - 1);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
	if (start > end) {
	    return null;
	} else {
	    SegmentTreeNode ret = new SegmentTreeNode(start, end);
	    if (start == end) {
		ret.sum = nums[start];
	    } else {
		int mid = start + (end - start) / 2;
		ret.left = buildTree(nums, start, mid);
		ret.right = buildTree(nums, mid + 1, end);
		ret.sum = ret.left.sum + ret.right.sum;
	    }
	    return ret;
	}
    }

    void update(int i, int val) {
	update(root, i, val);
    }

    void update(SegmentTreeNode root, int pos, int val) {
	if (root.start == root.end) {
	    root.sum = val;
	} else {
	    int mid = root.start + (root.end - root.start) / 2;
	    if (pos <= mid) {
		update(root.left, pos, val);
	    } else {
		update(root.right, pos, val);
	    }
	    root.sum = root.left.sum + root.right.sum;
	}
    }

    public int sumRange(int i, int j) {
	return sumRange(root, i, j);
    }

    public int sumRange(SegmentTreeNode root, int start, int end) {
	if (root.end == end && root.start == start) {
	    return root.sum;
	} else {
	    int mid = root.start + (root.end - root.start) / 2;
	    if (end <= mid) {
		return sumRange(root.left, start, end);
	    } else if (start >= mid + 1) {
		return sumRange(root.right, start, end);
	    } else {
		return sumRange(root.right, mid + 1, end) + sumRange(root.left, start, mid);
	    }
	}
    }
}

// Binary Index Tree
class NumArray {
    /**
     * Binary Indexed Trees (BIT or Fenwick tree): https://www.topcoder.com/community/data-science/data-science- tutorials/binary-indexed-trees/
     * 
     * Example: given an array a[0]...a[7], we use a array BIT[9] to represent a tree, where index [2] is the parent of [1] and [3], [6] is the parent of [5] and [7], [4] is the parent of [2] and [6],
     * and [8] is the parent of [4]. I.e.,
     * 
     * BIT[] as a binary tree: ______________* ______* __* __* * * * * indices: 0 1 2 3 4 5 6 7 8
     * 
     * BIT[i] = ([i] is a left child) ? the partial sum from its left most descendant to itself : the partial sum from its parent (exclusive) to itself. (check the range of "__").
     * 
     * Eg. BIT[1]=a[0], BIT[2]=a[1]+BIT[1]=a[1]+a[0], BIT[3]=a[2], BIT[4]=a[3]+BIT[3]+BIT[2]=a[3]+a[2]+a[1]+a[0], BIT[6]=a[5]+BIT[5]=a[5]+a[4], BIT[8]=a[7]+BIT[7]+BIT[6]+BIT[4]=a[7]+a[6]+...+a[0], ...
     * 
     * Thus, to update a[1]=BIT[2], we shall update BIT[2], BIT[4], BIT[8], i.e., for current [i], the next update [j] is j=i+(i&-i) //double the last 1-bit from [i].
     * 
     * Similarly, to get the partial sum up to a[6]=BIT[7], we shall get the sum of BIT[7], BIT[6], BIT[4], i.e., for current [i], the next summand [j] is j=i-(i&-i) // delete the last 1-bit from [i].
     * 
     * To obtain the original value of a[7] (corresponding to index [8] of BIT), we have to subtract BIT[7], BIT[6], BIT[4] from BIT[8], i.e., starting from [idx-1], for current [i], the next
     * subtrahend [j] is j=i-(i&-i), up to j==idx-(idx&-idx) exclusive. (However, a quicker way but using extra space is to store the original array.)
     */

    // binary index tree
    int[] tree;
    int[] nums;
    int size;

    public NumArray(int[] nums) {
	this.size = nums.length;
	this.tree = new int[size + 1];
	this.nums = new int[size];
	for (int i = 0; i < size; i++)
	    update(i, nums[i]);
    }

    void update(int row, int val) {
	int diff = val - nums[row];
	nums[row] = val;
	for (int i = row + 1; i <= size; i += i & -i) {
	    tree[i] += diff;
	}

    }

    public int sumRange(int i, int j) {
	if (i == 0)
	    return getSum(j);
	return getSum(j) - getSum(i - 1);
    }

    private int getSum(int i) {
	int sum = 0;
	i = i + 1;
	while (i > 0) {
	    sum += tree[i];
	    i -= i & (-i);// Another tree, go to the ancestor
	}
	return sum;
    }
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
