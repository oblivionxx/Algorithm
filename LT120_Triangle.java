/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

Array, DP
 */
import java.util.*;
public class LT120_Triangle {
	//bottom up.
	public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null || triangle.size()==0) return 0;
        int dp[] = new int[triangle.size()];			//dp[i] store the path sum.
        int len = triangle.size();
        
        //start from last row.
        for(int i=0;i<len;i++)
            dp[i] = triangle.get(len-1).get(i);
        
        for(int i=len-2;i>=0;i--){ //row
            for(int j=0;j<=i;j++){
                dp[j] = Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        }
        
        return dp[0];
        
    }
}
