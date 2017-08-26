import java.util.Arrays;

/*
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

Array
 */
public class LT581_Shortest_Unsorted_Continuous_Subarray {
    public int findUnsortedSubarray(int[] nums) {
	// left of subarray < min of subarray, right > max of subarray. must in same order as sorted
	// using sort
	int n = nums.length;
	int[] temp = new int[n];
	for (int i = 0; i < n; i++)
	    temp[i] = nums[i];
	Arrays.sort(temp);

	int start = 0;
	while (start < n && nums[start] == temp[start])
	    start++;

	int end = n - 1;
	while (end > start && nums[end] == temp[end])
	    end--;

	return end - start + 1;
    }
}
