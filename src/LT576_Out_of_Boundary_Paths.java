/*
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). 
 * However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

Example 1:
Input:m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:
Input:m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

Note:
Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].

DP, DFS
 */
public class LT576_Out_of_Boundary_Paths {
    long limit = 1000000007;
    // The number of paths for N moves is the sum of paths for N - 1 moves from the adjacent cells. If an adjacent cell is out of the border, the number of paths is 1.
    // https://discuss.leetcode.com/topic/88492/c-6-lines-dp-o-n-m-n-6-ms
    // O(mnN). 3d-dp
    public int findPaths(int m, int n, int N, int i0, int j0) {
	long[][][] dp = new long[N + 1][m][n];			//dp[k][i][j] number of path to work to i,j using k steps
	for (int k = 1; k <= N; k++) {
	    for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
		    dp[k][i][j] += i == 0 ? 1 : dp[k - 1][i - 1][j];		//start with border[i-1][j]. then move into the center[i][j], ways of moving out is depend on dp[k - 1][i - 1][j]. add up
		    dp[k][i][j] += i == m - 1 ? 1 : dp[k - 1][i + 1][j];
		    dp[k][i][j] += j == 0 ? 1 : dp[k - 1][i][j - 1];
		    dp[k][i][j] += j == n - 1 ? 1 : dp[k - 1][i][j + 1];
		    dp[k][i][j] %= limit;
		}
	    }
	}
	return (int) dp[N][i0][j0];						//ways of moving out at position [i0,j0]
    }
    
    // space compression. We mod k by 2 to reduce the amount of extra memory needed, since we don't need to keep all of the data values from every previous iteration
    // only the data values from the previous iteration is needed.
    public int findPaths2(int m, int n, int N, int i0, int j0) {
        long[][][] dp = new long[2][m][n];
        for (int k = 1; k <= N; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[k % 2][i][j] =((i == 0     ? 1 : dp[(k - 1) % 2][i - 1][j])
                                    + (i == m - 1 ? 1 : dp[(k - 1) % 2][i + 1][j])
                                    + (j == 0     ? 1 : dp[(k - 1) % 2][i][j - 1])
                                    + (j == n - 1 ? 1 : dp[(k - 1) % 2][i][j + 1])) % limit;
                }
            }
        }
        return (int)dp[N % 2][i0][j0];        
    }
}
