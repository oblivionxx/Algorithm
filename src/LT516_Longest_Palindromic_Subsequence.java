/*
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".

DP
 */
public class LT516_Longest_Palindromic_Subsequence {
    public int longestPalindromeSubseq(String s) {
        //subsequence cannot change order
        //dp[i][j]: the longest palindromic subsequence's length of substring(i, j)
        //State transition:
        //dp[i][j] = dp[i+1][j-1] + 2 if s.charAt(i) == s.charAt(j)
        //otherwise, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])   consider subsequence
        //Initialization: dp[i][i] = 1
        
        int[][] dp = new int[s.length()][s.length()];
        
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
        
    }
}
