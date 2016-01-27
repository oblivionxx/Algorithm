/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
Hint:
How many majority elements could it possibly have? at most 2. at least one
Do you have a better hint? Suggest it!

Array
 */
import java.util.*;
public class LT229_Major_Element_II {
	public List<Integer> majorityElement(int[] nums) {
        //maximum two number
        List<Integer> rst = new ArrayList<Integer>();
        int result1=0,result2=0,count1=0,count2=0;
        for(int i=0;i<nums.length;i++){
            if(count1==0 && count2==0){
                result1=nums[i];
                count1=1;
            }else if(nums[i]==result1){
                count1++;
            }else if(nums[i]==result2){
                count2++;
            }else if(count1==0){
                result1=nums[i];
                count1++;
            }else if(count2==0){
                result2=nums[i];
                count2++;
            }
            else{	//meet the third number.
                    count1--;
                    count2--;
            }
        }
        
        count1=0;
        count2=0;
        //rst1 and rst2 are right number, but need recount the occurrence >n/3. occurence might be negative, or the rst number is not n/3 majority but just keep there. Remeber, at most 2, could be only 1.
        
        for(int n:nums){
            if(n==result1) count1++;
            else 
            if(n==result2) count2++;
        }
        if(count1>nums.length/3)
            rst.add(result1);
        if(count2>nums.length/3)
            rst.add(result2);
        
        return rst;
        
    }
}
