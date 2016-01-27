/*
 Given a set of distinct integers, nums, return all possible subsets.
	Note:
	Elements in a subset must be in non-descending order.
	The solution set must not contain duplicate subsets.
	For example,
	If nums = [1,2,3], a solution is:
	
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3],
	  [1,2],
	  []
	]
	
 Backtracking, Array, BitManipulation
 */
import java.util.*;
public class LT078_Subsets {
	//normal backtracking DFS way. Only need to consider empty set.
	public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length==0 || nums==null)
            return res;
        Arrays.sort(nums);
        res.add(new ArrayList<Integer>()); //add empty set. Important.
        helper(nums, 0, new ArrayList<Integer>(), res);
        return res;
        
    }
    
    private void helper(int[] nums, int index, List<Integer> elm, List<List<Integer>> res)
    {
    
        
        for(int i=index;i<nums.length;i++)
        {
            elm.add(nums[i]);
            res.add(new ArrayList<Integer>(elm));
            helper(nums,i+1,elm,res);
            elm.remove(elm.size()-1);
        }
    }
}
