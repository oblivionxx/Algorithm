import java.util.HashMap;

/*
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.

Array, HashTable
 */
public class LT219_Contains_Duplicate_II {
	public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i]))
                map.put(nums[i],i);
            else{
                if(i-map.get(nums[i])<=k)
                    return true;
                else
                    map.put(nums[i],i);		//update new index.
            }
                
        }
        
        return false;
    }
}
