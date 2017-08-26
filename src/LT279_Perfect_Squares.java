/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

DP, BFS, Math
 */
public class LT279_Perfect_Squares {
    // Math. Lagrange's Four Square theorem, there are only 4 possible results: 1, 2, 3, 4.
    // 1. n is perfect square
    // 2.
    // 3.
    // 4. if and only if n can be written in the form of 4^k*(8*m + 7). Please refer to Legendre's three-square theorem.
    // Based on Legendre's three-square theorem, if n can be written as 4^k(8m+7), it can't be represented as the sum of 1 or 2 or 3 integers.
    public int numSquares(int n) {
	while (n % 4 == 0)
	    n /= 4;
	if (n % 8 == 7)
	    return 4;
	for (int a = 0; a * a <= n; a++) {
	    int b = (int) Math.sqrt(n - a * a);
	    if (a * a + b * b == n) {
		return (a > 0 ? 1 : 0) + (b > 0 ? 1 : 0);
	    }
	}

	return 3;
    }

    // dp
    public int numSquares1(int n) {
	if (n <= 0)
	    return 0;

	int[] dp = new int[n + 1]; // number i can be expressed the least number of squares sums.

	for (int i = 1; i <= n; i++) {
	    dp[i] = Integer.MAX_VALUE;
	    for (int j = 1; j * j <= i; j++) {
		dp[i] = Math.min(dp[i], dp[i - j * j] + 1); // similar to backpack. each square can be reused.
	    }
	}

	return dp[n];
    }

    // bfs

}
