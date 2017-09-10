import java.util.Arrays;

import common.Interval;

/*
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Greedy
 */
public class LT435_Non_overlapping_Intervals {
    // https://en.wikipedia.org/wiki/Interval_scheduling#Interval_Scheduling_Maximization
    public int eraseOverlapIntervals(Interval[] intervals) {
	Arrays.sort(intervals, (a, b) -> a.end - b.end); // sort by end is important. eg: [1,4],[2,3],[3,4]

	int end = Integer.MIN_VALUE;
	int count = 0;
	for (int i = 0; i < intervals.length; i++) { // start from the one which ends early.more no-overlap elements
	    // the interval with early start might be very long and incompatible with many intervals. But if we choose the interval that ends early, we'll have more space left to accommodate more
	    // interval
	    if (intervals[i].start >= end) { // no overlap
		end = intervals[i].end;
		count++;
	    }
	}

	return intervals.length - count; // remove no-overlap elements==>min remove
    }
}
