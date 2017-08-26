import java.util.*;

/*
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

DP, Math
 */
public class LT523_Continuous_Subarray_Sum {
    public boolean checkSubarraySum(int[] nums, int k) {
	// iterate through the input array exactly once, keeping track of the running sum mod k of the elements in the process.
	// If we find that a running sum value at index j has been previously seen before in some earlier index i in the array, then we know that the sub-array (i,j] contains a desired sum.
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	map.put(0, -1); // eg. [6],6
	int runningSum = 0;
	for (int i = 0; i < nums.length; i++) {
	    runningSum += nums[i];
	    if (k != 0)
		runningSum %= k;
	    Integer prev = map.get(runningSum);
	    if (prev != null) {
		if (i - prev > 1)
		    return true;
	    } else
		map.put(runningSum, i);
	}
	return false;
    }

    public boolean checkSubarraySum2(int[] nums, int k) {
	int[] dp = new int[nums.length + 1];
	for (int i = 1; i <= nums.length; i++) {
	    dp[i] = dp[i - 1] + nums[i - 1];
	}

	for (int i = 0; i < nums.length; i++) {
	    // start point
	    for (int j = i + 2; j <= nums.length; j++) { // size at least two
		int arraySum = dp[j] - dp[i];
		if (k == 0) {
		    if (arraySum == 0)
			return true;
		} else {
		    if (arraySum % k == 0)
			return true;
		}
	    }
	}
	return false;
    }
}
