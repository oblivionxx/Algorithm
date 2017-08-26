/*
A peak element is an element that is greater than its neighbors.
Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
Your solution should be in logarithmic complexity.

Binary Search, Array

 */
public class LT162_Find_Peak_Element {
    public int findPeakElement(int[] nums) {
	// linear scan O(n). binary search O(lgN)
	for (int i = 0; i < nums.length - 1; i++) {
	    if (nums[i] > nums[i + 1])
		return i;
	}
	return nums.length - 1;
    }

    public int findPeakElement2(int[] nums) {
	if (nums == null || nums.length == 0)
	    return 0;
	return helper(nums, 0, nums.length - 1);
    }

    public int helper(int[] nums, int l, int r) {
	// check center to two sides.
	while (l < r) { // here not equal. consider if there's only one element. if equal, then mid+1 is null pointer.
	    int mid = (l + r) / 2;
	    if (nums[mid] > nums[mid + 1])
		r = mid;
	    else
		l = mid + 1;
	}
	return l;

    }
}
