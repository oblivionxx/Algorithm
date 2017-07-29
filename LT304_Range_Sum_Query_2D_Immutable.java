/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.

DP
 */
public class LT304_Range_Sum_Query_2D_Immutable {

	public int[][] dp;
    public LT304_Range_Sum_Query_2D_Immutable(int[][] matrix) {
        int m = matrix.length;
        if(matrix==null || m==0){ 
            dp=null;
        }
        
        else{
            int n = matrix[0].length;
            dp = new int[m][n];
            dp[0][0] = matrix[0][0];
            
            for(int i=1;i<m;i++)
                dp[i][0] = dp[i-1][0]+matrix[i][0];
            
            for(int j=1;j<n;j++)
                dp[0][j] = dp[0][j-1]+matrix[0][j];
                
            for(int i=1;i<m;i++){
                for(int j=1;j<n;j++){
                    dp[i][j] = dp[i][j-1]+dp[i-1][j]-dp[i-1][j-1]+matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(dp==null|| row1 > row2 || col1 > col2) return 0;
        
        if (row1 == 0 && col1 == 0)
            return dp[row2][col2];
        else if (row1 == 0)
            return dp[row2][col2] - dp[row2][col1 - 1];
        else if (col1 == 0)
            return dp[row2][col2] - dp[row1 - 1][col2];
        else
            return dp[row2][col2] - dp[row1-1][col2] - dp[row2][col1-1] + dp[row1-1][col1-1];
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
