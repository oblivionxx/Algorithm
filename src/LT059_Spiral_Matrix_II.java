/*
 Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 For example,
 Given n = 3,

 You should return the following matrix:
	[
	 [ 1, 2, 3 ],
	 [ 8, 9, 4 ],
	 [ 7, 6, 5 ]
	]
 */
/*
 * Array
 */
public class LT059_Spiral_Matrix_II {
    public int[][] generateMatrix(int n) {
	int[][] res = new int[n][n];
	if (n <= 0)
	    return res;

	int rl = 0, cl = 0, rh = n - 1, ch = n - 1;
	int d = 1;
	res[0][0] = d;
	int i = 0, j = 0; // index
	while (d < n * n) {
	    while (j < ch)
		res[i][++j] = ++d;
	    rl++;
	    if (rl > rh)
		break;

	    while (i < rh)
		res[++i][j] = ++d;
	    ch--;
	    if (cl > ch)
		break;

	    while (j > cl)
		res[i][--j] = ++d;
	    rh--;
	    if (rl > rh)
		break;

	    while (i > rl)
		res[--i][j] = ++d;
	    cl++;
	    if (cl > ch)
		break;
	}

	return res;
    }
}
