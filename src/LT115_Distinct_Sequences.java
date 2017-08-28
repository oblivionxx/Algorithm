/*
Given a string S and a string T, count the number of distinct subsequences of T in S.
A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"
Return 3.

DP, String
 */
public class LT115_Distinct_Sequences {
    public int numDistinct(String S, String T) {
	/**
	 * Solution (DP): DP[i][j]表示T的前j个字符能够在S的前i个字符中能够取的子字符串的数量。Scan in S. Init: DP[i][0]=1，即T的前0个字符总是能够成为S的一个子字符串. DP[0][j]=0(j>0)，即不可能有非空字符串是S的前0个字符的子字符串 对于S的第i位字符有两种情况：
	 * 第一种为不能和T的j位字符匹配，则其值为S的前i－1位字符和T的前j位字符的匹配数；DP[i][j]=DP[i-1][j] 第二种情况为可以和T的第j位字符匹配，则其值为S的前i－1位字符和T的前j位字符的匹配数＋S的前i－1位字符和T的前j－1位字符的匹配数 DP[i][j]=DP[i-1][j]+DP[i-1][j-1]
	 */
	int sl = S.length();
	int tl = T.length();

	int[][] dp = new int[sl + 1][tl + 1];

	for (int i = 0; i <= sl; ++i) {
	    dp[i][0] = 1;
	}

	for (int s = 1; s <= sl; s++) {
	    for (int t = 1; t <= tl; t++) {
		if (S.charAt(s - 1) != T.charAt(t - 1)) {
		    dp[s][t] = dp[s - 1][t];
		} else {
		    dp[s][t] = dp[s - 1][t] + dp[s - 1][t - 1];
		}
	    }
	}

	return dp[sl][tl];
    }
}
