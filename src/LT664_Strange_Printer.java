/*
 * There is a strange printer with the following two special requirements:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

Example 1:
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
Hint: Length of the given string will not exceed 100.

DP, DFS
 */
public class LT664_Strange_Printer {
    /*
     * 就拿aba来说，最优路径是aaa -> b，所以对于打印机来说，打印单个a和多个a的代价是一样的，在考虑问题时，直接打印多个即可。那么接下来就要找出代价会变小的原因。 aabb 方案1： aaaa -> bb 方案2： aa —> bb 方案1与方案2等价 aaba 方案1： aaaa —> b 方案2： aa -> b -> a 方案1优于方案2 所以找到了优化准则：
     * 对于被a包裹的子问题b可以用递归求解，那么问题就变成了，如何遍历所有可能的包裹方案aa...b...a。 用dp[i][j]表示在某个区间内的最小代价， 现在假设第i个位置的字符为a，那么在i和j的范围内，找寻下一个字符为a，记作下标为m，则可以将问题划分为：dp[i + 1][m - 1] 和 dp[m][j]
     * https://discuss.leetcode.com/topic/100240/java-o-n-3-dp-solution-with-explanation-and-simple-optimization
     */
    public int strangePrinter(String s) {
	int[][] dp = new int[101][101];
	int n = s.length();
	if (n == 0)
	    return 0;
	char[] cs = s.toCharArray();
	for (int i = 0; i < n; ++i)
	    dp[i][i] = 1;
	for (int i = n - 1; i >= 0; --i) {
	    for (int j = i + 1; j < n; ++j) {
		dp[i][j] = dp[i + 1][j] + 1;			//init with maxValue. must print one more time for the letter added at i.
		char c = cs[i];
		for (int k = i; k < j; ++k) {
		    if (cs[k + 1] == c)				//dp[i][k] is subproblem that aa..b..a, it minus the printing needs as soon as it found a same char
			dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] - 1);	
		}
	    }
	}
	return dp[0][n - 1];
    }
}
