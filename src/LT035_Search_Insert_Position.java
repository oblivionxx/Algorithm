import java.util.Arrays;

/*
 Given a sorted array and a target value, return the index if the target is found. 
 If not, return the index where it would be if it were inserted in order.
	You may assume no duplicates in the array.
	Here are few examples.
	[1,3,5,6], 5 → 2
	[1,3,5,6], 2 → 1
	[1,3,5,6], 7 → 4
	[1,3,5,6], 0 → 0
 */
/*
 * Array, Binary Search
 */
public class LT035_Search_Insert_Position {
    public int searchInsert(int[] nums, int target) {
	// easy. think by myself
	int left = 0, right = nums.length - 1;
	while (left <= right) {
	    int mid = (left + right) / 2;
	    if (nums[mid] == target)
		return mid; // if found, return index;
	    else if (nums[mid] < target) // find the left bound s.t.
					 // num[left]>target
		left = mid + 1;
	    else
		right = mid - 1;
	}

	return left;
    }

    public int searchInsert2(int[] nums, int target) {
	int pos = Arrays.binarySearch(nums, target);
	return pos < 0 ? -(pos + 1) : pos;
    }
}
