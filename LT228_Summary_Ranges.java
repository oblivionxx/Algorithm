/*
Given a sorted integer array without duplicates, return the summary of its ranges.
For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

Array. 
Similar to missing ranges.
 */
import java.util.*;
public class LT228_Summary_Ranges {
	//O(n)
	public List<String> summaryRanges(int[] nums) {
		List<String> list=new ArrayList<>();

	    for(int i=0;i<nums.length;i++){
	        int a=nums[i];
	        while(i+1<nums.length&&(nums[i+1]-nums[i])==1){
	            i++;
	        }
	        if(a!=nums[i]){
	            list.add(a+"->"+nums[i]);
	        }else{
	            list.add(a+"");
	        }
	    }
	    return list;
	}
}
