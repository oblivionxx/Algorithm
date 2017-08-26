import java.util.HashSet;

/*
 * Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.

Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).


Example 1:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [3,2,4,4],
  [1,3,2,4],
  [2,3,3,4]
]

Return true. All 5 rectangles together form an exact cover of a rectangular region.

Example 2:

rectangles = [
  [1,1,2,3],
  [1,3,2,4],
  [3,1,4,2],
  [3,2,4,4]
]

Return false. Because there is a gap between the two rectangular regions.

Example 3:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [3,2,4,4]
]

Return false. Because there is a gap in the top center.

Example 4:

rectangles = [
  [1,1,3,3],
  [3,1,4,2],
  [1,3,2,4],
  [2,2,4,4]
]

Return false. Because two of the rectangles overlap with each other.

 */
public class LT391_Perfect_Rectangle {
    // 1. size of area = sum of individual one(no overlap situation case 4)
    // 2. count of corner points should 2 or 4. except four outside corner, count=1 (odd is not allowed. case 2, 3)
    public boolean isRectangleCover(int[][] rectangles) {

	if (rectangles.length == 0 || rectangles[0].length == 0)
	    return false;

	int x1 = Integer.MAX_VALUE; // for 4 outside corner
	int x2 = Integer.MIN_VALUE;
	int y1 = Integer.MAX_VALUE;
	int y2 = Integer.MIN_VALUE;

	HashSet<String> set = new HashSet<>();
	int area = 0;

	for (int[] rect : rectangles) {
	    x1 = Math.min(rect[0], x1);
	    y1 = Math.min(rect[1], y1);
	    x2 = Math.max(rect[2], x2);
	    y2 = Math.max(rect[3], y2);

	    area += (rect[2] - rect[0]) * (rect[3] - rect[1]); // add each small rectangles

	    String corner1 = rect[0] + " " + rect[1]; // count each corner
	    String corner2 = rect[0] + " " + rect[3];
	    String corner3 = rect[2] + " " + rect[1];
	    String corner4 = rect[2] + " " + rect[3];

	    if (!set.add(corner1))
		set.remove(corner1); // try add. if already exist then remove. guarantee to be even
	    if (!set.add(corner2))
		set.remove(corner2);
	    if (!set.add(corner3))
		set.remove(corner3);
	    if (!set.add(corner4))
		set.remove(corner4);
	}

	// check if have four corner.
	if (!set.contains(x1 + " " + y1) || !set.contains(x1 + " " + y2) || !set.contains(x2 + " " + y1)
		|| !set.contains(x2 + " " + y2) || set.size() != 4)
	    return false;

	return area == (x2 - x1) * (y2 - y1);
    }
}
