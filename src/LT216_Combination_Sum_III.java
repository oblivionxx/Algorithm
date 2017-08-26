
/*
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
Ensure that numbers within the set are sorted in ascending order.

Example 1:
Input: k = 3, n = 7
Output:

[[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output:
[[1,2,6], [1,3,5], [2,3,4]]

Backtracking, Array
 */
import java.util.*;

public class LT216_Combination_Sum_III {
    public List<List<Integer>> combinationSum3(int k, int n) {
	List<List<Integer>> res = new ArrayList<List<Integer>>();
	if (k < 1 || n > 45)
	    return res;
	helper(k, n, 1, new ArrayList<Integer>(), res);
	return res;
    }

    private void helper(int k, int n, int index, List<Integer> elm, List<List<Integer>> res) {
	if (n < 0)
	    return;
	if (elm.size() == k && n == 0) {
	    res.add(new ArrayList<Integer>(elm));
	    return;
	}

	// backtracking
	for (int i = index; i <= 9; i++) {
	    elm.add(i);
	    helper(k, n - i, i + 1, elm, res);
	    elm.remove(elm.size() - 1);
	}
    }
}
