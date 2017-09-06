import java.util.*;

import common.Interval;

/*
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]
Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?

BST
 */
public class LT352_Data_Stream_As_Disjoint_Intervals {
    /** Initialize your data structure here. */
    // adding is O(logN) since lowerKey(), higherKey(), put() and remove() are all O(logN). get is O(n)
    TreeMap<Integer, Interval> tree; // start point, interval

    public LT352_Data_Stream_As_Disjoint_Intervals() {
	tree = new TreeMap<>();
    }

    public void addNum(int val) {
	if (tree.containsKey(val))
	    return;
	Integer l = tree.lowerKey(val);
	Integer h = tree.higherKey(val);
	if (l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) { // val combined two interval
	    tree.get(l).end = tree.get(h).end;
	    tree.remove(h);
	} else if (l != null && tree.get(l).end + 1 >= val) { // val combined with low. add 1, add 2. should be [1,1] then[1,2]
	    tree.get(l).end = Math.max(tree.get(l).end, val); // previous end is [1,1]
	} else if (h != null && h == val + 1) { // val combined with high
	    tree.put(val, new Interval(val, tree.get(h).end));
	    tree.remove(h);
	} else { // val save as new interval
	    tree.put(val, new Interval(val, val));
	}
    }

    public List<Interval> getIntervals() {
	return new ArrayList<>(tree.values());
    }

    // using insert interval idea
    public class SummaryRanges {
	List<Interval> summary;

	public SummaryRanges() {
	    summary = new ArrayList<>();
	}

	public void addNum(int val) {
	    Interval cur = new Interval(val, val);
	    List<Interval> rst = new ArrayList<Interval>();
	    int pos = 0;
	    for (Interval interval : summary) {
		// non Overlap
		if (cur.end + 1 < interval.start) {
		    rst.add(interval);
		    continue;
		}
		if (cur.start > interval.end + 1) {
		    rst.add(interval);
		    pos++;
		    continue;
		}
		// Overlap // where is the remove?
		if (cur.start - 1 == interval.end)
		    cur.start = interval.start;
		else if (cur.end + 1 == interval.start)
		    cur.end = interval.end;
		// Merge
		else {
		    cur.start = Math.min(cur.start, interval.start);
		    cur.end = Math.max(cur.end, interval.end);
		}
	    }
	    rst.add(pos, cur);
	    summary = new ArrayList<Interval>(rst);
	}

	public List<Interval> getIntervals() {
	    return summary;
	}
    }

    // Follow up: 如果合并次数非常多，与数据流的规模相比不相交区间的数目很少，应当做怎样的优化？
    // 解题思路：
    // 利用TreeSet数据结构，将不相交区间Interval存储在TreeSet中。
    // TreeSet底层使用红黑树实现，可以用log(n)的代价实现元素查找。
    // 每次执行addNum操作时，利用TreeSet找出插入元素val的左近邻元素floor（start值不大于val的最大Interval），以及右近邻元素higher（start值严格大于val的最小Interval）
    // 然后根据floor, val, higher之间的关系决定是否对三者进行合并。
    public class SummaryRanges2 {
	private TreeSet<Interval> intervalSet;

	public SummaryRanges2() {
	    intervalSet = new TreeSet<Interval>(new Comparator<Interval>() {
		public int compare(Interval a, Interval b) {
		    return a.start - b.start;
		}
	    });
	}

	public void addNum(int val) {
	    Interval valInterval = new Interval(val, val);
	    Interval floor = intervalSet.floor(valInterval);
	    if (floor != null) {
		if (floor.end >= val) {
		    return;
		} else if (floor.end + 1 == val) {
		    valInterval.start = floor.start;
		    intervalSet.remove(floor);
		}
	    }
	    Interval higher = intervalSet.higher(valInterval);
	    if (higher != null && higher.start == val + 1) {
		valInterval.end = higher.end;
		intervalSet.remove(higher);
	    }
	    intervalSet.add(valInterval);
	}

	public List<Interval> getIntervals() {
	    return Arrays.asList(intervalSet.toArray(new Interval[0]));
	}
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such: SummaryRanges obj = new SummaryRanges(); obj.addNum(val); List<Interval> param_2 = obj.getIntervals();
 */
