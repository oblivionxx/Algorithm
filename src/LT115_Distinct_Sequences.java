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
	 * Solution (DP): We keep a m*n matrix and scanning through string S, while m = T.length() + 1 and n = S.length() + 1 and each cell in matrix Path[i][j] means the number of distinct
	 * subsequences of T.substr(1...i) in S(1...j)
	 * 
	 * Path[i][j] = Path[i][j-1] (discard S[j]) + Path[i-1][j-1] (S[j] == T[i] and we are going to use S[j]) or 0 (S[j] != T[i] so we could not use S[j]) while Path[0][j] = 1 and Path[i][0] = 0.
	 */

	// Here we construct a matrix matrixDP, where each cell matrixDP[i][j] represents the number of solutions of placing substring B[0..i] with A[0..j];

	// Case 1). matrixDP[0][j] = 1, since placing B = “” with any substring of A would have only 1 solution which is to delete all characters in A.

	// Case 2). when i > 0, matrixDP[i][j] can be derived by two cases:

	// case 2[a]). if B[i] != A[j], then the solution would be to ignore the character A[j] and align substring B[0..i] with A[0..(j-1)]. Therefore, matrixDP[i][j] = matrixDP[i][j-1].

	// case 2[b]). if B[i] == A[j], then first we could have the solution in case a), but also we could match the characters B[i] and A[j] and place the rest of them (i.e. B[0..(i-1)] and
	// A[0..(j-1)]. As a result, matrixDP[i][j] = matrixDP[i][j-1] + matrixDP[i-1][j-1]

	// e.g. B = "b", A = "abc"

	// matrixDP[1][2]=1: Place B'=b and A'=ab, only one solution, which is to remove character a in A'.

	int sl = S.length();
	int tl = T.length();

	int[][] dp = new int[tl + 1][sl + 1];

	for (int i = 0; i <= sl; ++i) {
	    dp[0][i] = 1;
	}

	for (int t = 1; t <= tl; ++t) {
	    for (int s = 1; s <= sl; ++s) {
		if (T.charAt(t - 1) != S.charAt(s - 1)) {
		    dp[t][s] = dp[t][s - 1];
		} else {
		    dp[t][s] = dp[t][s - 1] + dp[t - 1][s - 1];
		}
	    }
	}

	return dp[tl][sl];
    }
}
