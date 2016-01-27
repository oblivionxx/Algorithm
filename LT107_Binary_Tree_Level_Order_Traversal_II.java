/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
Tree, BFS
 */
import java.util.*;
public class LT107_Binary_Tree_Level_Order_Traversal_II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
        	
        	res.add(0, new ArrayList<>(elm));		//only difference to levelOrder. insert cur level before the previous level. 
        	elm.clear();
        	curLevel = nextLevel;
        	nextLevel = 0;
        }
        
        return res;
    }
}
