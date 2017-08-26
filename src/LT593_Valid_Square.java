import java.util.*;

/*
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.

Math
 */
public class LT593_Valid_Square {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
	// just calculate distance of each data point. 6 options
	HashSet<Integer> hs = new HashSet<>(
		Arrays.asList(dis(p1, p2), dis(p1, p3), dis(p1, p4), dis(p2, p3), dis(p2, p4), dis(p3, p4)));
	return !hs.contains(0) && hs.size() == 2; // One each for side & diagonal
    }

    public int dis(int[] a, int[] b) {
	return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }
}
