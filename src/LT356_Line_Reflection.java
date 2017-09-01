import java.util.HashSet;

/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

HashTable, Math
 */
public class LT356_Line_Reflection {
    public boolean isReflected(int[][] points) { // just check if x is reflective. not matter y
	if (points == null || points.length == 0)
	    return true;
	HashSet<String> set = new HashSet<>(); // string = x + " " + y
	int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	for (int i = 0; i < points.length; i++) {
	    int x = points[i][0];
	    int y = points[i][1];
	    max = Math.max(x, max);
	    min = Math.min(x, min);
	    set.add(x + " " + y);
	}

	int sum = max + min; // any pair of x1+x2 should equal to sum

	for (int i = 0; i < points.length; i++) {
	    int x = points[i][0];
	    int y = points[i][1];
	    if (!set.contains((sum - x) + " " + y))
		return false;
	}

	return true;
    }
}
