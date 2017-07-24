/*
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */

/*
 * String
 */
public class LT005_Longest_Palindrome_Substring {
	public String longestPalindrome(String s) {
		// O(n^2)
		if (s == null || s.length() == 0)
			return "";
		String init = s.substring(0, 1);
		for (int i = 0; i < s.length(); i++) {
			// odd length
			String s1 = isPalindrome(s, i, i);
			if (s1.length() > init.length())
				init = s1;

			// even length
			String s2 = isPalindrome(s, i, i + 1);
			if (s2.length() > init.length())
				init = s2;
		}

		return init;
	}

	public String isPalindrome(String s, int left, int right) {
		// from center to two side. Need to consider the boundary
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}

		return s.substring(left + 1, right);
	}

	public String longestPalindrome2(String s) {
		// dp[i,j] = true is substring(i,j) is palindrome. dp[i,i] = true,
		// dp[i,i+1]=(letter(i)==letter(i+1))
		// dp[i-1, j+1] = (dp[i,j]&& letter(i-1)==letter(j+1)
		int n = s.length();
		String res = null;
		int palindromeStartsAt = 0, maxLen = 0;

		boolean[][] dp = new boolean[n][n];
		// dp[i][j] indicates whether substring s starting at index i and ending
		// at j is palindrome

		for (int i = n - 1; i >= 0; i--) { // keep increasing the possible
											// palindrome string
			for (int j = i; j < n; j++) { // find the max palindrome within this
											// window of (i,j)

				// check if substring between (i,j) is palindrome
				dp[i][j] = (s.charAt(i) == s.charAt(j)) // chars at i and j
														// should match
						&& (j - i < 3 // if window is less than or equal to 3,
										// just end chars should match
								|| dp[i + 1][j - 1]); // if window is > 3,
														// substring (i+1, j-1)
														// should be palindrome
														// too

				// update max palindrome string
				if (dp[i][j] && (j - i + 1 > maxLen)) {
					palindromeStartsAt = i;
					maxLen = j - i + 1;
				}
			}
		}
		return s.substring(palindromeStartsAt, palindromeStartsAt + maxLen);
	}
}
