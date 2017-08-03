/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.

Binary Search
 */
public class LT302_Smallest_Rectangle_Enclosing_Black_Pixels {
    //1. Binary Search O(mlgn + nlgm)
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int left = searchColumns(image, 0, y, 0, m, true);
        int right = searchColumns(image, y + 1, n, 0, m, false);
        int top = searchRows(image, 0, x, left, right, true);
        int bottom = searchRows(image, x + 1, m, left, right, false);
        return (right - left) * (bottom - top);
    }
    
    private int searchColumns(char[][] image, int i, int j, int top, int bottom, boolean whiteToBlack) {
        while (i != j) {
            int k = top, mid = (i + j) / 2;
            while (k < bottom && image[k][mid] == '0') ++k;
            if (k < bottom == whiteToBlack) // k < bottom means the column mid has black pixel
                j = mid; //search the boundary in the smaller half
            else
                i = mid + 1; //search the boundary in the greater half
        }
        return i;
    }
    private int searchRows(char[][] image, int i, int j, int left, int right, boolean whiteToBlack) {
        while (i != j) {
            int k = left, mid = (i + j) / 2;
            while (k < right && image[mid][k] == '0') ++k;
            if (k < right == whiteToBlack) // k < right means the row mid has black pixel
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }

    //2.Normal DFS. Like island. O(mn)
    private int top, bottom, left, right;

    public int minArea2(char[][] image, int x, int y) {		//given x.y is a black pixel. connected black area
	if (image.length == 0 || image[0].length == 0)
	    return 0;
	top = bottom = x;
	left = right = y;
	dfs(image, x, y);
	return (right - left) * (bottom - top);
    }

    private void dfs(char[][] image, int x, int y) {
	if (x < 0 || y < 0 || x >= image.length || y >= image[0].length || image[x][y] == '0')
	    return;
	image[x][y] = '0'; // mark visited black pixel as white
	top = Math.min(top, x);
	bottom = Math.max(bottom, x + 1);
	left = Math.min(left, y);
	right = Math.max(right, y + 1);
	dfs(image, x + 1, y);
	dfs(image, x - 1, y);
	dfs(image, x, y - 1);
	dfs(image, x, y + 1);
    }
    
    // 3. BFS.
    /*
     * Theorem If there are only one black pixel region, then in a projected 1D
     * array all the black pixels are connected. Proof by contradiction Assume
     * to the contrary that there are disconnected black pixels at i and j where
     * i < j in the 1D projection array. Thus there exists one column k, k in
     * (i, j) and and the column k in the 2D array has no black pixel. Therefore
     * in the 2D array there exists at least 2 black pixel regions separated by
     * column k which contradicting the condition of
     * "only one black pixel region". Therefore we conclude that all the black
     * pixels in the 1D projection array is connected. This means we can do a
     * binary search in each half to find the boundaries, if we know one black
     * pixel's position. And we do know that.
     * 
     * To find the left boundary, do the binary search in the [0, y) range and
     * find the first column vector who has any black pixel.
     * 
     * To determine if a column vector has a black pixel is O(m) so the search
     * in total is O(m log n)
     * 
     * We can do the same for the other boundaries. The area is then calculated
     * by the boundaries. Thus the algorithm runs in O(m log n + n log m)
     */
    public int minArea3(char[][] image, int x, int y) {
	int m = image.length;
	if (m == 0)
	    return 0;
	int n = image[0].length;

	int l = search(image, 0, x, 0, n, true, true);
	int r = search(image, x + 1, m, 0, n, false, true);
	int top = search(image, 0, y, l, r, true, false);
	int bottom = search(image, y + 1, n, l, r, false, false);

	return (r - l) * (bottom - top);
    }

    public int search(char[][] image, int start, int end, int min, int max, boolean first, boolean row) {
	// start end is used in binary search.
	// first means whether we search for the left/up or the right/down
	// boundary
	// row means whether we search for the row or the column
	while (start != end) {
	    int mid = (start + end) / 2;
	    int k = min;
	    while (k < max && white(image, mid, k, row))
		k++;

	    if (k < max == first)
		end = mid;
	    else
		start = mid + 1;
	}
	return start;
    }

    boolean white(char[][] image, int mid, int k, boolean row) {
	return (row ? image[mid][k] : image[k][mid]) == '0';
    }
}
