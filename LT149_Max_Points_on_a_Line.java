import java.util.*;

/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
Hashtable, Math
 */
public class LT149_Max_Points_on_a_Line {
	//O(n^2). consider same point, consider k->infinite. 
    public int maxPoints(Point[] points) {
        int max = 0;
		if (points == null || points.length == 0) return 0;
		int n = points.length;
		if (n == 1) return 1;
		for (int i = 0; i < n - 1; ++i) {
			Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
			int dup = 0, lclmax = 0;
			for (int j = i + 1; j < n; ++j) {
				int x = points[j].x - points[i].x;
				int y = points[j].y - points[i].y;
				if (x == 0 && y == 0) {
					++dup;
					continue;
				}
				int gcd = gcd(x, y);
				x /= gcd;
				y /= gcd;
				if (!map.containsKey(x)) map.put(x, new HashMap<Integer, Integer>());
				if (!map.get(x).containsKey(y)) map.get(x).put(y, 0);
				map.get(x).put(y, map.get(x).get(y) + 1);
				lclmax = Math.max(lclmax, map.get(x).get(y));
			}
			max = Math.max(max, dup + lclmax + 1);
		}
		return max;
	}
	
	private int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	public int maxPoints2(Point[] points) {
		//O(n^2). It's because you have to calculate all slopes between any two points.
        //loop point, then loop the rest, those with same slope k is on the same line. k = (y1-y2)/(x1-x2)
        //attention: same point, verticle line which is infinite k
		//attention: slope k is float. better not to compare two float.
		//not working for: Input:[[0,0],[94911151,94911150],[94911152,94911151]]
		//Output: 3. Expected: 2
        int max = 0;
        if(points.length<3) return points.length;
        
        for(Point X:points){
            HashMap<Float, Integer> map = new HashMap<>(); //k, number of point
            float k = 0;
            int duplicate =0;
            int vertical = 0;
            for(Point Y:points){
                if(X.x==Y.x){
                  if(X.y==Y.y) 
                        duplicate++;           //X=Y, add itself once to duplicate.  
                  if(X.y!=Y.y) 
                        vertical++;
                }else{
                    k = (float)(Y.y-X.y)/(Y.x-X.x);
                    if(!map.containsKey(k))
                        map.put(k,1);
                    else
                        map.put(k,map.get(k)+1);
              }
            }
            
            //compare max, all value+duplicate, vertical+duplicate
            for (Integer value : map.values())
                max = Math.max(max, value+duplicate); 
            
            
            max = Math.max(max, vertical+duplicate); 
        }
        return max;
    }
}

class Point {
	int x;
	int y;
	Point() { x = 0; y = 0; }
	Point(int a, int b) { x = a; y = b; }
}
