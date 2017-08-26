import java.util.*;

/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

DFS, BFS, Union Find, Graph
 */
public class LT323_Number_of_Connected_Components_in_an_Undirected_Graph {
    /*
     * Union Find This is 1D version of Number of Islands II. For more
     * explanations, check out this 2D Solution. n points = n islands = n trees
     * = n roots. With each edge added, check which island is e[0] or e[1]
     * belonging to. If e[0] and e[1] are in same islands, do nothing.
     * Otherwise, union two islands, and reduce islands count by 1. Bonus: path
     * compression can reduce time by 50%. Hope it helps!
     */
    public int countComponents(int n, int[][] edges) {
	int[] roots = new int[n];
	for (int i = 0; i < n; i++)
	    roots[i] = i;

	for (int[] e : edges) {
	    int root1 = find(roots, e[0]);
	    int root2 = find(roots, e[1]);
	    if (root1 != root2) {
		roots[root1] = root2; // union
		n--;
	    }
	}
	return n;
    }

    public int find(int[] roots, int id) {
	while (roots[id] != id) {
	    roots[id] = roots[roots[id]]; // optional: path compression
	    id = roots[id];
	}
	return id;
    }

    // UF 2
    public int countComponents2(int n, int[][] edges) {
	QuickUnion qu = new QuickUnion(n);
	for (int i = 0; i < edges.length; i++) {
	    qu.union(edges[i][0], edges[i][1]);
	}
	return qu.getCount();

    }

    public class QuickUnion {
	private int[] id;
	private int cnt;

	public QuickUnion(int N) {
	    id = new int[N];
	    for (int i = 0; i < N; i++) {
		id[i] = i;
	    }
	    this.cnt = N;
	}

	private int root(int i) {
	    while (i != id[i])
		i = id[i];

	    // root(id[id[i...]]) until i=id. so find the top root.
	    return i;
	}

	public boolean connected(int p, int q) {
	    return root(p) == root(q);
	}

	public boolean union(int p, int q) {
	    int i = root(p);
	    int j = root(q);
	    if (i != j) {
		id[i] = j; // modify the root p to a pointer to root q
		cnt--;
		return true;
	    } else // p,q already connected
		return false;
	}

	public int getCount() {
	    return cnt;
	}

    }

    // DFS
    // https://leetcode.com/discuss/80671/java-concise-dfs
    public int countComponents3(int n, int[][] edges) {
	if (n <= 1)
	    return n;
	// form adjacent list
	List<List<Integer>> adjList = new ArrayList<List<Integer>>();
	for (int i = 0; i < n; i++) {
	    adjList.add(new ArrayList<Integer>());
	}
	for (int[] edge : edges) {
	    adjList.get(edge[0]).add(edge[1]);
	    adjList.get(edge[1]).add(edge[0]);
	}

	boolean[] visited = new boolean[n];
	int count = 0;
	for (int i = 0; i < n; i++) {
	    if (!visited[i]) {
		dfs(i, adjList, visited);
		count++;
	    }
	}

	return count;
    }

    public void dfs(int i, List<List<Integer>> adjList, boolean[] visited) {
	visited[i] = true; // update visited.
	for (int j : adjList.get(i)) { // loop all its adjacent node
	    if (!visited[j]) {
		dfs(j, adjList, visited);
	    }
	}
    }
}
