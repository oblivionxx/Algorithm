/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).


DP.
 */
public class LT188_Best_Time_to_Buy_and_Sell_Stock_IV {
	public int maxProfit(int k, int[] prices) {
        int len = prices.length;  
        if(len == 0) {  
            return 0;  
        }  
        
        if (k >= len){			//Apply infinite transaction times.
            return helper(prices);
        }
        
        //2d dp
        int[][] local = new int[len][k+1];      // local[i][j]: max profit till i day, j transactions, where there is transaction happening on i day  
        int[][] global = new int[len][k+1];     // global[i][j]: max profit across i days, j transactions  
        for(int i=1; i<len; i++) {  
            int diff = prices[i] - prices[i-1];  
            for(int j=1; j<=k; j++) {  
                local[i][j] = Math.max(global[i-1][j-1]+Math.max(diff,0), local[i-1][j]+diff);  
                //局部（在第i天交易后，总共交易了j次） =  max{情况2，情况1}
                //情况1：在第i-1天时，恰好已经交易了j次（local[i-1][j]），那么如果i-1天到i天再交易一次：即在第i-1天买入，第i天卖出（diff），by definition, jth transaction on i day. so diff should definitely add to it.
                //情况2：第i-1天后，共交易了j-1次（global[i-1][j-1]），因此为了满足“第i天过后共进行了j次交易，且第i天必须进行交易”的条件：我们可以选择1：在第i-1天买入，然后再第i天卖出（diff），或者选择在第i天买入，然后同样在第i天卖出（收益为0）。
                
                global[i][j] = Math.max(global[i-1][j], local[i][j]);   //max{局部（在第i天交易后，恰好满足j次交易），全局（到达第i-1天时已经满足j次交易）} => jth 交易在第i天还是在第i天之前
            }  
        }  
        return global[len-1][k];  
    
    }
    
    
    public int helper(int[] prices){
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++){
            profit = Math.max(profit, profit + prices[i + 1] - prices[i]);
        }

        return profit;
    }
}
