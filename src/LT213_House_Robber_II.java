/*
Note: This is an extension of House Robber.
After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

DP
 */
public class LT213_House_Robber_II {
    // rob 1st one or rob from 2nd house
    // case1: include nums0 but give only dp[i-2], do not rob nums[n-1] means dp[n-2]=dp[n-1]
    // case2: dp[0]=0, dp[1]=nums[1], then cal until dp[n-1]. using diff init condition

    // both dp[i] = max(dp[i-2]+nums[i], dp[i-1])
    public int rob(int[] nums) {
	if (nums == null || nums.length == 0)
	    return 0;
	if (nums.length == 1)
	    return nums[0];
	if (nums.length == 2)
	    return Math.max(nums[0], nums[1]);

	int n = nums.length;
	int[] dp1 = new int[n];
	dp1[0] = nums[0];
	dp1[1] = Math.max(nums[0], nums[1]); // rob the 1st house, get nums[0]

	for (int i = 2; i < n - 1; i++)
	    dp1[i] = Math.max(dp1[i - 2] + nums[i], dp1[i - 1]); // same as house robber I, but only take dp1[n-2] into consideration

	int[] dp2 = new int[n]; // different init. condition
	dp2[0] = 0;
	dp2[1] = nums[1];

	for (int i = 2; i < n; i++)
	    dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);

	return Math.max(dp1[n - 2], dp2[n - 1]); // rob nth house
    }
}
