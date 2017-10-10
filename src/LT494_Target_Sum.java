/*
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.

DPS, DP
 */
public class LT494_Target_Sum {
    private int res = 0;

    public int findTargetSumWays(int[] nums, int S) {
	// recursion. O(2^n) but can prune eg. remaining number all sum or all
	// substract is less or larger than S
	helper(nums, S, 0);
	return res;
    }

    public void helper(int[] nums, int S, int index) {
	if (index == nums.length) {
	    if (S == 0)
		res++;
	    return;
	}

	helper(nums, S - nums[index], index + 1);
	helper(nums, S + nums[index], index + 1);
    }

    // dp O(l∗n)
    // dp[n-1,e]来表示数列中只有n-1个数、题目期望值为e时输出的答案。那么我们真正需要的答案用这个方法表示自然就是dp[n,S]
    // 因为第n个数的符号非正即负只有两种状态，所以dp[n,S]=dp[n-1,S-an]+dp[n-1,S+an]
    // 用样例来说明，第五个数为1，那么只有前四个数和为4（第五个数符号为+）或者6（第五个数符号为-）的时候可以得到期望值。即我们需要的就是前4个数有多少种组合可以令和为4或者为6的，并让他们相加
    // DP的初始状态为只有一个数的时候，我们让dp[1,a1]和dp[1,-a1]均为1即可（额外注意当a1=0时为特殊情况，应令dp[1,0]=2）
    // 我们刚刚思路中用的dp的第二个下标是可以为负数的，范围为[-sum,sum]（排除掉S>sum和S<-sum这两种已知无解的情况）。然而我们程序中数组的下标是从0开始的，所以我们把这个下标的值都加上一个sum，让它的范围变成[0,2*sum]
    public int findTargetSumWays2(int[] nums, int S) {
	int sum = 0;
	for (int n : nums) {
	    sum += n;
	}
	if (sum < Math.abs(S)) {
	    return 0;
	}

	int doubleSize = sum << 1;
	int[][] dp = new int[nums.length][doubleSize + 1];
	if (nums[0] == 0) {
	    dp[0][sum] = 2;
	} else {
	    dp[0][sum - nums[0]] = 1;
	    dp[0][sum + nums[0]] = 1; // why return dp[len][S+sum] see here. must include the situation to the farthest S+sum/S-sum. offset sum
	}

	for (int i = 1; i < nums.length; i++) {
	    for (int j = 0; j <= doubleSize; j++) {
		if (j >= nums[i]) {
		    dp[i][j] += dp[i - 1][j - nums[i]];
		}
		if (j + nums[i] <= doubleSize) {
		    dp[i][j] += dp[i - 1][j + nums[i]];
		}
	    }
	}

	// as given sum can range from -1000 to +1000, we need to add an offset of 1000 to the sum indices (column number) to map all the sums obtained to positive range only.
	// could init int[][] dp = new int[nums.length][2001] and get dp[nums.length - 1][S + 1000]
	return dp[nums.length - 1][S + sum];		//offset sum.

    }
}
