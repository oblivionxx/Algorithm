/*
 * In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element appears in its original position.

There's originally an array consisting of n integers from 1 to n in ascending order, you need to find the number of derangement it can generate.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: 3
Output: 2
Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
Note:
n is in the range of [1, 106].

Math
 */
public class LT634_Find_the_Derangement_of_An_Array {
    // dp. place 1 at i's position =>1) put i on 1's position = dp[i-2] 2) disarrange the rest. i is not on 1's position dp[i-1]
    // d(i)=(i−1)∗[d(i−1)+d(i−2)] //i could be put on 1~i-1 positions.
    // O(n)
    public int findDerangement(int n) {
	int[] dp = new int[n + 1];
	dp[0] = 1;
	dp[1] = 0;
	for (int i = 2; i <= n; i++)
	    dp[i] = (int) (((i - 1L) * (dp[i - 1] + dp[i - 2])) % 1000000007);
	return dp[n];
    }
}
