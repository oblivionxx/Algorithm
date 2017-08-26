/*
 Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 The replacement must be in-place, do not allocate extra memory.
 
 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1
 */
/*
 * Array
 */

public class LT031_Next_Permutation {
    public void nextPermutation(int[] nums) {
	// 1,1,5,2. find i->5. j->2. switch(i-1, j) sort(i,end)
	// Worst case O(3*n)
	if (nums.length < 2)
	    return;

	int i = nums.length - 1;
	while (i > 0) {
	    if (nums[i - 1] < nums[i])
		break;
	    i--;
	}

	if (i == 0) {
	    reverseSort(nums, 0, nums.length - 1);
	    return;
	} else {
	    int val = nums[i - 1];
	    int j = nums.length - 1;
	    while (j >= i) {
		if (nums[j] > val)
		    break;
		j--;
	    }

	    swap(nums, j, i - 1);
	    reverseSort(nums, i, nums.length - 1);
	    return;
	}
    }

    public void swap(int[] num, int i, int j) {
	int temp = 0;
	temp = num[i];
	num[i] = num[j];
	num[j] = temp;
    }

    public void reverseSort(int[] num, int start, int end) {
	if (start > end)
	    return;
	for (int i = start; i <= (end + start) / 2; i++)
	    swap(num, i, start + end - i);
    }

}
