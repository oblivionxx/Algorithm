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
	//idea similar to 153.
	public int findMin2(int[] nums) {
	     int l = 0, r = nums.length-1;
	     while (l < r) {								//here without =.
	         int mid = (l + r) / 2;		
	         if (nums[mid] < nums[r]){					//right is in order. smallest is on the left half.
	             r = mid;
	         }else if (nums[mid] > nums[r]){			//right is not inorder. smallest is on right.
	             l = mid + 1;
	         }else {  
	             r--;  									//nums[mid]=nums[r] no idea, but we can eliminate nums[r];
	         }
	     }
	     return nums[l];
	}
	
	//use left<right-1. different to 153.
		public int findMin(int[] nums) {
			if(nums == null || nums.length==0)
	            return 0;
	        int l = 0;
	        int r = nums.length-1;
	        int min = nums[0];
	        while(l<r-1)
	        {
	            int m = (l+r)/2;
	            if(nums[l]<nums[m]){						//left is in order.
	                min = Math.min(nums[l],min);
	                l = m+1;
	            }else if(nums[l]>nums[m]){					//left is not inorder.
	                min = Math.min(nums[m],min);
	                r = m-1;
	            }else{										//left is all same numbers.
	                l++;
	            }
	        }
	        min = Math.min(nums[r],min);
	        min = Math.min(nums[l],min);
	        return min;
	    }
		
}
