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
    
    
    public int maxKilledEnemies2(char[][] grid) {
        if(grid == null || grid.length == 0 ||  grid[0].length == 0) return 0;
        int max = 0;
        int row = 0;
        int[] col = new int[grid[0].length];
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[0].length;j++){
                if(grid[i][j] == 'W') continue;
                if(j == 0 || grid[i][j-1] == 'W'){
                     row = killedEnemiesRow(grid, i, j);
                }
                if(i == 0 || grid[i-1][j] == 'W'){
                     col[j] = killedEnemiesCol(grid,i,j);
                }
                if(grid[i][j] == '0'){//十字路口题，这个0改为E就可以了，题就只有E和W，代表1，0
                    max = (row + col[j] > max) ? row + col[j] : max;
                }
            }
    
        }
        
        return max;
    }
    
    //calculate killed enemies for row i from column j
    private int killedEnemiesRow(char[][] grid, int i, int j){
        int num = 0;
        while(j <= grid[0].length-1 && grid[i][j] != 'W'){
            if(grid[i][j] == 'E') num++;
            j++;
        }
        return num;
    }
    //calculate killed enemies for  column j from row i
    private int killedEnemiesCol(char[][] grid, int i, int j){
        int num = 0;
        while(i <= grid.length -1 && grid[i][j] != 'W'){
            if(grid[i][j] == 'E') num++;
            i++;
        }
        return num;
    }
}
