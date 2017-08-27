
/*
 Given a collection of intervals, merge all overlapping intervals.

 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 */
/*
 * Array, Sort
 */
import java.util.*;

import common.Interval;

public class LT056_Merge_Interval {
    public List<Interval> merge(List<Interval> intervals) {
	List<Interval> res = new ArrayList<>();
	if (intervals == null || intervals.size() == 0)
	    return res;

	Comparator<Interval> cmp = new Comparator<Interval>() {
	    public int compare(Interval a, Interval b) {
		return a.start == b.start ? a.end - b.end : a.start - b.start;
	    }
	};

	Collections.sort(intervals, cmp);
	// start merge
	res.add(intervals.get(0));
	for (int i = 1; i < intervals.size(); i++) {
	    Interval cur = intervals.get(i);
	    if (res.get(res.size() - 1).end >= cur.start) { // overlap, need
							    // merge. attention:
							    // equal.
		res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, cur.end);
	    } else {
		res.add(cur);
	    }
	}

	return res;
    }

}
