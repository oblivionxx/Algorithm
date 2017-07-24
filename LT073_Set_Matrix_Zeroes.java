/*
 Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 Follow up:
 Did you use extra space?
 A straight forward solution using O(mn) space is probably a bad idea.
 A simple improvement uses O(m + n) space, but still not the best solution.
 Could you devise a constant space solution?
 */
/*
 * Array
 */
public class LT073_Set_Matrix_Zeroes {
	public void setZeroes1(int[][] matrix) {
		// O(m+n) space. O(3*m*n) time
		if (matrix == null || matrix.length == 0)
			return;
		int m = matrix.length, n = matrix[0].length;

		int[] row = new int[m]; // or boolean[].
		int[] col = new int[n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					row[i] = 1;
					col[j] = 1;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			if (row[i] == 1) {
				for (int j = 0; j < n; j++)
					matrix[i][j] = 0;
			}
		}

		for (int j = 0; j < n; j++) {
			if (col[j] == 1) {
				for (int i = 0; i < m; i++)
					matrix[i][j] = 0;
			}
		}
	}

	public void setZeroes2(int[][] matrix) {
		// O(1). use row[0] and col[0] to store. and two flag store for row[0]
		// and col[0]
		if (matrix == null || matrix.length == 0)
			return;
		int m = matrix.length, n = matrix[0].length;

		boolean row = false;
		boolean col = false;

		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) {
				col = true;
				break;
			}
		}

		for (int i = 0; i < n; i++) {
			if (matrix[0][i] == 0) {
				row = true;
				break;
			}
		}

		// use row[0] and col[0] to store state.
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		if (row) {
			for (int i = 0; i < n; i++) {
				matrix[0][i] = 0;
			}
		}

		if (col) {
			for (int i = 0; i < m; i++) {
				matrix[i][0] = 0;
			}
		}

	}
}
