/*
 Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */
/*
 * Array, Backtracking
 */
import java.util.*;
public class LT090_Subsets_II {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length==0 || nums==null)
            return res;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];  
        res.add(new ArrayList<Integer>()); //add empty set
        helper(nums, 0, new ArrayList<Integer>(), res,visited);
        return res;
        
    }
    
    private void helper(int[] nums, int index, List<Integer> elm, List<List<Integer>> res,boolean[] visited){
        
        for(int i=index;i<nums.length;i++){
            if(i>0 && nums[i-1] == nums[i] && !visited[i-1])  continue;		//add for duplicates.
            
            if(!visited[i]){												//add visited for duplicates.
                elm.add(nums[i]);
                visited[i] = true;
                res.add(new ArrayList<Integer>(elm));
                helper(nums,i+1,elm,res,visited);
                elm.remove(elm.size()-1);
                visited[i] = false;
            }
        }
    }
}
