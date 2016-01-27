import java.util.Stack;

/*
 Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 Array, HashTable, Stack, DP
 */
public class LT085_Maximum_Rectangle {
	//1. by applying Q84.
	public int maximalRectangle(char[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;    
        
        int[] height = new int[n];	//对每一列构造数组
        for(int i=0;i<m;i++){		//loop each row.
            for(int j=0;j<n;j++){
                if(matrix[i][j] == '0')//如果遇见0，这一列的高度就为0了
                    height[j] = 0;
                else
                    height[j] += 1;
                }
            max = Math.max(largestRectangleArea(height),max);
            }
            
        return max;
    }
	
	public int largestRectangleArea(int[] height) {
		int area = 0;
		Stack<Integer> stack = new java.util.Stack<Integer>();			//stack store increasing value's INDEX. 
		for (int i = 0; i < height.length; i++) {
			if (stack.empty() || height[stack.peek()] < height[i]) {
				stack.push(i);
			}else {														//if next value < stack.peek.
				int start = stack.pop();
				int width = stack.empty() ? i : i - stack.peek() - 1;	
				area = Math.max(area, height[start] * width);			//calculate the max area not including i.
				i--;													//re-compare height[i] and the new stack.peek's height
			}
		}
       
		while (!stack.empty()) {
			int start = stack.pop();
			int width = stack.empty() ? height.length : height.length - stack.peek() - 1;   //ex. 1,2,3,4,3,2. so this calculate from start to end. 
			area = Math.max(area, height[start] * width);      
        }
		
		return area;
	}
	
	//2.DP O(n^3). n rows. in each row, n columns, in each columns. compare n times of left and right. so worst case is n^3.
	/* Open matrix from top to the bottom line by line. Let the maximal rectangle area at row i and column j be computed by [right(i,j) - left(i,j)]*height(i,j). 
	 * left(i,j) = max(left(i-1,j), curleft), curleft can be determined from the current row
	 * right(i,j) = min(right(i-1,j), curright), curright can be determined from the current row
	 * height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
	 * height(i,j) = 0, 				if matrix[i][j]=='0';
	 */
	public int maximalRectangle2(char[][] matrix) {
		if(matrix==null || matrix.length==0 || matrix[0].length==0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        //dp saved for current row. when loop for next row. used as previous row, just compare with current value and update.
        int[] left = new int[n];
        int[] right = new int[n];
        for(int i = 0; i < n; i++){
        	right[i] = n;			//be default, right array is n.
        }
        int[] height = new int[n];
        
        
        for(int i=0;i<m;i++){
        	int cur_left = 0, cur_right = n;
        	//write separately
        	//compute height
        	for(int j = 0; j < n; j++) { 
                if(matrix[i][j] == '1')  		
                	height[j]++;
                else
                	height[j] = 0;
        	}
        	
        	//compute left
        	for(int j = 0; j < n; j++) { 
                if(matrix[i][j] == '1') 
                	left[j] = Math.max(left[j], cur_left);
                else{
                	left[j] = 0;		//can ensure left[j] is continuous 1s on the left.
                	cur_left = j+1;			
                }
        	}
        	
        	//compute right
        	for(int j = n-1; j >=0; j--) { 
                if(matrix[i][j] == '1') 
                	right[j] = Math.min(right[j], cur_right);
                else{
                	right[j] = n;		//set default. since later will compute min.
                	cur_right = j;			
                }
        	}
        	
        	 for(int j=0; j<n; j++)
                 max = Math.max(max,(right[j]-left[j])*height[j]);
        }
        return max;
	}
        
        
	
}
