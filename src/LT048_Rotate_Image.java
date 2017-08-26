/*
 You are given an n x n 2D matrix representing an image.
 Rotate the image by 90 degrees (clockwise).

 Follow up:
 Could you do this in-place?
 */
/*
 * Array
 */
public class LT048_Rotate_Image {
    public void rotate(int[][] matrix) {
	if (matrix == null || matrix.length == 0)
	    return;
	int m = matrix.length;

	for (int i = 0; i < m / 2; i++) {
	    for (int j = 0; j < Math.ceil((double) m / 2); j++) {
		int tmp = matrix[i][j];
		matrix[i][j] = matrix[m - 1 - j][i];
		matrix[m - 1 - j][i] = matrix[m - 1 - i][m - 1 - j];
		matrix[m - 1 - i][m - 1 - j] = matrix[j][m - 1 - i];
		matrix[j][m - 1 - i] = tmp;
	    }
	}
    }
}
