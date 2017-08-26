/*
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

Examples:
Input: [1,7,4,9,2,5]
Output: 6
The entire sequence is a wiggle sequence.

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
Follow up:
Can you do it in O(n) time?

DP, Greedy
 */
public class LT376_Wiggle_Subsequence {
    // O(n) Greedy. single number is also wiggle sequence. find max length
    public int wiggleMaxLength(int[] nums) {
	if (nums.length == 0 || nums.length == 1) {
	    return nums.length;
	}
	int k = 0;
	while (k < nums.length - 1 && nums[k] == nums[k + 1]) {
	    // Skips all the same numbers from series beginning eg 5, 5, 5, 1
	    k++;
	}
	if (k == nums.length - 1) {
	    return 1;
	}
	int result = 2;
	// This will track the result of result array. will have at least 1,2 or 2,1

	boolean smallReq = nums[k] < nums[k + 1]; // To check series starting pattern
	for (int i = k + 1; i < nums.length - 1; i++) {
	    if ((smallReq && nums[i + 1] < nums[i]) || (!smallReq && nums[i + 1] > nums[i])) {
		result++;
		smallReq = !smallReq; // Toggle the requirement from small to
				      // big number
	    }
	}
	// greedy. check possible next elm only by comparing neighboring numbers.
	// eg. 1,2,6,5: when have 6. will not add result. when add 5, actually
	// the sequence is 1 6 5. if output sequence. before result++, could update next[result] =
	// nums[i + 1];

	return result;
    }

    // DP O(n^2)
    // up[i] refers to the length of the longest wiggle subsequence obtained so far considering ith element as the last element of the wiggle subsequence and ending with a rising wiggle.
    // will update up if i<j and nums[i]<nums[j]
    // similar down[i]
    public int wiggleMaxLength2(int[] nums) {
	if (nums.length < 2)
	    return nums.length;
	int[] up = new int[nums.length];
	int[] down = new int[nums.length];
	for (int i = 1; i < nums.length; i++) {
	    for (int j = 0; j < i; j++) {
		if (nums[i] > nums[j]) {
		    up[i] = Math.max(up[i], down[j] + 1);
		} else if (nums[i] < nums[j]) {
		    down[i] = Math.max(down[i], up[j] + 1);
		}
	    }
	}
	return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }
}
