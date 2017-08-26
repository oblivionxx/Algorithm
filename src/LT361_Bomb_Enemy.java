/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note that you can only put the bomb at an empty cell.

Example:
For the given grid

0 E 0 0
E 0 W E
0 E 0 0

return 3. (Placing a bomb at (1,1) kills 3 enemies)

DP
 */
public class LT361_Bomb_Enemy {
    public int maxKilledEnemies(char[][] grid) {
	// dp[i][j] is number of enemy. scan 4 directions
	if (grid == null || grid.length == 0 || grid[0].length == 0)
	    return 0;

	int m = grid.length;
	int n = grid[0].length;
	int[][] dp = new int[m][n];

	// from left to right
	for (int i = 0; i < m; i++) {
	    int current = 0; // cur save the enemies on the left side.
	    for (int j = 0; j < n; j++)
		current = process(grid, dp, i, current, j);
	}
	// from top to bottom
	for (int j = 0; j < n; j++) {
	    int current = 0; // cur save the enemies on the top.
	    for (int i = 0; i < m; i++)
		current = process(grid, dp, i, current, j);
	}
	// from right to left
	for (int i = 0; i < m; i++) {
	    int current = 0;
	    for (int j = n - 1; j >= 0; j--)
		current = process(grid, dp, i, current, j);
	}
	int ans = 0;
	// from bottom to top
	for (int j = 0; j < n; j++) {
	    int current = 0;
	    for (int i = m - 1; i >= 0; i--) {
		current = process(grid, dp, i, current, j);
		if (grid[i][j] == '0')
		    ans = Math.max(ans, dp[i][j]);
	    }
	}

	return ans;
    }

    private int process(char[][] c, int[][] dp, int i, int current, int j) {
	if (c[i][j] == 'W')
	    current = 0;
	if (c[i][j] == 'E')
	    current++;
	dp[i][j] += current;
	return current;
    }
}
