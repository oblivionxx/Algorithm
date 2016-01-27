import java.util.HashSet;

/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Array, Union Find
 */
public class LT128_Longest_Consecutive_Sequence {
	public int longestConsecutive(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        HashSet<Integer> set = new HashSet<>();
        
        //put all numbers into set.
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        
        int res = 1;
        
        for(int elm:nums){
            if(!set.contains(elm)) continue;
            int left= elm-1;
            int right = elm+1;
            int localMax = 1;
            
            //this two steps will definitely find for one consecutive sequence
            while(set.contains(left)){
                set.remove(left);
                localMax++;
                left--;
            }
            
            while(set.contains(right)){
                set.remove(right);
                localMax++;
                right++;
            }
            
            res = Math.max(res, localMax);
        }
        
        return res;
    }
}
