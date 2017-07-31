/*
There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one. The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell. The distance is represented by the number of moves.
Note:
All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.

Math
 */
public class LT573_Squirrel_Simulation {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
	// except the 1st nut, other nuts. the distance is 2*dist(nut_i, tree).
	// from tree to nut, pick nut back to tree
	// total distance = sum(2*dist(nut_i, tree))
	// total distance - dist_i+dist(nut_i, sq) ==>
	// max(dist_i-dist(nut_i,sq)) substract the unnecessary part of 1st nut
	int sum = 0, max = Integer.MIN_VALUE;
	for (int[] nut : nuts) {
	    int dist = Math.abs(nut[0] - tree[0]) + Math.abs(nut[1] - tree[1]);
	    sum += 2 * dist;
	    max = Math.max(max, dist - Math.abs(nut[0] - squirrel[0]) - Math.abs(nut[1] - squirrel[1]));
	}

	return sum - max;
    }
}
