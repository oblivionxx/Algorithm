import java.util.Arrays;

/*
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].

DP
 */
public class LT646_Maximum_Length_of_Pair_Chain {
    public int findLongestChain(int[][] pairs) {
	Arrays.sort(pairs, (a, b) -> a[1] - b[1]); // sort 2d array by end

	int count = 0, tmpEnd = Integer.MIN_VALUE;
	for (int[] pair : pairs) {
	    if (pair[0] > tmpEnd) {
		count++;
		tmpEnd = pair[1];
	    }
	}
	return count;
    }

    // dp
    public int findLongestChain2(int[][] pairs) {
	Arrays.sort(pairs, (a, b) -> (a[1] - b[1])); // doesn't matter sort by start or end.
	int i, j, max = 0, n = pairs.length;
	int dp[] = new int[n];

	for (i = 0; i < n; i++) {
	    dp[i] = 1;
	}

	for (i = 1; i < n; i++) {
	    for (j = 0; j < i; j++) {
		if (pairs[i][0] > pairs[j][1] && dp[i] < dp[j] + 1) {
		    dp[i] = dp[j] + 1;
		}
	    }
	    max = Math.max(max, dp[i]);
	}

	return max;
    }
}
