/*
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.

DP, MinMax
 */
public class LT486_Predict_the_Winner {
    public boolean PredictTheWinner(int[] nums) {
        return helper(nums, 0, nums.length-1)>=0;
    }
    private int helper(int[] nums, int s, int e){        
        return s==e ? nums[e] : Math.max(nums[e] - helper(nums, s, e-1), nums[s] - helper(nums, s+1, e));
    }
    
    //dp[i][j] means the max sum we can get if the array starts from i and ends to j.
    //dp[i][i] means only one coin and we pick firstly, dp[i][i+1] means there are two coins, we pick the larger one.
    //dp[i][j] = max( nums[i] + dp[i + 1][j], dp[i][j - 1] + nums[j]), But dp[i + 1][j] and dp[i][j - 1] depends on the move that our opponent make.
    //dp[i+1][j] = min(dp[i + 1][j - 1], dp[i + 2][j])          //opponent pick larger one then smaller one for us.
    //dp[i][j] = max(min(dp[i + 1][j - 1], dp[i + 2][ j]) + nums[i], min (dp[i][j - 2], dp[i + 1][ j - 1]) + nums[j]}) .
    //sub problem is from i=j and extend to two sides. so loop j=0~len, loop i=j-1~0
    public boolean PredictTheWinner2(int[] nums) {
        int n = nums.length, sum = 0;
	    if(n % 2 == 0) return true;
        int[][] dp = new int[n][n];
        for(int i=0; i < n; i++) {
            dp[i][i] = nums[i];
            sum += nums[i];
        }

        for(int j = 0; j < n; j++){
            for(int i = j - 1; i >= 0; i--){
                //border check
            	int a = (i + 1 < n && j - 1 >= 0) ? dp[i + 1][j - 1] : 0;
                int b = (i + 2 < n) ? dp[i + 2][ j] : 0;
                int c = (j - 2 >= 0) ? dp[i][j - 2] : 0;
                dp[i][j] = Math.max(Math.min(a, b) + nums[i], Math.min(a, c) + nums[j]);
            }
        }

        return sum-dp[0][n - 1] <=dp[0][n-1];
        
    }

    //https://leetcode.com/articles/predict-the-winner/#approach-3-dynamic-programming-accepted
    //1-d dp
}
