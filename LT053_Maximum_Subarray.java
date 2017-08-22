/*
 Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 
 For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 the contiguous subarray [4,−1,2,1] has the largest sum = 6.

 More practice:
 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
/*
 * Array, DP, Divide and Conquer
 */
public class LT053_Maximum_Subarray {
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int local = nums[0], global = nums[0]; // dont write =0.
		for (int i = 0; i < nums.length; i++) {
			local = Math.max(local + nums[i], nums[i]); // important
			global = Math.max(global, local);
		}

		return global;
	}

	// divide and conquer. array is on the left half, right half, or cross
	// center
	public int maxSubArray2(int[] nums) {
		return maxSubArray(nums, 0, nums.length - 1);
	}

	public int maxSubArray(int[] nums, int l, int r) {
		if (l == r)
			return nums[l]; // only one element
		int mid = (l + r) / 2;
		return Math.max(Math.max(maxSubArray(nums, l, mid), maxSubArray(nums, mid + 1, r)),
				maxCrossCenter(nums, l, mid, r));
	}

	public int maxCrossCenter(int[] nums, int l, int m, int r) {
		int localSum = 0;
		int leftSum = Integer.MIN_VALUE;
		for (int i = m; i >= l; i--) {
			localSum = localSum + nums[i];
			leftSum = localSum > leftSum ? localSum : leftSum;
		}

		localSum = 0;
		int rightSum = Integer.MIN_VALUE;
		for (int i = m + 1; i <= r; i++) {
			localSum = localSum + nums[i];
			rightSum = localSum > rightSum ? localSum : rightSum;
		}

		return leftSum + rightSum;
	}
}
