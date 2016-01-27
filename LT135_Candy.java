/*
There are N children standing in a line. Each child is assigned a rating value.
You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Greedy
 */
public class LT135_Candy {
	//left to right, then right to left, then sum up.
	//O(n) left-to-right check and then right-to-left. O(n^2) is not good
	public int candy(int[] ratings) {
        if(ratings==null || ratings.length==0) return 0;
        int len = ratings.length;
        int[] dp = new int[len];
        int cur = 1;
        int sum = 0;
        dp[0]=1;
        //left to right, from index 1 to index len-1
        for(int i=1;i<len;i++){
            if(ratings[i]>ratings[i-1])
                cur++;
            else
                cur = 1;
            dp[i] = cur;
        }
        
        //right to left. from index len-2 to 0
        for(int i=len-2;i>=0;i--){
            if(ratings[i]>ratings[i+1])
                cur++;
            else
                cur= 1;
            dp[i] = Math.max(cur, dp[i]);
            sum +=dp[i];
        }
        
        sum +=dp[len-1];
        return sum;
    }
}
