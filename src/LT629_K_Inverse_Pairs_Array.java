/*
 * Given two integers n and k, find how many different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.

We define an inverse pair as following: For ith and jth element in the array, if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's not.

Since the answer may be very large, the answer should be modulo 109 + 7.

Example 1:
Input: n = 3, k = 0
Output: 1
Explanation: 
Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pair.
Example 2:
Input: n = 3, k = 1
Output: 2
Explanation: 
The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
Note:
The integer n is in the range [1, 1000] and k is in the range [0, 1000].

DP
 */

public class LT629_K_Inverse_Pairs_Array {
// 	这道题给了我们1到n总共n个数字，让我们任意排列数组的顺序，使其刚好存在k个翻转对，所谓的翻转对，就是位置在前面的数字值大，而且题目中表明了结果会很大很大，要我们对一个很大的数字取余。对于这种结果巨大的题目，劝君放弃暴力破解或者是无脑递归，想都不用想，那么最先应该考虑的就是DP的解法了。我们需要一个二维的DP数组，其中dp[i][j]表示1到i的数字中有j个翻转对的排列总数，那么我们要求的就是dp[n][k]了，即1到n的数字中有k个翻转对的排列总数。现在难点就是要求递推公式了。我们想如果我们已经知道dp[n][k]了，怎么求dp[n+1][k]，先来看dp[n+1][k]的含义，是1到n+1点数字中有k个翻转对的个数，那么实际上在1到n的数字中的某个位置加上了n+1这个数，为了简单起见，我们先让n=4，那么实际上相当于要在某个位置加上5，那么加5的位置就有如下几种情况：
// 	xxxx5
// 	xxx5x
// 	xx5xx
// 	x5xxx
// 	5xxxx
// 	这里xxxx表示1到4的任意排列，那么第一种情况xxxx5不会增加任何新的翻转对，因为xxxx中没有比5大的数字，而 xxx5x会新增加1个翻转对，xx5xx，x5xxx，5xxxx分别会增加2，3，4个翻转对。那么xxxx5就相当于dp[n][k]，即dp[4][k]，那么依次往前类推，就是dp[n][k-1], dp[n][k-2]...dp[n][k-n]，这样我们就可以得出dp[n+1][k]的求法了:
// 	dp[n+1][k] = dp[n][k] + dp[n][k-1] + ... + dp[n][k - n]
// 	那么dp[n][k]的求法也就一目了然了:
// 	dp[n][k] = dp[n - 1][k] + dp[n - 1][k-1] + ... + dp[n - 1][k - n + 1]
    public int kInversePairs(int n, int k) {
        int M = 1000000007;
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                for (int m = 0; m <= k; ++m) {
                    if (m - j >= 0 && m - j <= k) {
                        dp[i][m] = (dp[i][m] + dp[i - 1][m - j]) % M;
                    }
                }
            }
        }
        return dp[n][k];
    }
	
	
    // dp[n][k] denotes the number of arrays that have k inverse pairs for array composed of 1 to n
    // we can establish the recursive relationship between dp[n][k] and dp[n-1][i]:
    //
    // if we put n as the last number then all the k inverse pair should come from the first n-1 numbers
    // if we put n as the second last number then there's 1 inverse pair involves n so the rest k-1 comes from the first n-1 numbers
    // ...
    // if we put n as the first number then there's n-1 inverse pairs involve n so the rest k-(n-1) comes from the first n-1 numbers
    //
    // dp[n][k] = dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k+1-n+1]+dp[n-1][k-n+1]
    //
    // It's possible that some where in the right hand side the second array index become negative, since we cannot generate negative inverse pairs we just treat them as 0, but still leave the item
    // there as a place holder.
    //
    // dp[n][k] = dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k+1-n+1]+dp[n-1][k-n+1]
    // dp[n][k+1] = dp[n-1][k+1]+dp[n-1][k]+dp[n-1][k-1]+dp[n-1][k-2]+...+dp[n-1][k+1-n+1]
    //
    // so by deducting the first line from the second line, we have
    //
    // dp[n][k+1] = dp[n][k]+dp[n-1][k+1]-dp[n-1][k+1-n]

    // https://leetcode.com/articles/k-inverse-pairs-array/
    public static int kInversePairs(int n, int k) {
	int mod = 1000000007;
	if (k > n * (n - 1) / 2 || k < 0)
	    return 0;
	if (k == 0 || k == n * (n - 1) / 2)
	    return 1;
	long[][] dp = new long[n + 1][k + 1];
	dp[2][0] = 1;
	dp[2][1] = 1;
	for (int i = 3; i <= n; i++) {
	    dp[i][0] = 1;
	    for (int j = 1; j <= Math.min(k, i * (i - 1) / 2); j++) {
		dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
		if (j >= i)
		    dp[i][j] -= dp[i - 1][j - i];
		dp[i][j] = (dp[i][j] + mod) % mod;
	    }
	}
	return (int) dp[n][k];
    }
}
