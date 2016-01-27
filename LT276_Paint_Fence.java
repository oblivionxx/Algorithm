/*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

DP
 */
public class LT276_Paint_Fence {
	public int numWays(int n, int k) {
        int[] dp = new int[n+1];
        if(n==0) return 0;
        if(n==1) return k;
        if(n==2) return k*k;
        
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k*k;
        //dp[i] = (k-1)*dp[i-1] + (k-1)*dp[i-2]
        //也就意味着第三根柱子要么根第一个柱子不是一个颜色，要么跟第二根柱子不是一个颜色
        for(int i=3;i<=n;i++){
            dp[i] = (k-1)*dp[i-1] + (k-1)*dp[i-2];
        }
        
        return dp[n];
    }
}
