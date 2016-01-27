/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
         
DFS, BFS, Graph
 */
import java.util.*;
public class LT133_Clone_Graph {
	//dfs
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        
        if(node==null) return null;
        //store<origin_node, cloned_node  >
        HashMap<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        map.put(node, head);
        //dfs(map, origin_node) 
        dfs(map, node);
        
        return head;
    }
    
    public void dfs(HashMap<UndirectedGraphNode,UndirectedGraphNode> map,UndirectedGraphNode node){
        if(node==null) return;
        for(UndirectedGraphNode aneighbor:node.neighbors){
            if(!map.containsKey(aneighbor)){    					//if not visited
                UndirectedGraphNode newneighbor = new UndirectedGraphNode(aneighbor.label);
                map.put(aneighbor, newneighbor);
                dfs(map,aneighbor);
            }
            map.get(node).neighbors.add(map.get(aneighbor));	//update cloned_nodes neighbors property.
        }
    }
    
    
    //bfs
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if(node==null) return null;
        HashMap<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<>(); //store the visited node itself and its neighbouring
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);        //start clone
        Queue<UndirectedGraphNode> queue =  new LinkedList<UndirectedGraphNode>();
        queue.add(node);
        map.put(node, head);       //node loop itself
        
        while(!queue.isEmpty()){
            UndirectedGraphNode cur = queue.poll();
            for(UndirectedGraphNode aneighbour:cur.neighbors){
                //if not visited. add to queue
                if(!map.containsKey(aneighbour)){
                    queue.add(aneighbour);
                    UndirectedGraphNode newneighbor = new UndirectedGraphNode(aneighbour.label);     //store visited node itself and neibouring
                    map.put(aneighbour, newneighbor);
                }
                
                //update neibouring information. --> add cur's neighbour to the map. 
                map.get(cur).neighbors.add(map.get(aneighbour));
                //then the map key: node, value: node itself and its neighbour
            }
        }
        
        return head;
    }
}


class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
		}
}
 
