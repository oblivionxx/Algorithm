import java.util.*;

/*
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

Array, Map
 */
public class LT560_Subarray_Sum_Equals_K {
    //Solution 1. Brute force. We just need two loops (i, j) and test if SUM[i, j] = k. Time complexity O(n^2), Space complexity O(1). I bet this solution will TLE.
    //Solution 2. PreSum. get SUM[i, j] = SUM[0,j]-SUM[0,i].  save number of all seen PreSum to a HashMap. Time complexity O(n), Space complexity O(n).

    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();     //store <preSum1, count>
        preSum.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];                                 
            if (preSum.containsKey(sum - k)) {              //curSum, exisit preSum1 in the map such that curSum-preSum1 = k
                result += preSum.get(sum - k);              //result + count. how many such kind of situation
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);   
        }
        
        return result;
    }
}
