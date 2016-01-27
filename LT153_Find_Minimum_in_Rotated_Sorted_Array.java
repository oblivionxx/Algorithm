/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.

Array, Binary Search
 */
public class LT153_Find_Minimum_in_Rotated_Sorted_Array {
	public int findMin(int[] nums) {
		if(nums==null || nums.length==0) return 0;
		int left =0, right= nums.length-1;
		while(left<=right){
			int mid = (left+right)/2;
			if(nums[mid]<nums[left]){	//left is not ordered. min is in left
				right = mid;
			}else if(nums[mid]>nums[right]){
				left =  mid+1;
			}else
				break;
		}
		
		return nums[left];
    }
}
