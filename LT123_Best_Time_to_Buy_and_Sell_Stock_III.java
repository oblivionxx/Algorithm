/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Array, DP
 */
public class LT123_Best_Time_to_Buy_and_Sell_Stock_III {

	//1. use generalized k time transaction. then apply k=2
	
	//2. use 2 dp.
	public int maxProfit2(int[] prices) {
        if(prices==null || prices.length==0) return 0;
        int release1=0, release2=0;
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        
        for(int i:prices){
            release2 = Math.max(release2, hold2+i);
            hold2 = Math.max(hold2, release1-i);
            release1 = Math.max(release1, hold1+i);
            hold1 = Math.max(hold1, -i);
        }
        
        return release2;
    }
	
	//3. complexity dp.
	public int maxProfit3(int[] prices) {  
        if(prices.length == 0){  
            return 0;  
        }  
          
        int max = 0;  
        // dp数组保存左边和右边的利润最大值  
        int[] left = new int[prices.length];        // 计算[0,i]区间的最大值  
        int[] right = new int[prices.length];   // 计算[i,len-1]区间的最大值  
          
        process(prices, left, right);  
          
        // O(n)找到最大值  
        for(int i=0; i<prices.length; i++){  
            max = Math.max(max, left[i]+right[i]);  
        }  
          
        return max;  
    }  
      
    public void process(int[] prices, int[] left, int[] right){  
        left[0] = 0;  
        int min = prices[0];        // 最低买入价  
          
        // 左边递推公式  
        for(int i=1; i<left.length; i++){  
            left[i] = Math.max(left[i-1], prices[i]-min);   // i的最大利润为（i-1的利润）和（当前卖出价和之前买入价之差）的较大那个  
            min = Math.min(min, prices[i]);     // 更新最小买入价  
        }  
          
        right[right.length-1] = 0;  
        int max = prices[right.length-1];       // 最高卖出价  
        // 右边递推公式  
        for(int i=right.length-2; i>=0; i--){  
            right[i] = Math.max(right[i+1], max-prices[i]); // i的最大利润为（i+1的利润）和（最高卖出价和当前买入价之差）的较大那个  
            max = Math.max(max, prices[i]);     // 更新最高卖出价  
        }  
          
    }  
}
