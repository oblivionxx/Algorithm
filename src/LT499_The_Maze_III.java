import java.util.*;

/*
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.

Note:
There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

DFS, BFS
 */
public class LT499_The_Maze_III {
    // https://discuss.leetcode.com/topic/77474/similar-to-the-maze-ii-easy-understanding-java-bfs-solution/8
    // https://discuss.leetcode.com/topic/77074/clear-java-accepted-dfs-solution-with-explanation/2
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
	int m = maze.length, n = maze[0].length;
	int[][] length = new int[m][n];

	String[][] path = new String[m][n];
	for (String[] items : path)
	    Arrays.fill(items, new String());

	Queue<int[]> q = new LinkedList<>();

	int[] dx = { 1, 0, 0, -1 };
	int[] dy = { 0, -1, 1, 0 };
	char[] step = { 'd', 'l', 'r', 'u' };

	q.offer(ball);

	while (!q.isEmpty()) {
	    int[] cur = q.poll();
	    if (Arrays.equals(cur, hole))
		continue;

	    for (int k = 0; k < 4; k++) {
		int i = cur[0], j = cur[1];
		int count = 0;
		while ((i != hole[0] || j != hole[1]) && i + dx[k] >= 0 && i + dx[k] < m && j + dy[k] >= 0
			&& j + dy[k] < n && maze[i + dx[k]][j + dy[k]] == 0) {
		    count++;
		    i += dx[k];
		    j += dy[k];
		}

		if ((i != ball[0] || j != ball[1])
			&& (length[i][j] == 0 || length[i][j] > length[cur[0]][cur[1]] + count
				|| (length[i][j] == length[cur[0]][cur[1]] + count
					&& path[i][j].compareTo(path[cur[0]][cur[1]] + step[k]) > 0))) {

		    path[i][j] = path[cur[0]][cur[1]] + step[k];
		    length[i][j] = length[cur[0]][cur[1]] + count;

		    q.offer(new int[] { i, j });
		}
	    }
	}

	return path[hole[0]][hole[1]].length() == 0 ? "impossible" : path[hole[0]][hole[1]];
    }
}
