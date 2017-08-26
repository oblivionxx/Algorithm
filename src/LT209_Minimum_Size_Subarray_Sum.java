/*
Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

Array, Two Pointer, Binary Search
 */
public class LT209_Minimum_Size_Subarray_Sum {
    // O(n) two pointer, move left or right border if window sum is larger or less than the target
    public int minSubArrayLen(int s, int[] nums) {
	if (nums == null || nums.length == 0)
	    return 0;
	int left = 0, right = 0, len = nums.length + 1, sum = 0;
	while (right < nums.length) {
	    while (sum < s && right != nums.length) {
		sum += nums[right];
		right++;
	    }
	    while (sum >= s) {
		len = Math.min(len, right - left);
		sum -= nums[left];
		left++;

	    }
	}

	return len == nums.length + 1 ? 0 : len;
    }

    // O(nlgn) As to NLogN solution, logN immediately reminds you of binary search.
    // In this case, you cannot sort as the current order actually matters. How does one get an ordered array then? Since all elements are positive, the cumulative sum must be strictly increasing.
    // Then, a subarray sum can expressed as the difference between two cumulative sum.
    public int minSubArrayLen2(int s, int[] nums) {
	if (nums.length == 0)
	    return 0;
	int res = nums.length + 1;
	int[] sum = new int[nums.length + 1];

	// cumulative sum
	for (int i = 1; i < nums.length + 1; i++) {
	    sum[i] = sum[i - 1] + nums[i - 1];
	}

	// for each sum[i]. binary search find sum[i]+s. update min_window
	for (int i = 0; i < nums.length + 1; i++) {
	    int right = getRight(i + 1, nums.length, sum[i] + s, sum);
	    if (right == nums.length + 1)
		break;
	    res = Math.min(right - i, res);
	}

	return res == nums.length + 1 ? 0 : res;
    }

    public int getRight(int left, int right, int key, int[] sum) {
	while (left <= right) {
	    int mid = (left + right) / 2;
	    if (key > sum[mid])
		left = mid + 1;
	    else
		right = mid - 1;
	}

	return left;
    }

}
