import java.util.Arrays;

/*
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?

Two Pointer, Array
 */
public class LT259_3Sum_Smaller {
    public int threeSumSmaller(int[] nums, int target) {
	int count = 0;
	Arrays.sort(nums);
	for (int i = 0; i < nums.length - 2; i++) {
	    int left = i + 1, right = nums.length - 1;
	    while (left < right) {
		int sum = nums[i] + nums[left] + nums[right];
		if (sum >= target)
		    right--;
		else {
		    count += right - left; // [-2, 0, 1, 3] when -2,0,3 works. can be sure that -2,0,1 is also working. if just move left, then count less
		    left++;
		}
	    }
	}

	return count;
    }
}
