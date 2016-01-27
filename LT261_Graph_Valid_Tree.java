/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
For example:
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

DFS, BFS, Graph, Union Find
 */
public class LT261_Graph_Valid_Tree {
	//1. Union Find: if all nodes connected in one tree. And there's no loop.
	public boolean validTree(int n, int[][] edges) {
        QuickUnion qu = new QuickUnion(n);
        for(int i = 0; i < edges.length; i++){
            // 如果两个节点已经在同一集合中，说明新的边将产生环路
            if(!qu.union(edges[i][0], edges[i][1])){        //two vertices: edges[i][0], edges[i][1]
                return false;
            }
        }
        return qu.getCount() == 1;
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
    
    //2. https://leetcode.com/discuss/54211/ac-java-solutions-union-find-bfs-dfs
    //3. https://leetcode.com/discuss/52568/ac-java-graph-dfs-solution-with-adjacency-list
}
