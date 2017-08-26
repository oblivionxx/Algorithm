/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Array, Greedy
 */
public class LT122_Best_Time_to_Buy_and_Sell_Stock_II {
    // hackerrank has another definition.
    // https://www.hackerrank.com/challenges/stockmax/submissions/code/15526407
    public int maxProfit(int[] prices) {
	if (prices == null || prices.length == 0)
	    return 0;
	int max = 0;
	for (int i = 0; i < prices.length - 1; i++) {
	    max += Math.max(prices[i + 1] - prices[i], 0);
	}

	return max;
    }
}
