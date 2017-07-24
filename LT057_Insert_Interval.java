import java.util.*;

/*
 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
/*
 * Array, Sort
 */
public class LT057_Insert_Interval {
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> res = new ArrayList<>();

		for (Interval elm : intervals) {
			if (elm.end < newInterval.start) {
				res.add(elm);
			} else if (elm.start > newInterval.end) {
				res.add(newInterval);
				newInterval = elm;
			} else { // need to merge elm and newInterval to newInterval
				int nstart = Math.min(elm.start, newInterval.start);
				int nend = Math.max(newInterval.end, elm.end);
				newInterval = new Interval(nstart, nend); // cannot write to
															// res.add(new
															// Interval(nstart,
															// nend))
															// if like this,
															// cannot pass
															// [[1,5]], [2,3]
															// case.
			}
		}

		res.add(newInterval); // cannot forget
		return res;
	}
}
