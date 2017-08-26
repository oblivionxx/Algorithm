/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Array, DP
 */
public class LT123_Best_Time_to_Buy_and_Sell_Stock_III {

	// 1. use generalized k time transaction. then apply k=2
	// 2.
	public int maxProfit2(int[] prices) {
		// 设p(i) = 区间[0,1,2...i]的最大利润 + 区间[i,i+1,....n-1]的最大利润
		// 左右两个区间内分别只能有一次买卖，这就是第一道题的问题 那么本题的最大利润 =
		// max{p[0],p[1],p[2],...,p[n-1]}

		if (prices.length <= 1)
			return 0;
		// left to right
		int[] dp = new int[prices.length];
		int minV = Integer.MAX_VALUE;
		int maxRes = 0;
		for (int i = 0; i < prices.length; i++) {
			if (minV > prices[i])
				minV = prices[i];
			maxRes = Math.max(maxRes, prices[i] - minV);
			dp[i] = maxRes;
		}

		int res = dp[prices.length - 1];

		// right to left
		int maxV = Integer.MIN_VALUE;
		maxRes = 0;
		for (int i = prices.length - 1; i >= 0; i--) {
			if (maxV < prices[i])
				maxV = prices[i];
			maxRes = Math.max(maxRes, maxV - prices[i]);
			res = Math.max(res, maxRes + dp[i]);
		}

		return res;

	}

	// 3. use 2 dp.
	public int maxProfit3(int[] prices) {
		if (prices == null || prices.length == 0)
			return 0;
		int release1 = 0, release2 = 0;
		int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;

		for (int i : prices) {
			release2 = Math.max(release2, hold2 + i);
			hold2 = Math.max(hold2, release1 - i);
			release1 = Math.max(release1, hold1 + i);
			hold1 = Math.max(hold1, -i);
		}

		return release2;
	}
}
