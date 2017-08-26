/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

DP
 */
public class LT303_Range_Sum_Query_Immutable {
	public int[] dp;
    
    public LT303_Range_Sum_Query_Immutable(int[] nums) {
        dp = new int[nums.length+1];
        dp[0] =0;
        for(int i=0;i<nums.length;i++){
            dp[i+1] =nums[i]+dp[i];
        }
        //form the sum array
    }

    public int sumRange(int i, int j) {
        return dp[j+1]-dp[i];
    }
}
