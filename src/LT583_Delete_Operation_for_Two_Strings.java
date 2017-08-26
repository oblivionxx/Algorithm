/*
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.

String
 */
public class LT583_Delete_Operation_for_Two_Strings {
    public int minDistance(String word1, String word2) {
	// find the longest common subsequece. be careful order should be the same. use common letter is not working
	int dp[][] = new int[word1.length() + 1][word2.length() + 1];
	for (int i = 0; i <= word1.length(); i++) {
	    for (int j = 0; j <= word2.length(); j++) {
		if (i == 0 || j == 0)
		    dp[i][j] = 0;
		else // same letter. can remove the next.
		    dp[i][j] = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? dp[i - 1][j - 1] + 1
			    : Math.max(dp[i - 1][j], dp[i][j - 1]); // http://www.programcreek.com/2014/04/longest-common-subsequence-java/
	    }
	}
	int val = dp[word1.length()][word2.length()];
	return word1.length() - val + word2.length() - val;
    }
}