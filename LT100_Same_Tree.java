/*
 Given two binary trees, write a function to check if they are equal or not.
 Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 
 Tree, DFS
 */
import java.util.*;
public class LT100_Same_Tree {
	//Iterative
	public boolean isSameTree1(TreeNode p, TreeNode q) {
        if(p ==null && q==null)
            return true;
        
        //pre-order tree --> linkedlist queue.
  		LinkedList<TreeNode> q1 = new LinkedList<TreeNode>();
	    LinkedList<TreeNode> q2 = new LinkedList<TreeNode>();
	    q1.add(p);
	    q2.add(q);
	    while(!q1.isEmpty() && !q2.isEmpty()){
	        TreeNode n1 = q1.pop();
            TreeNode n2 = q2.pop();
            if (n1 == null && n2 == null) {
                continue;
            } else if (n1 == null || n2 == null) {
                return false;
            } else if (n1.val != n2.val) {
                return false;
            }
            
            q1.add(n1.left);
            q2.add(n2.left);
            q1.add(n1.right);
            q2.add(n2.right);
	                    
	    }
	    
	    if (!q1.isEmpty() || !q2.isEmpty()) {
            return false;
        }
		return true;
    }
	
	//recursive
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p ==null && q==null)
            return true;
            
       if(p!=null && q!=null){
            if(p.val ==q.val)   //compare the root node first.
               return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
            else
                return false;
       }
       
       return false;
    }
}
