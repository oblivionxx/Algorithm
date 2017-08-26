/*
Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
Solve it without division and in O(n).
For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)

Array
 */
public class LT238_Product_Of_Array_Except_Self {
    // 1. extra space.
    public int[] productExceptSelf(int[] nums) {
	int len = nums.length;
	int[] output = new int[len];
	if (nums.length == 0 || nums == null)
	    return output;

	int[] t1 = new int[len];
	int[] t2 = new int[len];

	t1[0] = 1; // store 1. IMPORTANT
	t2[len - 1] = 1;

	for (int i = 0; i < len - 1; i++) {
	    t1[i + 1] = nums[i] * t1[i]; // t1[i] 存的是nums中i位置以左的数的乘积
	}

	for (int j = len - 1; j > 0; j--) {
	    t2[j - 1] = nums[j] * t2[j]; // 倒过来 //t2[i] 存的是nums中i位置以右的数的乘积
	}

	for (int i = 0; i < len; i++)
	    output[i] = t2[i] * t1[i]; // 相乘，得到nums中i位置左右的数的乘积

	return output;
    }

    // 2. constant space.
    public int[] productExceptSelf2(int[] nums) {
	// use one output array store right[]. the left information is kept in int left.
	int[] output = new int[nums.length];

	// fill output[i] with the right product of position i
	output[nums.length - 1] = 1;
	for (int i = nums.length - 2; i >= 0; i--) {
	    output[i] = output[i + 1] * nums[i + 1];
	}

	int left = 1;
	for (int j = 0; j < nums.length; j++) {
	    output[j] *= left;
	    left *= nums[j]; // left is the product left to position i
	}

	return output;
    }
}
