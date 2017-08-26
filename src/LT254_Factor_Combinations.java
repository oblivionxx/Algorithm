/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

Backtracking
 */
import java.util.*;
public class LT254_Factor_Combinations {
	public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if (n <=3)
    		return result;
     
    	dfs(n, 2, new ArrayList<Integer>(), result); // because it need to begin from 1
    	return result;
    }
         
    private void dfs(int n, int lower, List<Integer> cur, List<List<Integer>> result) {
    	if (n==1) {
    	    if (cur.size() > 1) 
                result.add(new ArrayList<Integer>(cur));
            return;
        }
        
        for (int i = lower; i <= n; ++i) {
            if (n % i == 0) {
                cur.add(i);
                dfs(n / i, i, cur, result);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
