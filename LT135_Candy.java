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
	//two pass O(n) 
    //left to right. if rating ++, candy++. else give 1 candy
    //right to left. if rating ++, candy++. else keep the same. guarantee the 2nd requirement on both directions.
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candy = new int[len];

        candy[0] =1;
        for (int i = 1; i < len; ++i) {
            if (ratings[i] > ratings[i-1]) {
                candy[i] = candy[i-1] + 1;
            } else {
                candy[i] = 1;
            }
        }

        int total = candy[len-1];
        for (int i = len - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i+1] && candy[i] <= candy[i+1]) {
                candy[i] = candy[i+1] + 1;
            }
            total += candy[i];    
        }
        return total;
    }
}
