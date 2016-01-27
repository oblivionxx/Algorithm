/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
Tree, DP-Catalan Number
 */
public class LT096_Unique_Binary_Search_Trees {
	public int numTrees(int n) {
        if(n==0 || n==1) return 1;
        
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        for(int i=1;i<n+1;i++){
            for(int j=0;j<i;j++)
                dp[i] += dp[j]*dp[i-j-1];
        }
        
        //Ct+1 += Ci*Ct-i Catalan number
        //O(n^2), O(n)
        return dp[n];
    }
}
