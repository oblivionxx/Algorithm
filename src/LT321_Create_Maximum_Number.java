/*
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]

DP, Greedy
 */
public class LT321_Create_Maximum_Number {
    // In short we can first solve 2 simpler problem
    // Create the maximum number of one array with x numbers
    // Create the maximum number of two array using all of their digits, k=m+n
    // The algorithm is O((m+n)^3) in the worst case. It runs in 22 ms.
    // http://blog.csdn.net/u010025211/article/details/50527279
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
	// Write your code here
	int m = nums1.length, n = nums2.length;
	int[] res = new int[k];
	for (int i = Math.max(0, k - n); i <= k && i <= m; i++) {
	    int[] candidate = mergeArray(maxArray(nums1, i), maxArray(nums2, k - i), k);
	    if (greater(candidate, 0, res, 0))
		res = candidate;
	}

	return res;
    }

    // find biggest number using k integers in nums
    public int[] maxArray(int[] nums, int k) {
	int[] res = new int[k];
	for (int i = 0, j = 0; i < nums.length; i++) {
	    while (nums.length - i + j > k && j > 0 && res[j - 1] < nums[i])
		j--;
	    if (j < k)
		res[j++] = nums[i];
	}
	return res;
    }

    public int[] mergeArray(int[] nums1, int[] nums2, int k) {
	int[] res = new int[k];
	for (int i = 0, j = 0, r = 0; r < k; r++) {
	    res[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
	}

	return res;
    }

    public boolean greater(int[] nums1, int i, int[] nums2, int j) {
	while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
	    i++;
	    j++;
	}
	return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
}
