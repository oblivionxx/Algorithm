import java.util.List;
import java.util.Iterator;

/*
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].

Design
 */
public class LT281_Zigzag_Iterator {
    // return head of each list.
    public class ZigzagIterator {
	Iterator<Integer> it1;
	Iterator<Integer> it2;
	int turns;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
	    this.it1 = v1.iterator();
	    this.it2 = v2.iterator();
	    turns = 0;
	}

	public int next() {
	    // 如果没有下一个则返回0
	    if (!hasNext()) {
		return 0;
	    }
	    turns++; // loop starting from list1
	    // 如果是第奇数个，且第一个列表也有下一个元素时，返回第一个列表的下一个
	    // 如果第二个列表已经没有，返回第一个列表的下一个
	    if ((turns % 2 == 1 && it1.hasNext()) || (!it2.hasNext())) {
		return it1.next();
	    } else if ((turns % 2 == 0 && it2.hasNext()) || (!it1.hasNext())) {
		return it2.next();
	    }
	    return 0;
	}

	public boolean hasNext() {
	    return it1.hasNext() || it2.hasNext();
	}
    }

    /**
     * Your ZigzagIterator object will be instantiated and called as such: ZigzagIterator i = new ZigzagIterator(v1, v2); while (i.hasNext()) v[f()] = i.next();
     */
}
