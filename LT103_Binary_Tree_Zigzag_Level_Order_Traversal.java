import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
Stack, Tree, BFS
 */
public class LT103_Binary_Tree_Zigzag_Level_Order_Traversal {
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) return res;
        
        Stack<TreeNode> queue = new Stack<>();
        
        int level = 1;
        List<Integer> elm = new ArrayList<Integer>();
		elm.add(root.val);
		res.add(elm);
		
        queue.add(root);
       
        while(!queue.isEmpty()){
        	Stack<TreeNode> newQ = new Stack<TreeNode>();		//store nextlevel element.
        	elm = new ArrayList<Integer>();
        	while(!queue.isEmpty()){     						//loop the element in the stack
                TreeNode node = queue.pop();    
                if(level%2==0){         						//check the level number is odd or even. 
                    if(node.left!=null){
                    	newQ.push(node.left);
                        elm.add(node.left.val);
                    }
                    if(node.right!=null){
                    	newQ.push(node.right);
                        elm.add(node.right.val);
                    }
                }else{
                    if(node.right!=null){
                        newQ.push(node.right);					//newQ stack. reverse order to elm.
                        elm.add(node.right.val);				//elm is list. 
                    }
                    if(node.left!=null){
                        newQ.push(node.left);
                        elm.add(node.left.val);
                    }                   
                }
            }
            level++;
            if(elm.size()>0)   
                res.add(elm);
            queue = newQ;
        }
        
        return res;
    }
	
	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		root.left = left;
		root.right = right;
		left.left = new TreeNode(4);
		left.right =  new TreeNode(5);
		right.left =  new TreeNode(6);
		right.right =  new TreeNode(7);
		
		zigzagLevelOrder(root);
	}
}
