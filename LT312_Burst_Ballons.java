/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8]

Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
   
Divide and Conquer, DP
 */
public class LT312_Burst_Ballons {
	//O(n^3)
	public int maxCoins(int[] nums) {
        //https://leetcode.com/discuss/72216/share-some-analysis-and-explanations
        int len = nums.length;
        int[] iNum = new int[len+2];
        for(int i=1;i<=len;i++){
            iNum[i] = nums[i-1];
        }
        iNum[0] =1;
        iNum[len+1] = 1;

    
        int[][] dp = new int[len+2][len+2];
        for(int k=2;k<len+2;k++){
            for(int left=0;left<len+2-k;left++){
                int right = left+k;
                for(int i=left+1;i<right;i++){
                    dp[left][right] = Math.max(dp[left][right], iNum[i]*iNum[left]*iNum[right]+dp[left][i]+dp[i][right]);
                }
            }
        }
        

        return dp[0][len+1];
    }
	
	//<O(2^n) divide and conquer + memorization
	int[][] dp;
	int[] values;
    public int maxCoins2(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        int n = nums.length;
        dp = new int[n + 2][n + 2];

        //Initialize new array
        values = new int[n + 2];
        values[0] = values[n + 1] = 1;
        for (int i = 1; i < n + 1; i++) {
        	values[i] = nums[i - 1];
        }
       
        return DP(1, n);
    }

    public int DP(int i, int j){
    	if (dp[i][j] > 0) {//memorization
    		return dp[i][j];
    	}
    	for (int x = i; x <= j; x++) {              //cut at x between [i,j]
    		dp[i][j] = Math.max(dp[i][j], DP(i, x - 1) + values[i-1]*values[x]*values[j+1] + DP(x + 1, j));
    	}
    	return dp[i][j];
    }
}
