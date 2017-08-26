/*
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

DP, Bit Manipulation
 */
public class LT338_Counting_Bits {
    public int[] countBits(int num) {
	// use dp to save
	// Index : 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
	// num : 0 1 1 2 1 2 2 3 1 2 2 3 2 3 3 4

	// dp[i] = dp[i/2]+i%2
	int[] dp = new int[num + 1];
	dp[0] = 0;
	for (int i = 1; i <= num; i++) {
	    dp[i] = dp[i >> 1] + i % 2;
	}

	return dp;
    }
}
