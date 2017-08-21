import java.util.*;
/*
 * Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).

Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.
Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True

Example 2:

[[0,0],[0,10],[10,10],[10,0],[5,5]]

Answer: False

Math
 */
public class LT469_Convex_Polygon {
    public boolean isConvex(List<List<Integer>> points) {
        //convex polygon's. the angles are of same sign. calculate cross product: AB · BC
        boolean gotNegative = false;
        boolean gotPositive = false;
        int numPoints = points.size();
        int B, C;
        for (int A = 0; A < numPoints; A++) {
            // Trick to calc the last 3 points: n - 1, 0 and 1.
            B = (A + 1) % numPoints;
            C = (B + 1) % numPoints;
    
            int crossProduct =
                CrossProductLength(
                    points.get(A).get(0), points.get(A).get(1),
                    points.get(B).get(0), points.get(B).get(1),
                    points.get(C).get(0), points.get(C).get(1));
            if (crossProduct < 0) {
                gotNegative = true;
            }
            else if (crossProduct > 0) {
                gotPositive = true;
            }
            if (gotNegative && gotPositive) return false;           //compare with previous angles is positive or negative
        }
    
        // If we got this far, the polygon is convex.
        return true;
        
    }
    
    public static int CrossProductLength(int Ax, int Ay, int Bx, int By, int Cx, int Cy){
        // Get the vectors' coordinates.
        int BAx = Ax - Bx;
        int BAy = Ay - By;
        int BCx = Cx - Bx;
        int BCy = Cy - By;

        // Calculate the Z coordinate of the cross product.  aXb=|a|*|b|*sin(theta) = a_x*b_y - a_y*b_x
        return (BAx * BCy - BAy * BCx);
    }
}
