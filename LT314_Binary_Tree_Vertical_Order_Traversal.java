/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,20,4,5,2,7],
    _3_
   /   \
  9    20
 / \   / \
4   5 2   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,5,2],
  [20],
  [7]
]

HashTable
 */
import java.util.*;
public class LT314_Binary_Tree_Vertical_Order_Traversal {
	public List<List<Integer>> verticalOrder(TreeNode root) {
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root==null) return res;
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.offer(new Pair(root, 0));
        //by level order
        while(!queue.isEmpty()){
            Pair rootPair = queue.poll();
            TreeNode node = rootPair.root;
            int curLocation = rootPair.location;
            
            List<Integer> cur = new ArrayList<Integer>();
            if(!map.containsKey(curLocation)){
                cur.add(node.val);
                map.put(curLocation, cur);
            }
            else{
                cur = map.get(curLocation);
                cur.add(node.val);       //add new value to the list
                map.put(curLocation, cur);
            }
                
            if(node.left!=null)
                queue.offer(new Pair(node.left, curLocation-1));
            if(node.right!=null)
                queue.offer(new Pair(node.right, curLocation+1));
        }
      
        
        Object[] keys = map.keySet().toArray();
        Arrays.sort(keys);
        
        for(Object i:keys){
            res.add(map.get(i));
        }
        
        return res;
    }
    

    
    public class Pair{
        TreeNode root;
        int location=Integer.MIN_VALUE;
        Pair(TreeNode root, int location) { this.root = root; this.location = location; }
    }
}
