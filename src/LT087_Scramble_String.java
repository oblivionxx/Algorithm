import java.util.Arrays;
import java.util.HashMap;

/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
/*
 * DP, String
 */
public class LT087_Scramble_String {
    // 1. 3d Boolean DP
    // 因为递归解法有很多重复子问题，比如s2 = rgeat, s1 = great 当我们选择分割点为0时，要解决子问题 isScramble(reat, geat)，再对该子问题选择分割点0时，要解决子问题 isScramble(eat,eat)；而当我们第一步选择1作为分割点时，也要解决子问题
    // isScramble(eat,eat)。相同的子问题isScramble(eat,eat)就要解决2次。 因此可以用记忆化搜索来保存子问题
    public boolean isScramble(String s1, String s2) {
	// Write your code here
	if (s1.length() != s2.length())
	    return false;
	int n = s1.length();
	boolean[][][] dp = new boolean[n][n][n + 1]; // dp[i][j][len]表示的是以i和j分别为s1和s2起点的长度为len的字符串是不是互为scramble
	for (int i = 0; i < n; ++i)
	    for (int j = 0; j < n; ++j)
		dp[i][j][1] = s1.charAt(i) == s2.charAt(j);

	for (int len = 2; len <= n; ++len)
	    for (int x = 0; x < n && x + len <= n; ++x)
		for (int y = 0; y < n && y + len <= n; ++y)
		    for (int k = 1; k < len; ++k) // loop cut
			dp[x][y][len] |= dp[x][y][k] && dp[x + k][y + k][len - k]
				|| dp[x][y + len - k][k] && dp[x + k][y][len - k];

	return dp[0][0][n];
    }

    // 2. Recursion of String. Could apply memorization to optimize. eg. save left#right pattern in hashmap.
    public boolean isScramble2(String s1, String s2) {
	if (s1.length() != s2.length())
	    return false;
	if (s1.equals(s2))
	    return true;

	// scramble order.
	char[] ts1 = s1.toCharArray();
	char[] ts2 = s2.toCharArray();
	Arrays.sort(ts1);
	Arrays.sort(ts2);
	if (!new String(ts1).equals(new String(ts2))) {
	    return false;
	}

	for (int i = 1; i < s1.length(); i++) {
	    String s11 = s1.substring(0, i);
	    String s12 = s1.substring(i);

	    // from left start len=i substring
	    String s21 = s2.substring(0, i);
	    String s22 = s2.substring(i);
	    if (isScramble(s11, s21) && isScramble(s12, s22))
		return true;

	    // from right end len=i substring.
	    s21 = s2.substring(s2.length() - i);
	    s22 = s2.substring(0, s2.length() - i);
	    if (isScramble(s11, s21) && isScramble(s12, s22))
		return true;

	}
	return false;
    }

    HashMap<String, Boolean> hash = new HashMap<String, Boolean>();

    // recursion with memorization
    public boolean isScramble3(String s1, String s2) {
	// Write your code here
	if (s1.length() != s2.length())
	    return false;

	if (hash.containsKey(s1 + "#" + s2))
	    return hash.get(s1 + "#" + s2);

	int n = s1.length();
	if (n == 1) {
	    return s1.charAt(0) == s2.charAt(0);
	}
	for (int k = 1; k < n; ++k) {
	    if (isScramble(s1.substring(0, k), s2.substring(0, k)) && isScramble(s1.substring(k, n), s2.substring(k, n))
		    || isScramble(s1.substring(0, k), s2.substring(n - k, n))
			    && isScramble(s1.substring(k, n), s2.substring(0, n - k))) {
		hash.put(s1 + "#" + s2, true);
		return true;
	    }
	}
	hash.put(s1 + "#" + s2, false);
	return false;
    }

}
