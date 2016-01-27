/*
 Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	For example,
	If n = 4 and k = 2, a solution is:
	
	[
	  [2,4],
	  [3,4],
	  [2,3],
	  [1,2],
	  [1,3],
	  [1,4],
	]
	
 Backtracking
 */
import java.util.*;
public class LT077_Combinations {
	public List<List<Integer>> combine(int n, int k) {
	        //[1,2] is ok, but [2,1] is not
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        if(n<1 || k<1)
	            return res;
	        helper(n,k,1,new ArrayList<Integer>(),res);
	        return res;
	    }
	    
    private void helper(int n, int k, int index, ArrayList<Integer> elm, List<List<Integer>> res) 
    {
        if(elm.size()==k)
         {  res.add(new ArrayList<Integer>(elm));
            return;
         }
         
        for(int i=index;i<=n;i++)
        {
            elm.add(i);
            helper(n,k,i+1,elm,res); //not index+1. important. otherwise would return [4,2] like this
            elm.remove(elm.size()-1);
            
        }
    }
    
}
