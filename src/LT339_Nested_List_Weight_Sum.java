import java.util.List;

import common.NestedInteger;

/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)

Example 2:
Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)

DFS
 */
public class LT339_Nested_List_Weight_Sum {
    public int depthSum(List<NestedInteger> nestedList) {
	return helper(nestedList, 1);
    }

    public int helper(List<NestedInteger> nestedList, int depth) {
	int sum = 0;
	if (nestedList == null || nestedList.size() == 0)
	    return 0;
	for (NestedInteger each : nestedList) {
	    if (each.isInteger())
		sum += depth * each.getInteger();
	    else
		sum += helper(each.getList(), depth + 1);
	}

	return sum;
    }
}
