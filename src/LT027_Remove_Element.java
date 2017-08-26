/*
 Given an array and a value, remove all instances of that value in place and return the new length.
 The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
/*
 * Array, Two Pointer
 */
public class LT027_Remove_Element {
    public int removeElement(int[] nums, int val) {
	int len = 0;
	for (int i = 0; i < nums.length; i++) {
	    if (nums[i] != val) {
		nums[len++] = nums[i];
	    }
	}
	return len; // use len++, so no need to len+1
    }
}
