/*
 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 Note:
 Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},
    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
 */

/*
 * Array, Two Pointer
 */
import java.util.*;

public class LT015_3Sum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		// Triplet in non-descending order, so Sort first
		// Two place to avoid duplicate triplet.
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || (i > 0 && nums[i] > nums[i - 1])) {
				// no duplicate
				int target = -nums[i];
				int left = i + 1;
				int right = nums.length - 1;
				while (left < right) {
					if (nums[left] + nums[right] == target) {
						List<Integer> elm = new ArrayList<Integer>();
						elm.add(nums[i]);
						elm.add(nums[left]);
						elm.add(nums[right]);
						res.add(elm);

						left++;
						right--;

						// no duplicate
						while (left < right && nums[right] == nums[right + 1])
							right--;
						while (left < right && nums[left] == nums[left - 1])
							left++;

					} else if (nums[left] + nums[right] > target)
						right--;
					else
						left++;
				}
			}
		}

		return res;
	}
}
