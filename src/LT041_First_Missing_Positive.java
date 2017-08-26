/*
 Given an unsorted integer array, find the first missing positive integer.
 For example,
 Given [1,2,0] return 3,
 and [3,4,-1,1] return 2.
 Your algorithm should run in O(n) time and uses constant space.
 */
/*
 * Array
 */
public class LT041_First_Missing_Positive {
    // O(n) using bucket sort.
    // O(nlgn) using quick sort.
    public int firstMissingPositive(int[] nums) {
	/*
	 * We must have nums[i]<=n We only have n numbers, and the answer must be in [1, n + 1]. 1. If n numbers are 1, 2, 3, ..., n, then the answer is n + 1. 2. use a number x (x <= 0 || x > n) to
	 * replace a number in 1, 2, 3, ..., n. There must be a number less than n+1. 不能按照A[i] = i来存，因为题目要求寻找正数，如果A[0]用来存储0的话，会影响数据处理。比如当A = {1}的时候，如果A[0] = 0的话，根本没地方存1的值了。所以正确的存储方式应该是A[i]= i+1.
	 */

	// nums[i] should be in position nums[i]-1. swap nums[i],
	// nums[nums[i]-1].
	// swap nums. so that the array is starting from 1.2.3...
	// 3.4.-1.1->-1.4.3.1.->-1.1.3,4 ->1.-1.3.4->-1 is not in the correct
	// position

	for (int i = 0; i < nums.length; i++) {
	    if (i != nums[i] - 1) {
		if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == nums[nums[i] - 1])
		    continue;
		else {
		    int temp = nums[i]; // swap nums[i], nums[nums[i]-1]
		    nums[i] = nums[temp - 1];
		    nums[temp - 1] = temp;
		    i--; // recheck the cur point. swap until it's correct or
			 // out of range
		}
	    }
	}
	// Find first location where the index doesn't match the value
	for (int i = 0; i < nums.length; i++) {
	    if (i != nums[i] - 1)
		return i + 1;
	}

	return nums.length + 1;
    }
}
