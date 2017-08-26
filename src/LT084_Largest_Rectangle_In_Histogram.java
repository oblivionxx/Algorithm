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
	// trick using stack
	public int largestRectangleArea(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);              //stack save asc bars
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));     //i - 1 - s.peek() is the width. calculate stack peek's index to cur position.
                i--;                                                                                //if 1,3,4,2 just compare 4,2 bar. pop. then compare 3,-,2 bar. 4 is removed. then width++
            }
        }
        return maxArea;
    }
}
