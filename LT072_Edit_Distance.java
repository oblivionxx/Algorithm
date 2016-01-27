/*
 Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

 You have the following 3 operations permitted on a word:
 a) Insert a character
 b) Delete a character
 c) Replace a character
 */
/*
 * String, DP
 */
public class LT072_Edit_Distance {
    public int minDistance(String word1, String word2) {
        //2d-DP dp[i][j]表示从word1前i个字符转换到word2前j个字符最少的步骤数
    	/*1. x==y，那么不用做任何编辑操作，所以dp[i][j] = dp[i-1][j-1]
        2. x != y
                (1) 在word1插入y， 那么dp[i][j] = dp[i][j-1] + 1
                (2) 在word1删除x， 那么dp[i][j] = dp[i-1][j] + 1
                (3) 把word1中的x用y来替换，那么dp[i][j] = dp[i-1][j-1] + 1
              最少的步骤就是取这三个中的最小值。
         */
    	//word1 and word2 can be empty string.
    	int m = word1.length(), n = word2.length();
    	//dp[i][j]表示从word1前i个字符转换到word2前j个字符最少的步骤数
    	int[][] dp = new int[m+1][n+1];	//
    	for(int i=0;i<=m;i++){
    		dp[i][0] = i;
    	}
    	
    	for(int j=0;j<=n;j++){
    		dp[0][j] = j;
    	}
    	
    	for(int i=1;i<=m;i++){
    		char l1 = word1.charAt(i-1);
    		for(int j=1;j<=n;j++){
    			char l2 = word2.charAt(j-1);
    			if(l1==l2)
    				dp[i][j] = dp[i-1][j-1];
    			else{
    				int replace = dp[i-1][j-1]+1;
                    int insert = dp[i-1][j]+1;
                    int delete = dp[i][j-1]+1;
                    
                    dp[i][j] = Math.min(Math.min(replace, insert),delete);
    			}
    		}
    	}
    	
    	return dp[m][n];
    }
}
