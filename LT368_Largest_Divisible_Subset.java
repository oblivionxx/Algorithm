import java.util.*;

/*
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

nums: [1,2,3]

Result: [1,2] (of course, [1,3] will also be ok)
Example 2:

nums: [1,2,4,8]

Result: [1,2,4,8]

DP, Math
 */
public class LT368_Largest_Divisible_Subset {
  //if i%j. then dp[i] = max(dp[i],dp[j]+1)
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums==null || nums.length==0) return new ArrayList<>();
        int[] dp = new int[nums.length+1];
        Arrays.sort(nums);
        int max = 1, index =0;
        for(int i=0;i<nums.length;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(nums[i]%nums[j]==0)
                    dp[i] = Math.max(dp[i],dp[j]+1);
            }
            if(dp[i]>=max){
                max = dp[i];
                index = i;          //keep the max index
            }
        }
        
        List<Integer> res = new ArrayList<>();
        int temp = nums[index];
        int curDp = dp[index];
        for (int i = index; i >= 0; i--){
            if (temp % nums[i] == 0 && dp[i] == curDp){
                res.add(0,nums[i]);
                temp = nums[i];         //?
                curDp--;
            }
        }
        return res;
    }
}
