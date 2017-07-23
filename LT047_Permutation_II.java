import java.util.*;

/*
 Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 For example,
 [1,1,2] have the following unique permutations:
 [1,1,2], [1,2,1], and [2,1,1].
 */
/*
 * Backtracking
 */
public class LT047_Permutation_II {
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(nums==null || nums.length==0) return res;
		boolean[] visited = new boolean[nums.length];
		Arrays.sort(nums);		//added for duplicates 
		helper(nums, visited, res, new ArrayList<Integer>());
		
		return res;
    }
	
	public void helper(int[] nums, boolean[] visited, List<List<Integer>> res, ArrayList<Integer> elm){
		if(elm.size()==nums.length){
			res.add(new ArrayList<Integer>(elm));
			return;
		}
		
		for(int i=0;i<nums.length;i++){
			if(i>0 && nums[i-1] == nums[i] && !visited[i-1])  continue;		//added for duplicates 
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
