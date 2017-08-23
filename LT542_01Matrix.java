import java.util.*;

/*
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.

DFS, BFS
 */
public class LT542_01Matrix {
    // start from 0 and propergate. mark 1 position to be MAX.
    public int[][] updateMatrix(int[][] matrix) {
	int m = matrix.length;
	int n = matrix[0].length;

	Queue<int[]> queue = new LinkedList<>();
	for (int i = 0; i < m; i++) {
	    for (int j = 0; j < n; j++) {
		if (matrix[i][j] == 0) {
		    queue.offer(new int[] { i, j });
		} else {
		    matrix[i][j] = Integer.MAX_VALUE;
		}
	    }
	}

	int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	while (!queue.isEmpty()) {
	    int[] cell = queue.poll();
	    for (int[] d : dirs) {
		int r = cell[0] + d[0];
		int c = cell[1] + d[1];
		if (r < 0 || r >= m || c < 0 || c >= n || matrix[r][c] <= matrix[cell[0]][cell[1]] + 1)
		    continue; // skip path with smaller value or point to 0
		queue.add(new int[] { r, c });
		matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
	    }
	}

	return matrix;
    }
    
    //in-place dp. update matrix from 4 directions.
    public int[][] updateMatrix2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
                if(i>0 && matrix[i-1][j]!=Integer.MAX_VALUE){
                    matrix[i][j] = Math.min(matrix[i-1][j]+1, matrix[i][j]);
                }
                if(j>0 && matrix[i][j-1]!=Integer.MAX_VALUE){
                    matrix[i][j] = Math.min(matrix[i][j-1]+1, matrix[i][j]);
                }
            }
        }
        
        for (int i = m-1; i >=0; i--) {
            for (int j = n-1; j >=0; j--) {
                if(i<m-1 && matrix[i+1][j]!=Integer.MAX_VALUE){
                    matrix[i][j] = Math.min(matrix[i+1][j]+1, matrix[i][j]);
                }
                if(j<n-1 && matrix[i][j+1]!=Integer.MAX_VALUE){
                    matrix[i][j] = Math.min(matrix[i][j+1]+1, matrix[i][j]);
                }
            }
        }
        return matrix;
    }
}
