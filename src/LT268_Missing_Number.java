/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

BitManipulation, Array, Math

 */
public class LT268_Missing_Number {
    // 1. XOR the array elements with 1~n
    public int missingNumber(int[] nums) {
	int res = 0;
	for (int i = 0; i < nums.length; i++) {
	    res ^= (i + 1) ^ nums[i];
	}

	return res;
    }

    // 2. Sum up
    public int missingNumber2(int[] nums) {
	int len = nums.length;

	int sum = 0;
	for (int i = 0; i < len; i++) {
	    sum += nums[i];
	}

	return (len + 1) * len / 2 - sum;

    }
}
