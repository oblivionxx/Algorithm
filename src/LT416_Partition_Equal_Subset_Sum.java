/*
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

DP
 */
public class LT416_Partition_Equal_Subset_Sum {
    // Let us assume dp[i][j] means whether the specific sum j can be gotten from the first i numbers.
    // If not pick num[i], dp[i][j] = dp[i-1][j]
    // If we pick nums[i]. dp[i][j] = dp[i-1][j-nums[i]]
    // dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]) ==>dp[n][sum] ==> optimize to 1d

    public boolean canPartition(int[] nums) {
	if (nums == null || nums.length == 0)
	    return true;
	int sum = 0;
	for (int n : nums)
	    sum += n;
	if (sum % 2 != 0)
	    return false;
	sum /= 2;
	boolean[] dp = new boolean[sum + 1];
	dp[0] = true;
	// replace nums[i-1] with nums[i]
	for (int i = 0; i < nums.length; i++) { // given first i numbers selected.
	    for (int j = sum; j >= nums[i]; j--) // try new sum j from nums[i]~sum.
		dp[j] = dp[j] || dp[j - nums[i]]; // update if first i numbers could form sum j.
	}
	return dp[sum];
    }
}
