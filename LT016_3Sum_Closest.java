import java.util.Arrays;

/*
 Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
    For example, given array S = {-1 2 1 -4}, and target = 1.
    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

/*
 * Array, Two Pointer
 */
public class LT016_3Sum_Closest {
    public int threeSumClosest(int[] nums, int target) {
    	Arrays.sort(nums);
        int globalMin = Integer.MAX_VALUE;      //used to compare
        int res = 0;                            //save res if find min
        for(int i=0;i<nums.length;i++){
            int left= i+1, right=nums.length-1;
            while(left<right){
                int sum = nums[i]+nums[left]+nums[right];
                int diff = Math.abs(sum-target);
                if(sum==target) return sum;
                else if(sum<target) left++;
                else right--;
                
                if(diff<globalMin){
                    globalMin = diff;
                    res = sum;
                }
            }
        } 
        return res;
    }
}
