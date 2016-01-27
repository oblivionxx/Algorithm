/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n^2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

DP, Binary Search
 */
public class LT300_Longest_Increasing_Subsequence {
	//DP. O(n^2)
	public int lengthOfLIS(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        
        int[] dp = new int[nums.length];
        for(int i=0;i<nums.length;i++)
            dp[i] = 1;
        //dp track the length of increasing sequence. init=1
        int max = 1;
        for(int i=1;i<nums.length;i++){ 
            for(int j=i-1;j>=0;j--) {       //find the smallest value 0-i
                if(nums[i]>nums[j] && dp[j]+1>dp[i])
                    dp[i] = dp[j]+1;       //dp[i] local optimum
               
                max = Math.max(max, dp[i]);         //global optimum
            }
        }
        return max;
    }
	
	//Binary Search
}
