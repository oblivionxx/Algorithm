import java.util.TreeSet;

/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.

Binary Search
 */
public class LT220_Contains_Duplicate_III {
    // 1. TreeSet.O(Nlgk). similar to hashset, but the value in the treeSet or treeMap is sorted
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
	// If using a window of k, then each time compare the number if value at most t, O(nk), ETL
	if (k < 1 || t < 0)
	    return false;
	TreeSet<Long> set = new TreeSet<Long>();
	for (int i = 0; i < nums.length; i++) {
	    // set.ceiling(n) return the smallest number >=n, can be null
	    // set.floor(n) return the biggest number <n, can be null
	    long n = nums[i];
	    if (set.ceiling(n) != null && set.ceiling(n) - n <= t || set.floor(n) != null && n - set.floor(n) <= t)
		return true;
	    set.add(n);
	    if (i >= k)
		set.remove((long) nums[i - k]); // keep the number in the set is only within index difference k, so here keep the window size<=k
	}

	return false;
    }

    // 2.Tree O(lgn)??

    // 3.Bucket O(n)
    // https://leetcode.com/discuss/38206/ac-o-n-solution-in-java-using-buckets-with-explanation
}
