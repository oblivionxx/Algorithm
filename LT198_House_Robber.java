/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

DP.
 */
public class LT198_House_Robber {
    public int rob(int[] nums) {
	    if(nums==null || nums.length==0) return 0;
	    if(nums.length==1) return nums[0];
	    if(nums.length==2) return Math.max(nums[0],nums[1]);
	    
	    //int[] dp means rob i th house. then get the money.
	    int n=nums.length;
	    int[] dp = new int[n];
	    dp[0] = nums[0];
	    dp[1] = Math.max(nums[0],nums[1]);  //rob the 1st house, get nums[0]
	    
	    for(int i=2;i<n;i++)
	        dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]); 
	    
	    return dp[n-1];  //rob nth house
	}
}
