/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]

DP
 */
public class LT309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
    public int maxProfit(int[] prices) {
	// buy[i] = max(sell[i-2]-price, buy[i-1])
	// sell[i] = max(buy[i-1]+price, sell[i-1])
	// https://leetcode.com/discuss/71354/share-my-thinking-process
	// can reduce O(n) space to O(1)
	// https://leetcode.com/discuss/71391/easiest-java-solution-with-explanations

	if (prices == null || prices.length == 0)
	    return 0;

	int buy = Integer.MIN_VALUE, prebuy = buy, sell = 0, presell = sell;
	for (int p : prices) {
	    prebuy = buy;
	    buy = Math.max(presell - p, buy);
	    presell = sell;
	    sell = Math.max(prebuy + p, sell);
	}

	return sell;
    }
}
