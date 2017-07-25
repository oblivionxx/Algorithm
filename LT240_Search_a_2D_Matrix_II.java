/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.

Divide and Conquer, Binary Search
 */
public class LT240_Search_a_2D_Matrix_II {
	// We start search the matrix across diagnol. if we use top left and bottom right, binary search cannot determine what next to search. 
	// So choose either top right or bottom left. Can narrow to 1/4 matrix.
	// From top right corner, initialize the current position to top right corner, if the target is greater than the value in current position, then the target can not be in entire row of current position because the row is sorted, if the target is less than the value in current position, then the target can not in the entire column because the column is sorted too.
	// We can rule out one row or one column each time, so the time complexity is O(m+n).

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
	
	public boolean searchMatrix2(int[][] matrix, int target) {
        
        if(matrix==null || matrix.length ==0 || matrix[0].length ==0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        
        return search(matrix,target, 0, m-1, 0, n-1);
        
        
    }
    
    private boolean search(int[][] matrix, int target, int startRow, int endRow, int startCol, int endCol){
        if(startRow>endRow || startCol > endCol) return false;
        
        int midRow = startRow + (endRow-startRow)/2;
        int midCol = startCol + (endCol-startCol)/2;
        
        if(target == matrix[midRow][midCol]) return true;
        else if(target>matrix[midRow][midCol]){
                return search(matrix,target, midRow+1, endRow, startCol, midCol) || 
                    search(matrix,target, startRow, midRow, midCol+1, endCol) || 
                    search(matrix,target, midRow+1, endRow, midCol+1, endCol);
                
        }else{
                return search(matrix,target, startRow, midRow-1, startCol, midCol-1) || 
                        search(matrix,target, midRow, endRow, startCol, midCol-1) ||
                        search(matrix,target, startRow, midRow-1, midCol, endCol);
                }
        
    }
}
