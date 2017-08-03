import java.util.Arrays;

/*
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
Input: [1,2,3]
Output: 6
Example 2:
Input: [1,2,3,4]
Output: 24
Note:
The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

Array, Math
 */
public class LT628_Maximum_Product_of_Three_Numbers {
    // if all negative, or all positive, or only one negative number. In this case, take 3 rightmost number  
    // if two or more negative numbers, compare rightmost 3 nums or 2 left most * 1 rightmost
    public int maximumProduct(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int a = nums[len-1]*nums[len-2]*nums[len-3];
        int b = nums[len-1]*nums[0]*nums[1];            //if negative
        return a>b?a:b;
    }
}
