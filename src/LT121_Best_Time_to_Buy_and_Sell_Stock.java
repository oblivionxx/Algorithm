/*
Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

DP, Array
 */
public class LT121_Best_Time_to_Buy_and_Sell_Stock {
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length==0) return 0;
        int prev = prices[0], max = Integer.MIN_VALUE;
        for(int i=0;i<prices.length;i++){
            if(prices[i]<prev) prev = prices[i];
            max = Math.max(max, prices[i]-prev);
        }
        return max;
    }
    
    public int maxProfit2(int[] prices) {
	if (prices == null || prices.length == 0)
	    return 0;
	int local = 0, max = 0;
	for (int i = 0; i < prices.length - 1; i++) {
	    local = Math.max(0, local + prices[i + 1] - prices[i]); // compare with 0.
	    max = Math.max(local, max);
	}

	return max;
    }
}
