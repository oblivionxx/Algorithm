import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 Each number in C may only be used once in the combination.
	Note:
	All numbers (including target) will be positive integers.
	Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
	The solution set must not contain duplicate combinations.
	For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
	A solution set is: 
	[1, 7] 
	[1, 2, 5] 
	[2, 6] 
	[1, 1, 6] 
 */
public class LT040_Combination_Sum_II {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
	    if (i > index && candidates[i] == candidates[i - 1]) // avoid
								 // duplicate
								 // in the
								 // result
								 // set. not
								 // zero, is
								 // index.
		continue;
	    elm.add(candidates[i]);
	    helper(res, elm, candidates, target - candidates[i], i + 1); // using
									 // i+1.
	    elm.remove(elm.size() - 1);
	}
    }

    // using visited array. but prefer the above solution.
    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	if (candidates == null || candidates.length == 0)
	    return res;

	Arrays.sort(candidates);
	boolean[] visited = new boolean[candidates.length];
	helper(candidates, 0, target, new ArrayList<Integer>(), res, visited);
	return res;
    }

    private void helper(int[] candidates, int idx, int target, ArrayList<Integer> item, List<List<Integer>> res,
	    boolean[] visited) {
	if (target < 0)
	    return;
	if (target == 0) {
	    res.add(new ArrayList<Integer>(item));
	    return;
	}

	for (int i = idx; i < candidates.length; i++) {
	    if (!visited[i]) {
		if (i > 0 && candidates[i] == candidates[i - 1] && visited[i - 1] == false)
		    continue;
		item.add(candidates[i]);
		visited[i] = true;
		helper(candidates, i, target - candidates[i], item, res, visited); // here
										   // use
										   // i,
										   // not
										   // i+1.
		visited[i] = false;
		item.remove(item.size() - 1);
	    }
	}

    }

}
