import java.util.Stack;

/*
 Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 Array, HashTable, Stack, DP
 */
public class LT085_Maximum_Rectangle {
    // 1. by applying Q84.
    public int maximalRectangle(char[][] matrix) {
	if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	    return 0;
	int m = matrix.length;
	int n = matrix[0].length;
	int max = 0;

	int[] height = new int[n]; // 对每一列构造数组
	for (int i = 0; i < m; i++) { // loop each row.
	    for (int j = 0; j < n; j++) {
		if (matrix[i][j] == '0')// 如果遇见0，这一列的高度就为0了
		    height[j] = 0;
		else
		    height[j] += 1;
	    }
	    max = Math.max(largestRectangleArea(height), max);
	}

	return max;
    }

    public int largestRectangleArea(int[] height) {
	int len = height.length;
	Stack<Integer> s = new Stack<Integer>();
	int maxArea = 0;
	for (int i = 0; i <= len; i++) {
	    int h = (i == len ? 0 : height[i]);
	    if (s.isEmpty() || h >= height[s.peek()]) {
		s.push(i); // stack save asc bars
	    } else {
		int tp = s.pop();
		maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek())); // i - 1 - s.peek() is the width.
		i--;
	    }
	}
	return maxArea;
    }

    // 2.DP O(n^3). n rows. in each row, n columns, in each columns. compare n
    // times of left and right. so worst case is n^3.
    /*
     * Open matrix from top to the bottom line by line. Let the maximal rectangle area at row i and column j be computed by [right(i,j) - left(i,j)]*height(i,j). left(i,j) = max(left(i-1,j), curleft),
     * curleft can be determined from the current row right(i,j) = min(right(i-1,j), curright), curright can be determined from the current row height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
     * height(i,j) = 0, if matrix[i][j]=='0';
     * 对于某个点(i,j)，以该点为底边上的一点，以这个点向上能到达的高度的最大值为高，以在这条高度轴线上每一行向左右两边展开能到达的距离的最小值为宽的矩形为该点能取的矩形的最大值（好像并不是最大值，但一定会有一个点能取到最大的矩形）。用height[i][j]来记录（i， j）这个点向上能取的最大高度，用left[i][j]来记录(i, j)能取的最左边的左边界，用right[i][j]来记录（i，j）能取的最右边的右边界。因为当前点的各个值都只和上一行的点有关
     */
    public static int maximalRectangle2(char[][] matrix) {
	if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
	    return 0;
	int m = matrix.length;
	int n = matrix[0].length;
	int max = 0;
	// dp saved for current row. when loop for next row. used as previous
	// row, just compare with current value and update.
	int[] left = new int[n];
	int[] right = new int[n];
	for (int i = 0; i < n; i++) {
	    right[i] = n; // be default, right array is n.
	}
	int[] height = new int[n];

	for (int i = 0; i < m; i++) {
	    int cur_left = 0, cur_right = n;
	    // write separately
	    // compute height
	    for (int j = 0; j < n; j++) {
		if (matrix[i][j] == '1')
		    height[j]++;
		else
		    height[j] = 0;
	    }

	    // compute left
	    for (int j = 0; j < n; j++) {
		if (matrix[i][j] == '1')
		    left[j] = Math.max(left[j], cur_left);
		else {
		    left[j] = 0; // can ensure left[j] is left most border can reach
		    cur_left = j + 1;
		}
	    }

	    // compute right
	    for (int j = n - 1; j >= 0; j--) {
		if (matrix[i][j] == '1')
		    right[j] = Math.min(right[j], cur_right);
		else {
		    right[j] = n; // set default. since later will compute min.
		    cur_right = j;		//if udpate curRight default = j-1. when calculate max should use right-left+1
		}
	    }

	    for (int j = 0; j < n; j++)
		max = Math.max(max, (right[j] - left[j]) * height[j]);   //at j, how many coninuous 1s on left ang right. 
	}
	return max;
    }

    public static void main(String[] args){
	char[][] matrix = {{'0','1','0'},{'0','1','1'}};
	maximalRectangle2(matrix);
    }
}
