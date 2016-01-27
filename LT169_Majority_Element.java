/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

Divide and Conquer, Array, Bit Manipulation
 */
public class LT169_Majority_Element {
	public int majorityElement(int[] nums) {
        // 1. using sort, then O(n log n)
        // 2. moore vote then O(k) linear time
        // idea is the majority number appears more than the other minority numbers
        
        int result =0, count =0;
        for(int i =0;i<nums.length;i++){
            if(count==0){
                result = nums[i];
                count =1;  //initialize
            }else if(result==nums[i]){
                count++;
            }else{
                count--;
            }
        }
        
        return result;
    }
}
