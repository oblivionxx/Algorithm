/*
 Follow up for "Remove Duplicates":
 What if duplicates are allowed at most twice?

 For example,
 Given sorted array nums = [1,1,1,2,2,3],

 Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 */
/*
 * Array, Two Pointer
 */
public class LT080_Remove_Duplicates_From_Sorted_Array_II {
	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		int index = 0;
		int count = 1;
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				count++;
				if (count >= 3)
					continue; // skip copy nums[i] to nums[index++] this step.
			} else
				count = 1;
			nums[index++] = nums[i];
		}

		return index;
	}
}
