/*
 Given a collection of distinct numbers, return all possible permutations.
 For example,
 [1,2,3] have the following permutations:
 [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */
/*
 * Backtracking
 */
import java.util.*;
public class LT046_Permutation {
	public List<List<Integer>> permute(int[] nums) {
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(nums==null || nums.length==0) return res;
		boolean[] visited = new boolean[nums.length];
		helper(nums, visited, res, new ArrayList<Integer>());
		
		return res;
    }
	
	public void helper(int[] nums, boolean[] visited, List<List<Integer>> res, ArrayList<Integer> elm){
		if(elm.size()==nums.length){
			res.add(new ArrayList<Integer>(elm));
			return;
		}
		
		for(int i=0;i<nums.length;i++){
			if(!visited[i]){
				visited[i] = true;
				elm.add(nums[i]);
				helper(nums, visited, res, elm);
				elm.remove(elm.size()-1);
				visited[i] = false;
			}
		}
	}
}
