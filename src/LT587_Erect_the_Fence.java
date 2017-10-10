import java.util.*;

/*
 * There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden. Your job is to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed. Your task is to help find the coordinates of trees which are exactly located on the fence perimeter.

Example 1:
Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
Explanation:

Example 2:
Input: [[1,2],[2,2],[4,2]]
Output: [[1,2],[2,2],[4,2]]
Explanation:

Even you only have trees in a line, you need to use rope to enclose them. 
Note:

All trees should be enclosed together. You cannot cut the rope to enclose trees that will separate them in more than one group.
All input integers will range from 0 to 100.
The garden has at least one tree.
All coordinates are distinct.
Input points have NO order. No order required for output.

Geometry
 */
public class LT587_Erect_the_Fence {
    // There are couple of ways to solve Convex Hull problem. http://blog.csdn.net/u014688145/article/details/72200018
    // https://en.wikipedia.org/wiki/Convex_hull_algorithms
    // The following code implements Gift wrapping aka Jarvis march algorithm
    // logic to handle case of multiple Points in a line because original Jarvis O(nH)
    // For every point on the hull we examine all the other points to determine the next point. Here n is number of input points and H is number of output or hull points ($$H ≤ n).
    // march algorithm assumes no three points are collinear.
    // It also uses knowledge in this problem
    // https://leetcode.com/problems/convex-polygon . 
    public List<Point> outerTrees(Point[] points) {
	Set<Point> result = new HashSet<>();

	// Find the leftmost point
	Point first = points[0];
	int firstIndex = 0;
	for (int i = 1; i < points.length; i++) {		//point with smallest x must be a point on the fence. same for maximum x and y.
	    if (points[i].x < first.x) {
		first = points[i];
		firstIndex = i;
	    }
	}
	result.add(first);

	Point cur = first;				//Point A
	int curIndex = firstIndex;
	do {
	    Point next = points[0];			//Point C. pick any.
	    int nextIndex = 0;
	    for (int i = 1; i < points.length; i++) {	//Point B
		if (i == curIndex)			//skip current point
		    continue;
		int cross = crossProductLength(cur, points[i], next);	//AB, BC clockwise, cross>0, meaning B在AC连线的右下侧.所以next应选择B而不是C
		if (nextIndex == curIndex || cross > 0 ||		//next is localmax.
			// Handle collinear points, use the farthest one
			(cross == 0 && distance(points[i], cur) > distance(next, cur))) {
		    next = points[i];					
		    nextIndex = i;
		}
	    }
	    // Handle collinear points
	    for (int i = 0; i < points.length; i++) {
		if (i == curIndex)			//skip current point.
		    continue;
		int cross = crossProductLength(cur, points[i], next);	//check any other node collinear with next.
		if (cross == 0) {					//add all 
		    result.add(points[i]);
		}
	    }

	    cur = next;
	    curIndex = nextIndex;

	} while (curIndex != firstIndex);		//end when loop back to 1st node

	return new ArrayList<Point>(result);
    }

    //This function returns a negative value if the point B is more counterclockwise to A than the point C
    private int crossProductLength(Point A, Point B, Point C) {
	// Get the vectors' coordinates.
	int BAx = A.x - B.x;
	int BAy = A.y - B.y;
	int BCx = C.x - B.x;
	int BCy = C.y - B.y;

	// Calculate the Z coordinate of the cross product.
	return (BAx * BCy - BAy * BCx);
    }

    private int distance(Point p1, Point p2) {
	return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    class Point {
	int x;
	int y;

	Point() {
	    x = 0;
	    y = 0;
	}

	Point(int a, int b) {
	    x = a;
	    y = b;
	}
    }

    // Graham Scan
    // https://leetcode.com/articles/erect-the-fence/#approach-2-graham-scan-accepted
}
