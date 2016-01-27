/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

Tree, BFS
 */
import java.util.*;
public class LT102_Binary_Tree_Level_Order_Traversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        
        List<Integer> elm = new ArrayList<Integer>();
		
        queue.add(root);
        int curLevel = 1;
        int nextLevel = 0;
        while(!queue.isEmpty()){
        	for(int i=0;i<curLevel;i++){
	        	TreeNode cur = queue.poll();
	        	elm.add(cur.val);
	        	if(cur.left!=null){
	        		queue.add(cur.left);
	        		nextLevel++;
	        	}
	        	if(cur.right!=null){
	        		queue.add(cur.right);
	        		nextLevel++;
	        	}
        	}
        	
        	res.add(new ArrayList<>(elm));
        	elm.clear();
        	curLevel = nextLevel;
        	nextLevel = 0;
        }
        
        return res;
    }
}
