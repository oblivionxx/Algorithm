/*
Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input: 
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.
Note:
The range of width and height of the input 2D array is [1,500].

Array, DFS
 */
public class LT531_Lonely_Pixel_I {
    // suppose matrix is m*n, there is at most min(m, n) lonely pixels, because there could be no more than 1 in each row, or column;
    public int findLonelyPixel(char[][] picture) {
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
		lonely += (picture[i][j] == 'B' && row[i] == 1 && col[j] == 1) ? 1 : 0; // only when row[i] col[j] only has one 'B'
	    }
	}

	return lonely;
    }
}
