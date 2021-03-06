/*
 Given an array of non-negative integers, you are initially positioned at the first index of the array.
 Each element in the array represents your maximum jump length at that position.

 Determine if you are able to reach the last index.

 For example:
 A = [2,3,1,1,4], return true.

 A = [3,2,1,0,4], return false.
 */
/*
 * Array, Greedy
 */
public class LT055_Jump_Game {
    public boolean canJump(int[] nums) {
	int max = 0;
	for (int i = 0; i < nums.length; ++i) {
	    if (i > max)
		return false;
	    max = Math.max(max, i + nums[i]);
	}
	return true;
    }

    public boolean canJump2(int[] nums) {
	int max = 0;

	for (int i = 0; i < nums.length; i++) {
	    if (i <= max)
		max = Math.max(max, i + nums[i]);
	}
	return max >= nums.length - 1;
    }
}
