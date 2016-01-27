/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

Array, Two Pointer, Binary Search
 */
public class LT287_Find_the_Duplicate_Number {

	public int findDuplicate(int[] nums) {
	    /*
	    那我们也就只能考虑用二分搜索法了，我们在区别[1, n]中搜索，首先求出中点mid，然后遍历整个数组，统计所有小于等于mid的数的个数，
	    如果个数大于mid，重复值应在[1, mid-1]之间，
	    此时的low就是我们要求的重复值，
	    O(nlgn). binary O(lgn), each time search a<=mid. O(n)
	    */
        int low =1, high=nums.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            int cnt = 0;
            for(int a:nums){
                if(a<=mid) ++cnt;
            }
            
            if(cnt<=mid)
                low = mid+1;
            else
                high = mid-1;
        }
        
        return low;
    }
	
	//fast, slow pointer like linkedlist_cycle_ii
}
