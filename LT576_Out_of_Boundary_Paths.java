/*
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

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
    //The number of paths for N moves is the sum of paths for N - 1 moves from the adjacent cells. If an adjacent cell is out of the border, the number of paths is 1.
    //https://discuss.leetcode.com/topic/88492/c-6-lines-dp-o-n-m-n-6-ms
    public int findPaths(int m, int n, int N, int i0, int j0) {
        long limit = 1000000007;
        long[][][] dp = new long[N + 1][m][n];
        for (int k = 1; k <= N; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[k][i][j] += i == 0     ? 1 : dp[k - 1][i - 1][j];
                    dp[k][i][j] += i == m - 1 ? 1 : dp[k - 1][i + 1][j];
                    dp[k][i][j] += j == 0     ? 1 : dp[k - 1][i][j - 1];
                    dp[k][i][j] += j == n - 1 ? 1 : dp[k - 1][i][j + 1];
                    dp[k][i][j] %= limit;
                }
            }
        }
        return (int)dp[N][i0][j0];        
    }
}
