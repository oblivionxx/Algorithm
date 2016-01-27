/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].

Tree, DFS, BFS
 */
import java.util.*;
public class LT199_Binary_Tree_Right_Side_View {
	//BFS. Level order idea. when reach end of level, add to result.
	public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		
	    queue.add(root);
		int curNum = 1;
		int nextNum = 0;
		if(root==null) return res;
		
		while(!queue.isEmpty()){
			TreeNode cur  = queue.poll();
			curNum--;
			
			if(cur.left!=null){
			   queue.add(cur.left);
			   nextNum++;
			}
			
			if(cur.right!=null){
			    queue.add(cur.right);
			    nextNum++;
			}
	
		    if(curNum==0){
			    res.add(cur.val);
			    curNum = nextNum;
			    nextNum=0;
		    }
		}
		
	    return res;
    }
	
	//DFS. recheck
	public  List<Integer> rightSideView2(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if(root==null)
			return res;
		dfs(res, root, 0);
		return res;
	}
 
	public  void dfs(List<Integer> res, TreeNode root, int cur) {		
		//cur is height.height == result.size() is the core part in this recursion, it limits the amount of Node add to the result in each level(height) of the Tree.
		if (res.size() <= cur) 					// if it's ok to add. Otherwise will go to the last level and find another unnecessary root.left.
			res.add(root.val);
		if (root.right != null)
			dfs(res, root.right,cur+1);	
		if (root.left != null)
			dfs(res, root.left,cur+1);			//only when root.right checked. then check root.left. but would recheck if res.size()<=cur. means if already root.right added. then root.left would not add any more. 
		return;
	}
}
