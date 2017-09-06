/*
 * Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels located at some specific row R and column C that align with all the following rules:

Row R and column C both contain exactly N black pixels.
For all rows that have a black pixel at column C, they should be exactly the same as row R
The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

Example:
Input:                                            
[['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'B', 'W', 'B', 'B', 'W'],    
 ['W', 'W', 'B', 'W', 'B', 'W']] 

N = 3
Output: 6
Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
        0    1    2    3    4    5         column index                                            
0    [['W', 'B', 'W', 'B', 'B', 'W'],    
1     ['W', 'B', 'W', 'B', 'B', 'W'],    
2     ['W', 'B', 'W', 'B', 'B', 'W'],    
3     ['W', 'W', 'B', 'W', 'B', 'W']]    
row index

Take 'B' at row R = 0 and column C = 1 as an example:
Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.

Note:
The range of width and height of the input 2D array is [1,200].

Array, DFS
 */
public class LT533_Lonely_Pixel_II {
    public int findBlackPixel(char[][] picture, int N) {
	int row[] = new int[picture.length];
	int col[] = new int[picture[0].length];
	if (picture == null || picture.length == 0)
	    return 0;
	for (int i = 0; i < picture.length; i++) {
	    for (int j = 0; j < picture[0].length; j++) {
		row[i] += picture[i][j] == 'B' ? 1 : 0;
		col[j] += picture[i][j] == 'B' ? 1 : 0;
	    }
	}

	int lonely = 0;
	for (int i = 0; i < picture.length; i++) {
	    for (int j = 0; j < picture[0].length; j++) {
		// Check row2
		boolean rule2 = obeysRule2(picture, i, j, picture.length, picture[0].length, row, col);
		lonely += (picture[i][j] == 'B' && row[i] == N && col[j] == N && rule2) ? 1 : 0;
		// only when row[i] col[j] only has one 'B'
	    }
	}

	return lonely;
    }

    // row2 meaning. if picture[i][j]=B. then all other picture[i'][j] = B the row should be exactly the same as row i
    private boolean obeysRule2(char[][] picture, int index, int j, int m, int n, int[] rows, int[] cols) {
	for (int i = 0; i < m; i++) { // find other row except current row index.
	    if (i == index)
		continue;
	    if (picture[i][j] == 'B') { // other row should be the same as row index
		if (rows[index] != rows[i])
		    return false; 	// number of Black points is different in row index and row i.
		for (int col = 0; col < n; col++) {
		    if (picture[i][col] != picture[index][col])		//check each column in row index and row col.
			return false;
		}
	    }
	}
	return true;
    }

    // BETTER SOLUTION USING HASHMAP
    // https://discuss.leetcode.com/topic/81686/verbose-java-o-m-n-solution-hashmap/2
}
