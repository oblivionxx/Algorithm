
/*
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
Try to solve it in linear time/space.
Return 0 if the array contains less than 2 elements.
You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

Sort
 */
import java.util.*;

public class LT164_Maximum_Gap {
    // bucket sort.
    // Bucket sort --> N个元素A~B。那么最大差值不会小于bucket len = ceiling[(B - A) / (N - 1)].
    // so find in two buckets. bucket size = (B-A)/len+1. so store max and min into the bucket. int prev = max[0];. loop i=1~bucketsize, update(min[i]-pre). then change pre=max(i)
    public int maximumGap(int[] num) {
	if (num == null || num.length < 2)
	    return 0;
	int len = num.length;
	int min = 0;
	int max = 0;
	for (int i = 0; i < len; i++) {
	    min = Math.min(min, num[i]);
	    max = Math.max(max, num[i]);
	}
	int bucketLen = (max - min) / (len - 1) + ((max - min) % (len - 1) > 0 ? 1 : 0);
	int bucketCount = (max - min) / bucketLen + 1;
	int[] minBuckets = new int[bucketCount];
	int[] maxBuckets = new int[bucketCount];

	Arrays.fill(minBuckets, Integer.MAX_VALUE);
	Arrays.fill(maxBuckets, Integer.MIN_VALUE);

	for (int i = 0; i < len; i++) {
	    int b = (num[i] - min) / bucketLen;
	    minBuckets[b] = Math.min(minBuckets[b], num[i]);
	    maxBuckets[b] = Math.max(maxBuckets[b], num[i]);
	}
	int maxGap = 0;
	int prev = maxBuckets[0];
	for (int i = 1; i < bucketCount; i++) {
	    if (minBuckets[i] == Integer.MAX_VALUE && maxBuckets[i] == Integer.MIN_VALUE)
		continue;
	    maxGap = Math.max(maxGap, minBuckets[i] - prev);
	    prev = maxBuckets[i];
	}

	return maxGap;
    }

    public int maximumGap2(int[] nums) {
	// write your code here
	// Bucket sort
	int max = Integer.MIN_VALUE;
	int min = Integer.MAX_VALUE;
	int n = nums.length;
	for (int num : nums) {
	    max = Math.max(max, num);
	    min = Math.min(min, num);
	}

	if (max == min) {
	    return 0;
	}

	int bucketSize = (max - min) / n + 1;
	int bucketNum = (max - min) / bucketSize + 1;
	int[] bucketMin = new int[bucketNum];
	int[] bucketMax = new int[bucketNum];
	HashSet<Integer> set = new HashSet<Integer>();
	for (int num : nums) {
	    int index = (num - min) / bucketSize;
	    if (!set.contains(index)) {
		bucketMin[index] = num;
		bucketMax[index] = num;
		set.add(index);
		continue;
	    }

	    if (bucketMin[index] > num) {
		bucketMin[index] = num;
	    }
	    if (bucketMax[index] < num) {
		bucketMax[index] = num;
	    }
	}

	// 一定会有数落在0bucket里，因为index = (num - min) / bucketSize，当num = min时就落在0桶里，所以第一个非空的桶一定为0
	int pre = 0;
	int res = 0;
	// 寻找下一个非空的桶，空的桶就跳过
	for (int i = 1; i < bucketNum; i++) {
	    if (!set.contains(i)) {
		continue;
	    }
	    res = Math.max(res, bucketMin[i] - bucketMax[pre]);
	    pre = i;
	}

	return res;
    }
}
