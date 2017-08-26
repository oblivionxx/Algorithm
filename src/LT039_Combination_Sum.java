
/*
 Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 The same repeated number may be chosen from C unlimited number of times.
	Note:
	All numbers (including target) will be positive integers.
	Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
	The solution set must not contain duplicate combinations.
	For example, given candidate set 2,3,6,7 and target 7, 
	A solution set is: 
	[7] 
	[2, 2, 3] 
 */
/*
 * Array, Backtracking
 */
import java.util.*;

public class LT039_Combination_Sum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	if (candidates == null || candidates.length == 0)
	    return res;
	Arrays.sort(candidates);
	helper(res, new ArrayList<Integer>(), candidates, target, 0);
	return res;
    }

    public void helper(List<List<Integer>> res, ArrayList<Integer> elm, int[] candidates, int target, int index) {
	if (target < 0)
	    return;

	if (target == 0) {
	    res.add(new ArrayList<Integer>(elm)); // elm will be used for
						  // backtracking....so
						  // re-instantiate
	    return;
	}

	for (int i = index; i < candidates.length; i++) {
	    // if(i>index && candidates[i]==candidates[i-1]) without this, we
	    // will have duplicate results. but leetcode dont check this.
	    // continue;
	    elm.add(candidates[i]);
	    helper(res, elm, candidates, target - candidates[i], i); // each
								     // number
								     // can
								     // be
								     // used
								     // infinite
								     // times
	    elm.remove(elm.size() - 1);
	}
    }
}
