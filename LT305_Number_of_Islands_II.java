import java.util.ArrayList;
import java.util.List;

/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?

Union Find
 */
public class LT305_Number_of_Islands_II {
	//additional ref: https://leetcode.com/discuss/69392/python-clear-solution-unionfind-class-weighting-compression
	//https://leetcode.com/discuss/69572/easiest-java-solution-with-explanations
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<Integer>();
        
        QuickUnion u = new QuickUnion(m*n);
        
        int len = positions.length; //#len points
        for(int i=0;i<len;i++){
            int x = positions[i][0];
            int y = positions[i][1];
            
            u.setIsland(x*n+y);
            if(x-1>=0){
                if (u.root((x-1)*n+y)>=0)       //has been set island before
                    u.union((x-1)*n+y, x*n+y);
            }
            
            if(x+1<m){
                if (u.root((x+1)*n+y)>=0)
                    u.union((x+1)*n+y, x*n+y);
            }
            
            if(y+1<n){
                if (u.root(x*n+y+1)>=0)
                    u.union(x*n+y+1, x*n+y);
            }
            
            if(y-1>=0){   
                if (u.root(x*n+y-1)>=0)
                u.union(x*n+y-1, x*n+y);
            }
            
            res.add(u.count);
        
        }
        
        return res;
    }
    
    
    public class QuickUnion{
        private int[] id;
        public int count;
        
        //constructor
        public QuickUnion(int N){
            id = new int[N];
            for (int i=0;i<N;i++) {
                id[i] = -1;
            }  
       
        }
    
        public void setIsland(int p){
            id[p] = p;
            count++;
        }
        
        
        private int root(int i){
            if(id[i]==-1) return -1;
            while(i!=id[i])
                i = id[i];
            return i;
        }

    
        public boolean connected(int p, int q){
            return root(p)==root(q);
        }
    
        public boolean union(int p, int q){
                int i = root(p);
                int j = root(q);
                if(i!=j){
                    id[i] = j; //modify the root p to a pointer to root q
                    count--;
                    return true;
                }
                else 
                    return false;
        }
    }
}
