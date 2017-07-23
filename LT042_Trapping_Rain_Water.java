/*
 Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 For example, 
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
/*
 * Array, Stack, Two Pointer
 */
public class LT042_Trapping_Rain_Water {
	//O(n)
	public int trap(int[] A) {
	    if (A.length < 3) return 0;
	    int ans = 0;
	    int l = 0, r = A.length - 1;

	    // find the left and right edge which can hold water
	    while (l < r && A[l] <= A[l + 1]) l++;
	    while (l < r && A[r] <= A[r - 1]) r--;

	    while (l < r) {
	        int left = A[l];
	        int right = A[r];
	        if (left <= right) {
	            // add volume until an edge larger than the left edge
	            while (l < r && left >= A[++l]) {
	                ans += left - A[l];
	            }
	        } else {
	            // add volume until an edge larger than the right volume
	            while (l < r && A[--r] <= right) {
	                ans += right - A[r];
	            }
	        }
	    }
	    return ans;
	}
	
	public int trap2(int[] height) {
        //O(3*n).  每个A[i]能trapped water的容量，取决于height[i]左右两边的高度（可延展）较小值与height[i]的差值，即volume[i] = [min(left[i], right[i]) - height[i]] * 1
		if(height==null || height.length==0) return 0;
		
		//left to right
		int[] left = new int[height.length];
		int max = height[0];
		for(int i=0;i<height.length;i++){
			left[i] = Math.max(max, height[i]);
			max = Math.max(max, left[i]);		//the same as Math.max(max, height[i])
		}
		//right to left
		int[] right = new int[height.length];
		max = height[height.length-1];
		for(int i=height.length-1;i>=0;i--){
			right[i] = Math.max(max, height[i]);
			max = Math.max(max, right[i]);		//the same as Math.max(max, height[i])
		}
		
		//sum up
		int sum = 0;
		for(int i=0;i<height.length;i++){
			sum += Math.min(left[i], right[i])-height[i];
		}
		
		return sum;
    }
}
