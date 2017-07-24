/*
 Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 You are given a target value to search. If found in the array return its index, otherwise return -1.
 You may assume no duplicate exists in the array.
 */
/*
 * Array, Binary Search
 */
public class LT033_Search_In_Rotated_Sorted_Array {
	public int search(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while (left <= right) { // in case nums.length==1
			int mid = (left + right) / 2;
			if (nums[mid] == target)
				return mid;

			if (nums[mid] < nums[right]) {
				// right side is sorted
				if (nums[mid] < target && nums[right] >= target) // don't forget
																	// the equal
																	// here.
																	// nums[right]>=target
					left = mid + 1;
				else
					right = mid - 1;
			} else {
				// left side is sorted
				if (nums[mid] > target && nums[left] <= target)
					right = mid - 1;
				else
					left = mid + 1;
			}
		}

		return -1;
	}
}
