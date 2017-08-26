/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

Array, DP.
 */
public class LT152_Maximum_Product_SubArray {
	//need to consider *0, multiply two negative number...这里只维护一个局部最优并不能保证全局最优. 比较nums[i] 与nums[i]*max 是局部最优 当nums[i] 较大时，会开始新的subarray.
    //我们只需要在维护一个局部最大的同时，在维护一个局部最小，这样如果下一个元素遇到负数时，就有可能与这个最小相乘得到当前最大的乘积和
    
	public int maxProduct(int[] nums) {
		//local max = max(local min* nums[i], local max*nums[i]) to avoid - - = + 
        int localMax = nums[0], localMin = nums[0], globalMax = nums[0];
        
        for(int i=1;i<nums.length;i++){
            int temp = localMax;
            localMax = Math.max(Math.max(nums[i], nums[i]*temp), nums[i]*localMin);
            localMin = Math.min(Math.min(nums[i], nums[i]*temp), nums[i]*localMin);
            globalMax = Math.max(localMax, globalMax);
        }
        
        return globalMax;
    }
}
