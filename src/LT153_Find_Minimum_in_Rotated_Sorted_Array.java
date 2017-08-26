/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.

Array, Binary Search
 */
public class LT153_Find_Minimum_in_Rotated_Sorted_Array {
    public int findMin(int[] nums) {
	int left = 0, right = nums.length - 1;
	while (left < right) {
	    int mid = (left + right) / 2; // find the part which is not sorted
	    if (nums[mid] > nums[right]) {
		left = mid + 1;
	    } else {
		right = mid;
	    }
	}

	return nums[left];
    }
}
