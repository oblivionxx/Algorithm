/*
 Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 */
/*
 * Array, Two Pointer
 */
public class LT011_Container_With_Most_Water {
	public int maxArea(int[] height) {
		// BF. find all the index selection = c(2,n). O(n^2)
		// lines don't take space.Two pointer from two side to center O(n)
		int left = 0, right = height.length - 1;
		int max = 0;
		while (left < right) {
			max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
			if (height[left] > height[right])
				right--;
			else
				left++;
		}
		return max;
	}
}
