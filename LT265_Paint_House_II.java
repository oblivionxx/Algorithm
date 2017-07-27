/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?

DP.
 */
public class LT265_Paint_House_II {
	public int minCostII(int[][] costs) {
        //Time complexity: O(n*k*k).
        //Space complexity: O(n*k).
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;

        //Init dp matrix with costs matrix
        int[][] dp = new int[n][k];
        for(int i=0;i<k;i++){
            dp[0][i] = costs[0][i];
        }
        
        //set value in dp matrix
        for(int i=1;i<n;i++){
            for(int j=0;j<k;j++){
                dp[i][j] = Integer.MAX_VALUE;
                for(int m=0;m<k;m++){
                    if(m!=j)
                        dp[i][j] = Math.min(costs[i][j] + dp[i - 1][m], dp[i][j]);
                }
            }
        }
        
        //get the smallest. Final state
        int min = Integer.MAX_VALUE;
        for(int i=0;i<k;i++){
            min = Math.min(min, dp[n-1][i]);
        }
        
        return min;
    
    }
	
	//2. A O(k) Space Solution:
	//Since dp[i][k] only depends on dp[i-1][j], we can use a 1-D DP solution. 
	public int minCostII2(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
         
        int n = costs.length;
        int k = costs[0].length;
         
        // dp[j] means the min cost for color j
        int[] dp1 = new int[k];
        int[] dp2 = new int[k];
         
        // Initialization
        for (int i = 0; i < k; i++) {
            dp1[i] = costs[0][i];
        }
         
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dp2[j] = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++) {
                    if (m != j) {
                        dp2[j] = Math.min(dp1[m] + costs[i][j], dp2[j]);
                    }
                }
            }
             
            for (int j = 0; j < k; j++) {
                dp1[j] = dp2[j];
            }
        }
         
        // Final state
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            minCost = Math.min(minCost, dp1[i]);
        }
         
        return minCost;
    }
	
	
	//3. A Time O(n*K) Solution:
	//Use two variables min1 and min2, where min1 is the minimum value, whereas min2 is next to the minimum value. 
	public int minCostII3(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
         
        int n = costs.length;
        int k = costs[0].length;
         
        // dp[j] means the min cost for color j
        int[] dp = new int[k];
        int min1 = 0;
        int min2 = 0;
 
        for (int i = 0; i < n; i++) {
            int oldMin1 = min1;
            int oldMin2 = min2;
             
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
             
            for (int j = 0; j < k; j++) {
                if (dp[j] != oldMin1 || oldMin1 == oldMin2) {
                    dp[j] = oldMin1 + costs[i][j];
                } else {
                    dp[j] = oldMin2 + costs[i][j];
                }
                 
                if (min1 <= dp[j]) {
                    min2 = Math.min(min2, dp[j]);
                } else {
                    min2 = min1;
                    min1 = dp[j];
                }
            }
        }
         
        return min1;
    }
	
	public int minCostII4(int[][] costs) {
        //Explanation: dp[i][j] represents the min paint cost from house 0 to house i when house i use color j; The formula will be dp[i][j] = Math.min(any k!= j| dp[i-1][k]) + costs[i][j].
        //O(nk) with space: O(1)

        //no need an array to represent dp[i][j], we only need to know the min cost to the previous house of any color and if the color j is used on previous house to get prev min cost, use the second min cost that are not using color j on the previous house. So I have three variable to record: prevMin, prevMinColor, prevSecondMin. and the above formula will be translated into: dp[currentHouse][currentColor] = (currentColor == prevMinColor? prevSecondMin: prevMin) + costs[currentHouse][currentColor].
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
    
        int n = costs.length, k = costs[0].length;
        if(k == 1) return (n==1? costs[0][0] : -1);
        
        int prevMin = 0, prevMinInd = -1, prevSecMin = 0;//prevSecMin always >= prevMin
        for(int i = 0; i<n; i++) {
            int min = Integer.MAX_VALUE, minInd = -1, secMin = Integer.MAX_VALUE;
            for(int j = 0; j<k;  j++) {
                int val = costs[i][j] + (j == prevMinInd? prevSecMin : prevMin);
                if(minInd< 0) {min = val; minInd = j;}//when min isn't initialized
                else if(val < min) {//when val < min, 
                    secMin = min;
                    min = val;
                    minInd = j;
                } else if(val < secMin) { //when min<=val< secMin
                    secMin = val;
                }
            }
            prevMin = min;
            prevMinInd = minInd;
            prevSecMin = secMin;
        }
        return prevMin;
    }
}
