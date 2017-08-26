/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
Note:
All costs are positive integers.

DP
 */
public class LT256_Paint_House {
	//dp[i][j] -- the min cost for house i on painting color j
    //dp[i][R] = cost[i][R] + Math.min(dp[i - 1][B], dp[i - 1][G]);
    //dp[i][B] = cost[i][B] + Math.min(dp[i - 1][R], dp[i -1 ][G]);
    //dp[i][G] = cost[i][G] + Math.min(dp[i - 1][R], dp[i - 1][B]);
    //Final staus: min(dp[n - 1][R], dp[n - 1][B], dp[n - 1][G]);

    public int minCost(int[][] costs) {
        
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int m = costs.length;

        //costs matrix
        int[][] dp = new int[m][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        
        for(int i=1;i<m;i++){
            dp[i][0] = costs[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i - 1][0], dp[i -1 ][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }
        
        return Math.min(Math.min(dp[m-1][0],dp[m-1][1]),dp[m-1][2]);
        
    }
}
