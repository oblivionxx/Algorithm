/*
Rotate an array of n elements to the right by k steps.
For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

Hint:
Could you do it in-place with O(1) extra space?

Array.
 */
public class LT189_Rotate_Array {
    public void rotate(int[] nums, int k) {
	// Can we do this in O(1) space and in O(n) time? The following solution does.
	// Assuming we are given {1,2,3,4,5,6} and order 2. The basic idea is:
	// 1. Divide the array two parts: 1,2,3,4 and 5, 6
	// 2. Rotate first part: 4,3,2,1,5,6
	// 3. Rotate second part: 4,3,2,1,6,5
	// 4. Rotate the whole array: 5,6,1,2,3,4
	if (nums == null || k == 0)
	    return;
	k = k % nums.length;

	rotateSub(nums, 0, nums.length - k - 1);
	rotateSub(nums, nums.length - k, nums.length - 1);
	rotateSub(nums, 0, nums.length - 1);

    }

    private void rotateSub(int[] nums, int left, int right) {

	while (left < right) {
	    int tmp = nums[right];
	    nums[right] = nums[left];
	    nums[left] = tmp;
	    left++;
	    right--;
	}
    }

    // 2. using extra space. then arraycopy
    public void rotate2(int[] nums, int k) {
	k = k % nums.length;
	int[] tmp = new int[nums.length];

	for (int i = 0; i < k; i++) {
	    tmp[i] = nums[i + nums.length - k];
	}

	for (int i = k; i < nums.length; i++) {
	    tmp[i] = nums[i - k];
	}

	System.arraycopy(tmp, 0, nums, 0, nums.length);// The difference is that Arrays.copyOf does not only copy elements, it also creates a new array. System.arrayCopy copies into an existing array.
    }
}
