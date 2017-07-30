import java.util.*;
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
    //adding is O(logN) since lowerKey(), higherKey(), put() and remove() are all O(logN). get is O(n)
    TreeMap<Integer, Interval> tree;                                //start point, interval

    public LT352_Data_Stream_As_Disjoint_Intervals() {
        tree = new TreeMap<>();
    }

    public void addNum(int val) {
        if(tree.containsKey(val)) return;
        Integer l = tree.lowerKey(val);
        Integer h = tree.higherKey(val);
        if(l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) {      //val combined two interval
            tree.get(l).end = tree.get(h).end;
            tree.remove(h);
        } else if(l != null && tree.get(l).end + 1 >= val) {            //val combined with low. add 1, add 2. should be [1,1] then[1,2]
            tree.get(l).end = Math.max(tree.get(l).end, val);           //previous end is [1,1]
        } else if(h != null && h == val + 1) {                          //val combined with high
            tree.put(val, new Interval(val, tree.get(h).end));
            tree.remove(h);
        } else {                                                        //val save as new interval
            tree.put(val, new Interval(val, val));
        }
    }

    public List<Interval> getIntervals() {
        return new ArrayList<>(tree.values());
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
