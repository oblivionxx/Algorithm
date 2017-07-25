import java.util.*;

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

DFS, BFS, Union Find
 */
public class LT200_Number_of_Islands {
	//1. bfs
	class Pair{
		int x,y;
		Pair(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	
	//bfs
    public int numIslands(char[][] grid) {
    	if(grid==null||grid.length==0) return 0;
    	int row = grid.length,column = grid[0].length;
    	int res = 0;
        for(int i=0;i<row;i++){
        	for(int j=0;j<column;j++){
        		if(grid[i][j]=='1'){
        			bfs(grid, i, j);
        			res++;
        		}
        	}
        }
        return res;
    }
    
    private void bfs(char[][]grid,int i,int j){
        int m = grid.length, n = grid[0].length;
    	Queue<Pair> que = new LinkedList<>();
    	que.offer(new Pair(i,j));
    	while(!que.isEmpty()){
    		Pair p= que.poll();
    		grid[p.x][p.y] = '*';
    		if(p.x>0 && grid[p.x-1][p.y]=='1'){
    			grid[p.x-1][p.y] ='*';
    			que.offer(new Pair(p.x-1,p.y));
    		}
            if(p.x<m-1 && grid[p.x+1][p.y]=='1'){
    			grid[p.x+1][p.y] ='*';
    			que.offer(new Pair(p.x+1,p.y));
    		}
            if(p.y>0 && grid[p.x][p.y-1]=='1'){
    			grid[p.x][p.y-1] ='*';
    			que.offer(new Pair(p.x,p.y-1));
    		}
    		if(p.y<n-1 && grid[p.x][p.y+1]=='1'){
    			grid[p.x][p.y+1] ='*';
    			que.offer(new Pair(p.x,p.y+1));
    		}
    	}
    }
    
    //2. DFS
    public int numIslands2(char[][] grid) {
        int m = grid.length;
        if(m==0) return 0;
        int n= grid[0].length;
        if(n==0) return 0;
        
        int res = 0;
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]!='1') continue;   //do nothing
                	res++;
                	dfs(grid,i,j, m, n);  //search for connectivity
            }
        }
        
        return res;
    }
    
    private void dfs(char[][]grid, int i, int j, int m, int n){
        if(i<0 || i>=m || j<0 || j>=n) return;
        if(grid[i][j]=='1'){
            grid[i][j]='2';
            dfs(grid,i-1,j,m,n);
            dfs(grid,i+1,j,m,n);
            dfs(grid,i,j-1,m,n);
            dfs(grid,i,j+1,m,n);            //dfs to the connected part
        }   
    }
    
    //3. Union
    public int numIslands3(char[][] grid) {
        int m = grid.length;
        if(grid==null || m==0) return 0;
        int n = grid[0].length;
        
        //encode the location to i*n+j
        //Connect all water with one single end unit (m * n), and connect all land with its neighboring lands
        QuickUnion u = new QuickUnion(m*n+1);
        for(int i =0;i<m;i++){
            for(int j =0;j<n ;j++){
               if(grid[i][j] == '0')
                    u.union(i*n+j, m*n);
                else{   //connect with right, and bottom...
                    if(j < n - 1 && grid[i][j + 1] == '1') u.union(i * n + j, i * n + j + 1);
                    if(i < m - 1 && grid[i + 1][j] == '1') u.union(i * n + j, i * n + j + n);
                }
            }
        }
        return u.getCount()-1;
    }
    
    
    public class QuickUnion{
        private int[] id;
        private int count;
    
        //constructor
        public QuickUnion(int N){
            id = new int[N];
            for (int i=0;i<N;i++) {
                id[i] = i;
            }   
            this.count = N;
        }
    
    
        private int root(int i){
            while(i!=id[i])
                i = id[i];
    
            //root(id[id[i...]]) until i=id. so find the top root.
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
        
        public int getCount(){
            return count;
        }
    }
}
