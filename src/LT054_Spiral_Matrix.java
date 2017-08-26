
/*
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 For example,
 Given the following matrix:
	[
	 [ 1, 2, 3 ],
	 [ 4, 5, 6 ],
	 [ 7, 8, 9 ]
	]
 You should return [1,2,3,6,9,8,7,4,5].
 */
/*
 * Array
 */
import java.util.*;

public class LT054_Spiral_Matrix {
    public List<Integer> spiralOrder(int[][] matrix) {
	List<Integer> res = new ArrayList<>();
	if (matrix == null || matrix.length == 0)
	    return res;
	int m = matrix.length, n = matrix[0].length;
	int rl = 0, cl = 0, rh = m - 1, ch = n - 1;
	res.add(matrix[0][0]);
	int i = 0, j = 0; // index.
	while (true) {
	    // left to right
	    while (j < ch)
		res.add(matrix[i][++j]);
	    rl++;
	    if (rl > rh)
		break;

	    // up to down
	    while (i < rh)
		res.add(matrix[++i][j]);
	    ch--;
	    if (cl > ch)
		break;

	    // right to left
	    while (j > cl)
		res.add(matrix[i][--j]);
	    rh--;
	    if (rl > rh)
		break;

	    // bottom to up
	    while (i > rl)
		res.add(matrix[--i][j]);
	    cl++;
	    if (cl > ch)
		break;
	}

	return res;
    }
}
