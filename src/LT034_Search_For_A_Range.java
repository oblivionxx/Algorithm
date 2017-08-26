/*
 Given a sorted array of integers, find the starting and ending position of a given target value.
 Your algorithm's runtime complexity must be in the order of O(log n).
 If the target is not found in the array, return [-1, -1].
 For example,
 Given [5, 7, 7, 8, 8, 10] and target value 8,
 return [3, 4].
 */
/*
 * Array, Binary Search 
 */
public class LT034_Search_For_A_Range {
	public int[] searchRange(int[] nums, int target) {

		int ll = 0, lr = nums.length - 1;
		while (ll <= lr) {
			int mid = (ll + lr) / 2;
			if (nums[mid] < target)
				ll = mid + 1; // ll is always >= target
			else
				lr = mid - 1; // dont care
		}

		int rl = 0, rr = nums.length - 1;
		while (rl <= rr) {
			int mid = (rl + rr) / 2;
			if (nums[mid] > target)
				rr = mid - 1; // rr is always<=target
			else
				rl = mid + 1; // dont care
		}

		int[] res = { -1, -1 };
		if (ll <= rr) {
			res[0] = ll;
			res[1] = rr;
		}

		return res;
	}
}
