/*
 * Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.

Binary Search, Array
 */
public class LT644_Maximum_Average_Subarray_II {
    // nums[i]+nums[i+1]+...+nums[j])/(j-i+1)>x
    // =>nums[i]+nums[i+1]+...+nums[j]>x*(j-i+1)
    // =>(nums[i]-x)+(nums[i+1]-x)+...+(nums[j]-x)>0
    public boolean check(int[] nums, int k, double x) { // Check whether we can find a subarray whose average is bigger than x
	int n = nums.length;
	double[] a = new double[n];
	for (int i = 0; i < n; i++)
	    a[i] = nums[i] - x; // Transfer to a[i], find whether there is a subarray whose sum is bigger than 0

	double now = 0, last = 0;
	for (int i = 0; i < k; i++)
	    now += a[i];
	if (now >= 0)
	    return true;
	for (int i = k; i < n; i++) {
	    now += a[i];
	    last += a[i - k];
	    if (last < 0) {
		now -= last;
		last = 0;
	    }
	    if (now >= 0)
		return true;
	}
	return false;
    }

    public double findMaxAverage(int[] nums, int k) {
	double l = Integer.MIN_VALUE, r = Integer.MAX_VALUE;
	while (r - l > 0.000004) { // Binary search the answer
	    double mid = (l + r) / 2;
	    if (check(nums, k, mid))
		l = mid;
	    else
		r = mid;
	}
	return r;
    }

    // TLE. Reuse 643
    public double findMaxAverage2(int[] nums, int k) {
	double max = Double.NEGATIVE_INFINITY;
	for (int i = k; i <= nums.length; i++) {
	    double average = helper(nums, i);
	    max = Math.max(max, average);
	}

	return max;
    }

    public double helper(int[] nums, int k) {
	long sum = 0;
	for (int i = 0; i < k; i++)
	    sum += nums[i];
	long max = sum;

	for (int i = k; i < nums.length; i++) {
	    sum += nums[i] - nums[i - k];
	    max = Math.max(max, sum);
	}

	return max / 1.0 / k;
    }
}
