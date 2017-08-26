import java.util.*;

/*
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.

Hash Table
 */
public class LT525_Contiguous_Array {
    public int findMaxLength(int[] nums) {
	// 一个二进制的数组，找邻近的子数组使其0和1的个数相等。遇到1就加1，遇到0，就减1，这样如果某个子数组和为0，就说明0和1的个数相等
	// calculate presum. if we find preSum[i, j] == 0 then we know there are even number of -1 and 1 between index i and j
	for (int i = 0; i < nums.length; i++) {
	    if (nums[i] == 0)
		nums[i] = -1;
	}

	Map<Integer, Integer> sumToIndex = new HashMap<>();
	sumToIndex.put(0, -1);
	int sum = 0, max = 0;

	for (int i = 0; i < nums.length; i++) {
	    sum += nums[i];
	    if (sumToIndex.containsKey(sum)) {
		max = Math.max(max, i - sumToIndex.get(sum));
	    } else {
		sumToIndex.put(sum, i);
	    }
	}
	return max;
    }
}
