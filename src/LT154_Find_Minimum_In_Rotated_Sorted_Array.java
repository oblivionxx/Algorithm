/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
The array may contain duplicates.

Array, Binary Search
 */
public class LT154_Find_Minimum_In_Rotated_Sorted_Array {
	public int findMin(int[] nums) {
        //allow duplicates
        int left = 0, right =nums.length-1;
        while(left<right){
            int mid = (left+right)/2;       //find the part which is not sorted
            if(nums[mid]>nums[right]){
                left = mid+1;
            }else if(nums[mid]<nums[left]){
                right = mid;
            }else{
                //nums[mid]=nums[r] no idea, but we can eliminate nums[r];
                right--;        //result using left, so better to eliminate right side
            }
        }
        
        return nums[left];
    }
}
