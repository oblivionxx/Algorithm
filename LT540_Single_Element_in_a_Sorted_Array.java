/*
 * Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.

 */
public class LT540_Single_Element_in_a_Sorted_Array {
    public int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length-1;       

        while(low < high) {
            int mid = low + (high - low)/2;
            if(nums[mid] != nums[mid+1] && nums[mid] != nums[mid-1])
                return nums[mid];
            else if(nums[mid] == nums[mid+1] && mid % 2 == 0)   //even index should follow by the same value
                low = mid+1;
            else if(nums[mid] == nums[mid-1] && mid % 2 == 1)   //odd index should be the same as the one before
                low = mid+1;
            else
                high = mid-1;
        }
        return nums[low];
    }
}
