/*
 Implement wildcard pattern matching with support for '?' and '*'.
 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)
	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "*") → true
	isMatch("aa", "a*") → true
	isMatch("ab", "?*") → true
	isMatch("aab", "c*a*b") → false
 */
/*
 * DP, Greedy, Backtracking, String
 */
public class LT044_Wildcard_Matching {
	// dp[i][j] denotes whether s[0....i-1] matches p[0.....j-1]
	// initialize dp[i][0], i= [1,m]. dp[i][0] should be false because p has
	// nothing in it.
	// initialize dp[0][j], j = [1, n]. s has nothing, to get dp[0][j] = true, p
	// must be '*', '*', '**',etc. Once p.charAt(j-1) != '*', all the dp[0][j]
	// afterwards will be false.
	public boolean isMatch(String s, String p) {
		int m = s.length(), n = p.length();
		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;
		for (int i = 1; i <= m; i++) {
			dp[i][0] = false;
		}

		for (int j = 1; j <= n; j++) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = true;
			} else {
				break;
			}
		}

		// dp[i][j] = dp[i-1][j] || dp[i][j-1]
		// if(dp[i-1][j] == true) --> s(0-->i-2) matches with p(0-->j-1) now you
		// see p[j-1] == '*' , in the end of s, you can add another or more k
		// chars due to the last of pattern is '*', making dp[i][j] or even
		// dp[i+k][j] true;
		// if(dp[i][j-1] == true) --> s(0-->i-1) matches with p(0-->j-2) now you
		// see p[j-1] == '*', add this into p, * can match none in s. you don't
		// need to add anything into s, you can see now dp[i][j] = true.
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (p.charAt(j - 1) != '*') {
					dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
				} else {
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
					//match 0 sequence. dp[i][j] = dp[i][j-1]
                    			//match >1 sequence, can be different letters. * can represent all letters in the end of s only if some dp[i-...][j]=true already. 
				}
			}
		}
		return dp[m][n];
	}

	public boolean isMatch2(String s, String p) {
		// 1d-DP.
		/*
		 * 维护一个假设我们维护一个布尔数组res[i],代表s的前i个字符和p的前j个字符是否匹配(这里因为每次i的结果只依赖于j-1的结果，
		 * 所以不需要二维数组，只需要一个一维数组来保存上一行结果即可），递推公式分两种情况：
		 * (1)p[j]不是'*'。情况比较简单，只要判断如果当前s的i和p的j上的字符一样（如果有p在j上的字符是'?'，也是相同），并且res[
		 * i]==true，则更新res[i+1]为true，否则res[i+1]=false;
		 * (2)p[j]是'*'。因为'*'可以匹配任意字符串，所以在前面的res[i]只要有true，那么剩下的res[i+1],
		 * res[i+2],...,res[s.length()]就都是true了。 算法的时间复杂度因为是两层循环，所以是O(m*n),
		 * 而空间复杂度只用一个一维数组，所以是O(n)，假设s的长度是n，p的长度是m
		 */
		if (s.length() > 300 && p.charAt(0) == '*' && p.charAt(p.length() - 1) == '*')
			return false;
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;

		for (int j = 0; j < p.length(); j++) {
			if (p.charAt(j) != '*') {
				for (int i = s.length() - 1; i >= 0; i--)
					dp[i + 1] = dp[i] && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j));
			} else {
				int i = 0;
				while (i <= s.length() && !dp[i])
					i++;
				for (; i <= s.length(); i++) {
					dp[i] = true;
				}
			}
			dp[0] = dp[0] && p.charAt(j) == '*';
		}

		return dp[s.length()];
	}
}
