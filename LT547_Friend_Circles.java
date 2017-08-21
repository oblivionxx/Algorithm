/*
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.

DFS, Union Find
 */
public class LT547_Friend_Circles {
    // DFS Solution. Similar to number of island
    public int findCircleNum(int[][] M) {
	boolean[] visited = new boolean[M.length];
	int count = 0;
	for (int i = 0; i < M.length; i++)
	    if (!visited[i]) {
		dfs(M, visited, i); // find friend for i using visited boolean[]
		count++;
	    }
	return count;
    }

    private void dfs(int[][] M, boolean[] visited, int i) {
	for (int j = 0; j < M.length; j++)
	    if (M[i][j] == 1 && !visited[j]) {
		visited[j] = true;
		dfs(M, visited, j);
	    }
    }
    
    public int findCircleNum2(int[][] M) {
        //UF
        int m = M.length;
        QuickUnion qu = new QuickUnion(m);
        for(int i=0;i<m-1;i++){
            for(int j=i+1;j<m;j++){
                if(M[i][j]==1)
                    qu.union(i,j);
            }
        }
        
        return qu.getCount();
    }
    
    public class QuickUnion{
        private int[] id;
        private int cnt;
        
        public QuickUnion(int N){
            id = new int[N];
            for (int i=0;i<N;i++) {
                id[i] = i;
            }
            this.cnt = N;
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
                cnt--;
                return true;
            }
            else        //p,q already connected
                return false;
        }
        
        public int getCount(){
            return cnt;
        }
            
    }
}
