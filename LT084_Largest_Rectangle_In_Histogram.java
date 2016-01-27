import java.util.Stack;

/*
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.
 */
/*
 * Array, Stack
 */
public class LT084_Largest_Rectangle_In_Histogram {
	//trick using stack
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
}
