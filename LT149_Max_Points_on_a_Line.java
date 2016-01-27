import java.util.HashMap;

/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
Hashtable, Math
 */
public class LT149_Max_Points_on_a_Line {
	public int maxPoints(Point[] points) {
		//O(n^2). It's because you have to calculate all slopes between any two points.
        //loop point, then loop the rest, those with same slope k is on the same line. k = (y1-y2)/(x1-x2)
        //attention: same point, verticle line which is infinite k
		//attention: slope k is float. better not to compare two float.
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
