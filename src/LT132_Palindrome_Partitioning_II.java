/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

DP.
 */
public class LT132_Palindrome_Partitioning_II {

    // a b a | c c
    // j i
    // j-1 | [j, i] is palindrome
    // cut(j-1) + 1

    public int minCut(String s) {
	int[] dp = new int[s.length()]; // dp[i] = min cut for s(0,i)
	boolean[][] isPalindrome = new boolean[s.length()][s.length()];

	for (int i = 0; i < s.length(); i++) {
	    int localMin = i; // cut at each letter can gurantee palindrom partition.
	    for (int j = 0; j <= i; j++) {
		if (s.charAt(j) == s.charAt(i) && (i - j < 2 || isPalindrome[j + 1][i - 1])) {
		    isPalindrome[j][i] = true;
		    localMin = j == 0 ? 0 : Math.min(localMin, dp[j - 1] + 1);
		}
	    }
	    dp[i] = localMin;
	}

	return dp[s.length() - 1];
    }
}
