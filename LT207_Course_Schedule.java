/*
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:
2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.

Graph, DFS, BFS, Topological Sort
 */
import java.util.*;
public class LT207_Course_Schedule {
	//DFS
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        // init the adjacency list
        List<Set<Integer>> posts = new ArrayList<Set<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            posts.add(new HashSet<Integer>());
        }
        
        // fill the adjacency list
        for (int i = 0; i < prerequisites.length; i++) {
            posts.get(prerequisites[i][1]).add(prerequisites[i][0]);        //store the pre-course. of node i.
        }
        
        // count the pre-courses. in-degree
        int[] preNums = new int[numCourses];            //for arbitrary node i, sum up totoal pre-course.
        for (int i = 0; i < numCourses; i++) {
            Set<Integer> set = posts.get(i);
            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
            	preNums[it.next()]++;
            }
        }
        
        // remove a non-pre course each time
        for (int i = 0; i < numCourses; i++) {
            // find a non-pre course 
            int j = 0;
            for ( ; j < numCourses; j++) {
                if (preNums[j] == 0) break;
            }
            
            // if not find a non-pre course. there's no node with in-degree=0. which also means there's a cycle in the graph
            if (j == numCourses) return false;
            
            preNums[j] = -1;                            //set it as start
            
            // decrease courses that post the course    //remove the links==> decrease the courses
            Set<Integer> set = posts.get(j);
            Iterator<Integer> it = set.iterator();
            while (it.hasNext()) {
            	preNums[it.next()]--;
            }
        }
        
        return true;
    }
	
	//BFS
	public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<Set<Integer>> adjLists = new ArrayList<Set<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            adjLists.add(new HashSet<Integer>());
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
            adjLists.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        //count in-degree
        int[] indegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int x : adjLists.get(i)) {
                indegrees[x]++;
            }
        }
        
        //add all the node with in-degree=0
        int count = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
                count++;
            }
        }

        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int x : adjLists.get(cur)) {
                indegrees[x]--;
                if (indegrees[x] == 0) {
                    queue.offer(x);
                    count++;
                }
            }

        }
        
        //check if all the node with in-degree=0 in the end.
        if (count == numCourses) return true;
        return false;
    }
}
