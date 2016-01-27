/*
 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 For example,
 Consider the following matrix:

	[
	  [1,   3,  5,  7],
	  [10, 11, 16, 20],
	  [23, 30, 34, 50]
	]
 Given target = 3, return true.
 */
/*
 * Array, Binary Search
 */
public class LT074_Search_A_2D_Matrix {
	//O(log(m)+log(n))
	public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0) return false;
        int m = matrix.length, n = matrix[0].length;
        
        //search 1st column.
        int low = 0, high = m-1;
        while(low<=high){
        	int mid = (low+high)/2;
        	if(matrix[mid][0]==target) return true;
        	else if(matrix[mid][0]>target)
        		high = mid-1;
        	else
	        	low = mid+1;
        }
        
        int row = high;		//don't forget. high is always less than target.
        if(row<0) return false;
        
        //search for this row
        low = 0; 
        high =n-1;
        while(low<=high){
        	int mid = (low+high)/2;
        	if(matrix[row][mid]==target) return true;
        	else if(matrix[row][mid]>target)
        		high = mid-1;
        	else
	        	low = mid+1;
        }
        
        return false;
    }
	
	public static void main(String[] args){
		int[][] matrix = new int[][]{{1,2,3,4},{10,11,12,14},{21,22,23,43}};
		searchMatrix(matrix, 12);
	}
}
