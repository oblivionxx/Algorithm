import java.util.*;

/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
  
BFS
 */
public class LT286_Walls_And_Gates {
    //O(m*n) space O(n)
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;
        //find a gate. starting bfs
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(rooms[i][j]==0)
                    bfs(rooms,i,j);
            }
        }
    }
    
    public void bfs(int[][] rooms, int i, int j){
        //find the sorrounding 4, if INF, then add to queue. keep the level as the distance. 
        //if visited, update the min-distance
        //if meet another gate 0 or wall -1, then dont add. Prune step necessary.
        Queue<Integer> queue = new LinkedList<Integer>();
        int dist = 0;
        queue.offer(i*rooms[0].length+j);        //store the location. can create a position class.
        
        Set<Integer> visited = new HashSet<Integer>();
        visited.add(i * rooms[0].length + j);	 //in one round. dont go backward to repeat.
        while(!queue.isEmpty()){
            int size = queue.size();        //size will change. the size is the length of this level. dont merge levels.
            //end a level. then dist++
            for(int k=0;k<size;k++){
                int cur = queue.poll();
                int row = cur/rooms[0].length;
                int col = cur%rooms[0].length;
                rooms[row][col] = Math.min(rooms[row][col], dist);
                int up = (row - 1) * rooms[0].length + col;
                int down = (row + 1) * rooms[0].length + col;
                int left = row * rooms[0].length + col - 1;
                int right = row * rooms[0].length + col + 1;
                if(row > 0 && rooms[row - 1][col] > 0 && !visited.contains(up)){    //be sure can visit the position by checking row and col with the border
                    queue.offer(up);
                    visited.add(up);
                }
                if(col > 0 && rooms[row][col - 1] > 0 && !visited.contains(left)){
                    queue.offer(left);
                    visited.add(left);
                }
                if(row < rooms.length - 1 && rooms[row + 1][col] > 0 && !visited.contains(down)){
                    queue.offer(down);
                    visited.add(down);
                }
                if(col < rooms[0].length - 1 && rooms[row][col + 1] > 0 && !visited.contains(right)){
                    queue.offer(right);
                    visited.add(right);
                }
            }
            dist++;
        }
    }
    
    //dfs. O(mn) but faster than bfs. only update those didn't have optimized distance.
    int[][] dir ={{0,1},{0,-1},{1,0},{-1,0}};
    public void wallsAndGates2(int[][] rooms) {
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                if(rooms[i][j]==0)
                	dfs(rooms,i,j);
            }
        }
    }
    
    public void dfs(int[][] rooms,int i,int j){
        for(int[] d:dir){
            if(i+d[0]>=0 && i+d[0]<rooms.length && j+d[1]>=0 && j+d[1]<rooms[0].length && rooms[i+d[0]][j+d[1]]>rooms[i][j]+1){
                rooms[i+d[0]][j+d[1]]=rooms[i][j]+1;
                dfs(rooms,i+d[0],j+d[1]);
            }
        }
    }
}
