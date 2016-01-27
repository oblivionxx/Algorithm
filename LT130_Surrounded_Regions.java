/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

BFS, Union Find
 */
import java.util.*;
public class LT130_Surrounded_Regions {
	//flip border O to *. using fill(board, i,j)
	//flip inner O to X.
	//flip back border O.
	public void solve(char[][] board) {
		if(board==null || board.length<=1 || board[0].length<=1)
	        return;
	    int m=board.length;
	    int n = board[0].length;
	    for(int i=0;i<n;i++){
	        fill(board, 0, i);
	        fill(board, m-1, i);
	    }
	    
	    for(int i=0;i<m;i++){
	        fill(board, i, 0);
	        fill(board, i, n-1);
	    }
	    
	    for(int i=0;i<m;i++){
	        for(int j=0;j<n;j++){
	            if(board[i][j]=='O')    
	                board[i][j] ='X';
	            else if(board[i][j]=='*')		//can do at the same time since inner O and board * are not connected.
	                board[i][j] ='O';
	        }
	    }
	}
	
	//BFS
	public void fill(char[][] board, int i, int j){
	    if(board[i][j]!='O')
	        return;
	    int m=board.length;
	    int n = board[0].length;
	    int loc = i*n+j;
	    board[i][j] = '*';
	    Queue<Integer> q = new LinkedList<>();
	    q.offer(loc);
	    while(!q.isEmpty()){
	        int cur = q.poll();
	        int row = cur/n;
	        int col = cur%n;
	        
	        if(row>0 && board[row-1][col]=='O') {
	            q.offer((row-1)*n+col);
	            board[row-1][col] = '*';
	        }
	        if(col>0 && board[row][col-1]=='O'){
	            q.offer(row*n+col-1);
	            board[row][col-1] = '*';
	        }
	        if(row<m-1 && board[row+1][col]=='O'){
	            q.offer((row+1)*n+col);
	            board[row+1][col] = '*';
	        }
	        if(col<n-1 && board[row][col+1]=='O'){
	            q.offer(row*n+col+1);
	            board[row][col+1] = '*';
	        }
	        
	    }
	}
}
