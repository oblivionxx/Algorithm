/*
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 Note: You can only move either down or right at any point in time.
 */
/*
 * Array, DP
 */
public class LT064_Minimum_Path_Sum {
    // 1.DP
    public int minPathSum1(int[][] grid) {
	if (grid == null || grid.length == 0)
	    return 0;
	int m = grid.length, n = grid[0].length;

	int[][] dp = new int[m][n];
	dp[0][0] = grid[0][0];
	for (int i = 1; i < m; i++) {
	    dp[i][0] = dp[i - 1][0] + grid[i][0];
	}

	for (int i = 1; i < n; i++) {
	    dp[0][i] = dp[0][i - 1] + grid[0][i];
	}

	for (int i = 1; i < m; i++) {
	    for (int j = 1; j < n; j++) {
		dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
	    }
	}

	return dp[m - 1][n - 1];
    }

    // improve space. add inplace
    public int minPathSum2(int[][] grid) {
	int m = grid.length;
	int n = grid[0].length;
	for (int i = 0; i < m; i++) {
	    for (int j = 0; j < n; j++) {
		if (i == 0 && j != 0) {
		    grid[i][j] = grid[i][j] + grid[i][j - 1];
		} else if (i != 0 && j == 0) {
		    grid[i][j] = grid[i][j] + grid[i - 1][j];
		} else if (i == 0 && j == 0) {
		    grid[i][j] = grid[i][j];
		} else {
		    grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
		}
	    }
	}

	return grid[m - 1][n - 1];
    }

    // 2. Dijstra. TLE
    // https://leetcode.com/discuss/10572/ac-java-dp-solution-v-s-tle-dijstra-solution
}
