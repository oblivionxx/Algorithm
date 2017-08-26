/*
 Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note:
 You are not suppose to use the library's sort function for this problem.

	Follow up:
	A rather straight forward solution is a two-pass algorithm using counting sort.
	First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
	
	Could you come up with an one-pass algorithm using only constant space?
 */
/*
 * Array, Two Pointer, Sort
 */
public class LT075_Sort_Colors {
	public void sortColors1(int[] nums) {
		// 1. counting sort.3-pass. if not require in-place, only need 2-pass,
		// just return B
		int n = nums.length;
		int[] B = new int[n];
		int[] C = new int[3];
		for (int i : nums) {
			C[i]++;
		}

		for (int i = 1; i < 3; i++) {
			C[i] += C[i - 1];
		}

		for (int i = n - 1; i >= 0; i--) {
			int a = nums[i];
			B[C[a] - 1] = a;
			C[a]--;
		}

		for (int i = 0; i < n; i++) {
			nums[i] = B[i];
		}
	}

	public void sortColors2(int[] nums) {
		// 2. swap in place.1-pass. swap 0 to front, 2 to end. using two pointer
		// to keep the 0s' end and 2s' head.
		int zero = 0, two = nums.length - 1;
		int i = 0; // index
		while (i <= two) {
			if (nums[i] == 0 && i != zero) {
				swap(nums, zero, i);
				zero++;
			} else if (nums[i] == 2 && i != two) {
				swap(nums, two, i);
				two--;
			} else
				i++;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
