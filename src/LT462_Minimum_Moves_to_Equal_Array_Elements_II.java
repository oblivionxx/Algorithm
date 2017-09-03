import java.util.Arrays;

/*
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

Math
 */
public class LT462_Minimum_Moves_to_Equal_Array_Elements_II {
    public int minMoves2(int[] nums) {
	// find the median. larger --, smaller++
	// 1. sort and get the diff to median O(nlgn)
	// Arrays.sort(nums);
	// int median = nums[nums.length >> 1];
	// return Arrays.stream(nums).map(i -> Math.abs(median - i)).sum();

	Arrays.sort(nums);
	int i = 0, j = nums.length - 1;
	int count = 0;
	while (i < j) {
	    count += nums[j] - nums[i];
	    i++;
	    j--;
	}
	return count;
    }

    // 2. optimize to O(n) used quick select to select the median element
    // https://discuss.leetcode.com/topic/68758/java-o-n-time-using-quickselect/2
    public int minMoves(int[] nums) {
	int n = nums.length;
	int median = findKth(nums, 0, n - 1, n / 2);
	int sum = 0;
	for (int i = 0; i < nums.length; i++)
	    sum += Math.abs(nums[i] - median);
	return sum;
    }

    public static int findKth(int[] nums, int start, int end, int k) {
	if (start > end)
	    return Integer.MAX_VALUE;
	int left = partition(nums, start, end); // find left from start to end.
	if (left == k)// Found kth smallest number
	    return nums[left];
	else if (left < k)// Check right part
	    return findKth(nums, left + 1, end, k); // restrict range
	else // Check left part
	    return findKth(nums, start, left - 1, k);
    }

    public static int partition(int[] nums, int start, int end) {
	int pivot = nums[end];// Take A[end] as the pivot,
	int left = start;
	for (int i = start; i < end; i++) {
	    if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
		swap(nums, left++, i);
	}
	swap(nums, left, end);// Finally, swap A[end] with A[left]
	return left;
    }

    private static void swap(int[] nums, int i, int j) {
	int t = nums[i];
	nums[i] = nums[j];
	nums[j] = t;
    }

}
