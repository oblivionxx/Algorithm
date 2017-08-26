import java.util.*;

/*
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

DFS
 */
public class LT491_Increasing_Subsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
	// backtracking
	Set<List<Integer>> res = new HashSet<List<Integer>>(); // using set to avoid duplicate
	findSequence(res, new ArrayList<Integer>(), 0, nums);
	List<List<Integer>> result = new ArrayList<List<Integer>>(res);
	return result;
    }

    public void findSequence(Set<List<Integer>> res, List<Integer> each, int index, int[] nums) {
	if (each.size() >= 2) {
	    res.add(new ArrayList<>(each));
	}
	for (int i = index; i < nums.length; i++) {
	    if (each.size() == 0 || each.get(each.size() - 1) <= nums[i]) {
		each.add(nums[i]);
		findSequence(res, each, i + 1, nums);
		each.remove(each.size() - 1);
	    }
	}
    }
}
