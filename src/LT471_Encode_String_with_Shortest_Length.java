/*
 * Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:
k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
Example 1:

Input: "aaa"
Output: "aaa"
Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
Example 2:

Input: "aaaaa"
Output: "5[a]"
Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
Example 3:

Input: "aaaaaaaaaa"
Output: "10[a]"
Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
Example 4:

Input: "aabcaabcd"
Output: "2[aabc]d"
Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
Example 5:

Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".

DP
 */
public class LT471_Encode_String_with_Shortest_Length {
    // dp[i][j] = string from index i to index j in encoded form. O(n^3)
    private String[][] dp;

    public String encode(String s) {
	dp = new String[s.length()][s.length()];
	return helper(s, 0, s.length() - 1);
    }

    private String helper(String s, int left, int right) {
	if (dp[left][right] != null)
	    return dp[left][right]; // If we have get the optimal result, return it directly.
	String substr = s.substring(left, right + 1);

	if (right - left < 4) { // no need to encode.
	    dp[left][right] = substr;
	    return dp[left][right];
	}

	dp[left][right] = substr;

	// the optimal solution is got from two part, one part is combined by the sub optimal encode
	for (int k = left; k < right; k++) {
	    if ((helper(s, left, k) + helper(s, k + 1, right)).length() < dp[left][right].length()) {
		dp[left][right] = dp[left][k] + dp[k + 1][right];
	    }
	}

	// check substr if repeat itself. encode
	for (int k = 0; k < substr.length(); k++) {
	    String repeatStr = substr.substring(0, k + 1);
	    if (isRepeat(substr, repeatStr)) {
		String ss = substr.length() / repeatStr.length() + "[" + dp[left][left + k] + "]";
		if (ss.length() < dp[left][right].length()) {
		    dp[left][right] = ss;
		}
	    }
	}

	return dp[left][right];
    }

    // We check whether s1 is the repetitive ones of s2 by using the following function. Since most s1 is not established by repetitive s2, my algorithm can "fail" fast which enhance the speed of the
    // total alogrithm
    private boolean isRepeat(String longS, String shortS) {
	if (shortS == null || shortS.length() == 0 || longS.length() % shortS.length() != 0)
	    return false;
	int dup = longS.length() / shortS.length();
	for (int i = 0; i < shortS.length(); i++) {
	    for (int j = 1; j < dup; j++) {
		if (longS.charAt(j * shortS.length() + i) != shortS.charAt(i))
		    return false;
	    }
	}
	return true;
    }
}
