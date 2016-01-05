/*
 Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 Do not allocate extra space for another array, you must do this in place with constant memory.
	For example,
	Given input array nums = [1,1,2],
	Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 */
/*
 * Array, Two Pointer
 */
public class LT026_Remove_Duplicates_From_Sorted_Array {
	public int removeDuplicates(int[] nums) {
		int len =0;
		for(int i=1;i<nums.length;i++){
			int tmp = nums[i-1];
			while(nums[i]==tmp){		//use while
				++i;
			}
			nums[++len] = nums[i];
		}
		
		return len+1;	//len is index. total len need+1
	}
}
